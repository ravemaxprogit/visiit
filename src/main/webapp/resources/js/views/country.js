$( document ).ready(function() {
	$("#country").change(function(){
		var selectedIdx = $("select[name='country'] option:selected").index();
		if(selectedIdx == 0) {
			location.reload();
			return;
			}
    	$("#state").html("");
    	$("#city").html("");
    	$("#location").html("");
    	$('table#paneltbl tr#results').remove();
		var countryId = $('#country').val();
		  $.post("stateList",{"countryId":countryId},
		  function(data,status){
			  if(status=="success"){
				  var dat = (JSON.parse(data))[0];
				  var optStr = "<option value=''>- Choose State -</option>";
				  $.each(dat, function (i, obj) {
					  optStr += "<option value='"+obj.stateId+"'>"+obj.stateName+"</option>";
				  });
				  $("#catId").html("State");
				  $("#state").html(optStr);
				  var results = generateStateResults(dat);
				  $("#paneltbl tr").last().after(results);
			  }
		  });
   });

    $("#state").change(function(){
    	var selectedIdx = $("select[name='state'] option:selected").index();
    	if(selectedIdx == 0){
    		$("#country").trigger("change");
    		return;
    	}
    	$("#city").html("");
    	$("#location").html("");
    	$('table#paneltbl tr#results').remove();
		var stateId = $('#state').val();
		  $.post("cityList",{"stateId":stateId},
		  function(data,status){
			  if(status=="success"){
				  var dat = (JSON.parse(data))[0];
				  var optStr = "<option value=''>- Choose City -</option>";
				  $.each(dat, function (i, obj) {
					  optStr += "<option value='"+obj.cityId+"'>"+obj.cityName+"</option>";
				  });
				  $("#catId").html("City");
				  $("#city").html(optStr);
				  var results = generateCityResults(dat);
				  $("#paneltbl tr").last().after(results);
			  }
		  });
    	});

    $("#city").change(function(){
    	var selectedIdx = $("select[name='city'] option:selected").index();
    	if(selectedIdx == 0){
    		$("#state").trigger("change");
    		return;
    	}
    	$("#location").html("");
		var cityId = $('#city').val();
		$('table#paneltbl tr#results').remove();
		  $.post("locationList",{"cityId":cityId},
		  function(data,status){
			  if(status=="success"){
				  var dat = (JSON.parse(data))[0];
				  var optStr = "<option value=''>- Choose Location -</option>";
				  $.each(dat, function (i, obj) {
					  optStr += "<option value='"+obj.locId+"'>"+obj.locName+"</option>";
				  });
				  $("#location").html(optStr);
				  $("#catId").html("Location");
				  $("#paneltbl tr").last().after(generateLocResults(dat));
			  }
		  });
    	});

    $("#saveBtn").click(function(){
    	if(validate()){
    		var category = $("#catId").html();
    		if(category=='Country'){
    			saveCountry();
    		} else if(category=='State'){
    			saveState();
    		} else if(category=='City'){
    			saveCity();
    		} else if(category=='Location'){
    			saveLocation();
    		}
    	}
    });
});

function saveCountry(){
	var id = $("#id").val();
	var code = $("#code").val();
	var name = $("#name").val();
	var desc = $("#desc").val();
	var active = $('input[name=isActive]:checked')?'Y':'N';
	var country ={};
	country.countryCode = code;
	country.countryName = name;
	country.countryDescription = desc;
	country.countryIsactive = active;
	country.countryId = id;
	var countryStr = JSON.stringify(country);  
	$.ajax({
		url:"saveCountry",
		type:"POST",
		data :{"countryStr":countryStr},
		success: function(json){
			$("#id").val("");
			alert(json);
			$("#onChangeFrm").attr("action","country");
			$("#onChangeFrm").submit();
		},
		error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
		statusCode: {404: function() {alert("This page has been moved.");	}}
	}).done(function(){});
}

function saveState(){
	var id = $("#id").val();
	var countryId = $("#country").val();
	var code = $("#code").val();
	var name = $("#name").val();
	var desc = $("#desc").val();
	var active = $("#isActive").is(':checked') ? 'Y' : 'N';
	var country ={};
	var state ={};
	state.stateCode = code;
	state.stateName = name;
	state.stateDescription = desc;
	country.countryId = countryId;
	state.country = country;
	state.stateIsactive = active;
	state.stateId = id;
	var stateStr = JSON.stringify(state);  
	$.ajax({
		url:"saveState",
		type:"POST",
		data :{"stateStr":stateStr},
		success: function(json){
			clearTexts();
			alert(json);
			$("#country").trigger("change");
		},
		error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
		statusCode: {404: function() {alert("This page has been moved.");	}}
	}).done(function(){});
}

function saveCity(){
	var id = $("#id").val();
	var stateId = $("#state").val();
	var code = $("#code").val();
	var name = $("#name").val();
	var desc = $("#desc").val();
	var active = $('input[name=isActive]:checked')?'Y':'N';
	var state ={};
	var city ={};
	city.cityCode = code;
	city.cityName = name;
	city.cityDescription = desc;
	state.stateId = stateId;
	city.state = state;
	city.cityIsactive = active;
	city.cityId = id;
	var cityStr = JSON.stringify(city);  
	$.ajax({
		url:"saveCity",
		type:"POST",
		data :{"cityStr":cityStr},
		success: function(json){
			clearTexts();
			alert(json);
			$("#state").trigger("change");
		},
		error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
		statusCode: {404: function() {alert("This page has been moved.");	}}
	}).done(function(){});
}

function saveLocation(){
	var id = $("#id").val();
	var cityId = $("#city").val();
	var code = $("#code").val();
	var name = $("#name").val();
	var desc = $("#desc").val();
	var active = $('input[name=isActive]:checked')?'Y':'N';
	var location ={};
	var city ={};
	location.locCode = code;
	location.locName = name;
	location.locDescription = desc;
	city.cityId = cityId;
	location.city = city;
	location.locIsactive = active;
	location.locId = id;
	var locStr = JSON.stringify(location);  
	$.ajax({
		url:"saveLocation",
		type:"POST",
		data :{"locStr":locStr},
		success: function(json){
			clearTexts();
			alert(json);
			$("#city").trigger("change");
		},
		error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
		statusCode: {404: function() {alert("This page has been moved.");	}}
	}).done(function(){});
}


function clearTexts(){
	$("#id").val("");
	$("#code").val("");
	$("#name").val("");
	$("#desc").val("");
	$("#isActive").prop("checked", false);
}

function deleteCountry(id){
	if(!confirm("Are You Sure ?")) return;
	$.post("deleteCountry",{"params":id},
			function(data,status){
		if(status=="success"){
			alert(data);
			$("#cntForm").attr("action","country");
			$("#cntForm").submit();
		}
	});
};

function deleteState(id){
	if(!confirm("Are You Sure ?")) return;
	$.post("deleteStates",{"params":id},
			function(data,status){
				if(status=="success"){
					alert(data);
					$("#country").trigger("change");
				}
	});
};

function deleteCity(id){
	if(!confirm("Are You Sure ?")) return;
	$.post("deleteCity",{"params":id},
		function(data,status){
			if(status=="success"){
				alert(data);
				$("#state").trigger("change");
			}
	});
};

function deleteLoc(id){
	if(!confirm("Are You Sure ?")) return;
	$.post("deleteLocation",{"params":id},
		function(data,status){
			if(status=="success"){
				alert(data);
				$("#city").trigger("change");
			}
	});
};

function editCntry(id,code,name,desc,act){
	$("#id").val(id);
	$("#name").val(name);
	$("#code").val(code);
	$("#desc").val(desc);
	if(act=='Y'){
		$("#isActive").prop("checked", true);
	}else {
    $("#isActive").prop("checked", false);
	}
};

function editState(obj){
	$("#id").val(obj.stateId);
	$("#code").val(obj.stateCode);
	$("#name").val(obj.stateName);
	$("#desc").val(obj.stateDescription);
	if(obj.stateIsactive=='Y'){
		$("#isActive").prop("checked", true);
	}else {
    $("#isActive").prop("checked", false);
	}
};

function editCity(obj){
	$("#id").val(obj.cityId);
	$("#code").val(obj.cityCode);
	$("#name").val(obj.cityName);
	$("#desc").val(obj.cityDescription);
	if(obj.cityIsactive=='Y'){
		$("#isActive").prop("checked", true);
	}else {
    $("#isActive").prop("checked", false);
	}
};

function editLoc(obj){
	$("#id").val(obj.locId);
	$("#code").val(obj.locCode);
	$("#name").val(obj.locName);
	$("#desc").val(obj.locDescription);
	if(obj.locIsactive=='Y'){
		$("#isActive").prop("checked", true);
	}else {
        $("#isActive").prop("checked", false);
	}
};


function generateStateResults(list){
	var result = "";	
	  $.each(list, function (i, obj) {
		  result += "<tr id='results'>";
		  result += "<td>"+obj.stateCode+"</td>";
		  result += "<td>"+obj.stateName+"</td>";
		  result += "<td>"+obj.stateDescription+"</td>";
		  result += "<td>"+obj.stateIsactive+"</td>";
		  result += "<td><p><a href='javascript:return false;' id='deleteBtn'  onclick='deleteState("+obj.stateId+");'><i style='color: red;' class='fa fa-trash'></i></a>";
		  result += "&nbsp;&nbsp;&nbsp; <a href='javascript:return false;' id='editBtn' onclick='editState("+JSON.stringify(obj)+");'><i style='color: #808080;' class='icon-pencil2'></i></a></p></td></tr>";
	  });
	  return result;
}

function generateCityResults(list){
	var result = "";	
	  $.each(list, function (i, obj) {
		  result += "<tr id='results'>";
		  result += "<td>"+obj.cityCode+"</td>";
		  result += "<td>"+obj.cityName+"</td>";
		  result += "<td>"+obj.cityDescription+"</td>";
		  result += "<td>"+obj.cityIsactive+"</td>";
		  result += "<td><p><a href='javascript:return false;' id='deleteBtn' onclick='deleteCity("+obj.cityId+");'><i style='color: red;' class='fa fa-trash'></i></a>";
		  result += "&nbsp;&nbsp;&nbsp; <a href='javascript:return false;' id='editbtn' onclick='editCity("+JSON.stringify(obj)+");'><i style='color: #808080;' class='icon-pencil2'></i></a></p></td></tr>";
	  });
	  return result;
}

function generateLocResults(list){
	var result = "";
	  $.each(list, function (i, obj) {
		  result += "<tr id='results'>";
		  result += "<td>"+obj.locCode+"</td>";
		  result += "<td>"+obj.locName+"</td>";
		  result += "<td>"+obj.locDescription+"</td>";
		  result += "<td>"+obj.locIsactive+"</td>";
		  result += "<td><p><a href='javascript:return false;' id='deletebtn' onclick='deleteLoc("+obj.locId+");'><i style='color: red;' class='fa fa-trash'></i></a>";
		  result += "&nbsp;&nbsp;&nbsp; <a href='javascript:return false;' id='editBtn' onclick='editLoc("+JSON.stringify(obj)+");'><i style='color: #808080;' class='icon-pencil2'></i></a></p></td></tr>";
	  });
	  return result;
}

function validate(){
	var err = '';
	var code = $("#code").val();
	var name = $("#name").val();
	var desc = $("#desc").val();
	if(code==''){
		err = "* Code is required field.\n";
	}
	if(code==''){
		err += "* Name is required field.\n";
	}
	if(code==''){
		err += "* Desc is required field.\n";
	}
	if(err == ''){
		return true;
	}else{
	    alert(err);
	    return false;
	}
}