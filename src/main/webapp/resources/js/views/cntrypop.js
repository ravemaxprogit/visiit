$( document ).ready(function() {
	$("#country").change(function(){
    	$("#state").html("");
    	$("#city").html("");
    	$("#location").html("");
    	$('table#paneltbl tr#results').remove();
		var countryId = $('#country').val();
		  $.post("stateList",{"countryId":countryId},
		  function(data,status){
			  if(status=="success"){
				  var dat = (JSON.parse(data))[0];
				  var optStr = "<option value='0'>- Choose State -</option>";
				  $.each(dat, function (i, obj) {
					  optStr += "<option value='"+obj.stateId+"'>"+obj.stateName+"</option>";
				  });
				  $("#catId").html("State");
				  $("#state").html(optStr);
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
				  var optStr = "<option value='0'>- Choose City -</option>";
				  $.each(dat, function (i, obj) {
					  optStr += "<option value='"+obj.cityId+"'>"+obj.cityName+"</option>";
				  });
				  $("#catId").html("City");
				  $("#city").html(optStr);
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
				  var optStr = "<option value='0'>- Choose Location -</option>";
				  $.each(dat, function (i, obj) {
					  optStr += "<option value='"+obj.locId+"'>"+obj.locName+"</option>";
				  });
				  $("#location").html(optStr);
				  $("#catId").html("Location");
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
	var active = 'Y';
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
	var active = 'Y';
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
	var active = 'Y';
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
	var active = 'Y';
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