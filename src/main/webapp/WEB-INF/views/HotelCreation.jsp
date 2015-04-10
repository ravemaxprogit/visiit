<%@ include file="Header.jsp" %>

<body nd-app="content">
    <!-- Page container -->

    <div class="page-container">  
    <%@ include file="Navigator.jsp" %>

        <!-- Page content -->
        <div class="page-content">
        
        <!-- Breadcrumbs line -->
            <br/>
            <div class="breadcrumb-line">
                <ul class="breadcrumb">
                    <li><a href="index-2.html">Home</a></li>
                    <li class="active">Dashboard</li>
                </ul>
            </div>
            <!-- /breadcrumbs line -->
        
        <form role="form" id="hotelFrm" novalidate>
                <div class="panel panel-default">
                  <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>Hotel Details</h6></div>
                    <div class="panel-body">
                     
                     <div class="row">
                       <div class="col-md-6">
                           <input type="text" name="hotel" placeholder="- Choose Hotel -" class="form-control" ng-model="hotel" required>
                       </div>               
                        <div class="col-md-4">
                            <a href="" btn btn-danger></a>&nbsp;&nbsp;|&nbsp;&nbsp;
                            <a href="" btn btn-danger><i class="fa fa-refresh"></i></a>
                        </div>
                     </div><br>
                        
                      <div class="row">
                            <div class="col-md-2  subtitle"> Name:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelName" name="hotelName" placeholder="hotel name" class="form-control" ng-model="hotelName" required>
                            </div>
                            <div class="col-md-2  subtitle">Hotel code:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelCode" name="hotelCode" placeholder="" class="form-control" ng-model="hotelCode" required>
                            </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-2  subtitle">Hotel Type:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelType" name="hotelType" placeholder="select" class="form-control" ng-model="hotelType" required>
                            </div>
                            <div class="col-md-2  subtitle">Description:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelDesc" name="hotelDesc" placeholder="located area, special feature" class="form-control" ng-model="hotelDesc" required>
                            </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-2  subtitle">Phone:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelPhone" name="hotelPhone" placeholder="99999999" class="form-control" ng-model="hotelPhone" required>
                            </div>
                            <div class="col-md-2  subtitle">Email:</div>
                            <div class="col-md-3">
                               <input type="email" id="hotelEmail" name="hotelEmail" placeholder="name@email.com" class="form-control" ng-model="hotelEmail" required>
                            </div>
                       </div>
                        
                        <div class="row">
                            <div class="col-md-2  subtitle">Address:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelAddress" name="hotelAddress" placeholder="#no, place" class="form-control" ng-model="hotelAddress" required>
                            </div>
                            <div class="col-md-2  subtitle">City:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelCity" name="hotelCity" placeholder="" class="form-control" ng-model="hotelCity" required>
                            </div>        
                       </div>
                       
                       <div class="row">
                           <div class="col-md-2  subtitle">Country:</div>
                            <div class="col-md-3">
                               <select name="country" id="country" class="form-control"
										required>
										<option value="">- Choose Country -</option>
										<c:if test="${!empty cntryList}">
											<c:forEach items="${cntryList}" var="cntry">
												<option value="${cntry.countryId}">${cntry.countryName}</option>
											</c:forEach>
										</c:if>
									</select>
							</div>
                            <div class="col-md-2  subtitle">State:</div>
                            <div class="col-md-3">
                               <select name="state" id="state" placeholder="- Choose state -"
										class="form-control" required>
									</select>
							 </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-2  subtitle">Postal Code:</div>
                            <div class="col-md-3">
                               <input type="text" id="hotelPostal" name="hotelPostal" placeholder="" class="form-control" ng-model="hotelPostal" required>
                            </div>
                            <div class="col-md-2  subtitle">Fax:</div>
                            <div class="col-md-3">
                               <input type="number" id="hotelFax" name="hotelFax" placeholder="" class="form-control" ng-model="hotelFax" required>
                            </div>
                       </div>
                       
                   </div>
             </div>   <br>
             
            <div class="panel panel-default">
                  <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i>Hotel Contact Details</h6></div>
                    <div class="panel-body">
                      <div class="row">
                            <div class="col-md-2  subtitle"><i style="color:#1d9d74;" class="fa fa-user"></i>&nbsp;&nbsp;&nbsp;[Contact 1]</div>
                            <div class="col-md-3">&nbsp;&nbsp;&nbsp;</div>
                            <div class="col-md-2  subtitle"><i style="color:#1d9d74;" class="fa fa-user"></i>&nbsp;&nbsp;&nbsp;[Contact 2]</div>
                            <div class="col-md-3">&nbsp;&nbsp;&nbsp;</div>
                      </div>
                      <div class="row">
                            <div class="col-md-2  subtitle">Name:</div>
                            <div class="col-md-3">
                               <input type="text" id="name1" placeholder="contact person name" class="form-control" ng-model="name1" required>
                            </div>
                            <div class="col-md-2  subtitle">Name:</div>
                            <div class="col-md-3">
                               <input type="text" id="name2" placeholder="contact person name" class="form-control" ng-model="name2" required>
                            </div>
                       </div>
                       
                        <div class="row">
                            <div class="col-md-2  subtitle">Phone:</div>
                            <div class="col-md-3">
                               <input type="text" id="phone1" placeholder="+9999999999" class="form-control" ng-model="phone1" required>
                            </div>
                            <div class="col-md-2  subtitle">Phone:</div>
                            <div class="col-md-3">
                               <input type="text" id="phone2" placeholder="+9999999999" class="form-control" ng-model="phone2" required>
                            </div>
                       </div>
                        
                        <div class="row">
                            <div class="col-md-2  subtitle">Email:</div>
                            <div class="col-md-3">
                               <input type="email" id="email1" placeholder="name@email.com" class="form-control" ng-model="email1" required>
                            </div>
                            <div class="col-md-2  subtitle">Email:</div>
                            <div class="col-md-3">
                               <input type="email" id="email2" placeholder="name@email.com" class="form-control" ng-model="email2" required>
                            </div>
                       </div>
                       
                       <div class="row">
                            <div class="col-md-2  subtitle">Position:</div>
                            <div class="col-md-3">
                               <input type="text" id="position1" placeholder="select" class="form-control" ng-model="position1" required>
                            </div>
                            <div class="col-md-2  subtitle">Position:</div>
                            <div class="col-md-3">
                               <input type="text" id="position2" placeholder="select" class="form-control" ng-model="position2" required>
                            </div>
                       </div>
                   </div>
             </div>  
             <br>          
             <div class="form-actions text-right">
                    <input type="button" value="Save" class="btn btn-danger" onClick="saveHotel();">
                    <input type="reset" value="Clear" class="btn btn-default">
              </div>
        
        </form></br>
        
        <form action="#" role="form">
                <div class="panel panel-default">
                    <div class="panel-heading"><h6 class="panel-title"><i class="icon-pencil3"></i> Data Grid</h6></div>
                    <div class="panel-body">

                        <div class="block">
                            <div class="">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>

                                            <th class="">Code</th>
                                            <th class="">Hotel Name</th>
                                            <th class="">Contact Person</th>
                                            <th class="">Phone</th>
                                            <th class="">Email</th>
                                            <th class="">Description</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                  <c:if test="${hotelList != null}">
								    <c:forEach items="${hotelList}" var="hotel">
                                        <tr>
                                            <td>${hotel.hdCode}</td>
                                            <td>${hotel.hdName}</td>
                                            <td>${hotel.hdFax}</td>
                                            <td>${hotel.hdPhone}</td>
                                            <td>${hotel.hdEmail}</td>
                                            <td>${hotel.hdDescription}</td>
                                            <td>
                                            <p>
                                                <a href=""><i style="color:#F57A8F;" class="fa fa-times"></i></a> &nbsp;&nbsp;&nbsp;
                                                <a href=""><i style="color:#808080;" class="icon-pencil2"></i></a>
                                            </p>
                                            </td>
                                        </tr>
                                      </c:forEach>
                                  </c:if>  
                                    </tbody>
                                </table>
                            </div>
                        </div>


                    </div>
                </div>
            </form>

<script type="text/javascript" src="resources/js/views/country.js"></script>
<script type="text/javascript">
function saveHotel(){
	var id = $("#hotelCode").val();
	var title = $("#hotelName").val();
	var type = $("#hotelType").val();
	var desc = $("#hotelDesc").val();
	var address = $("#hotelAddress").val();
	var phone = $("#hotelPhone").val();
	var city = $("#hotelCity").val();
	var email = $("#hotelEmail").val();
	var state = $("#state").val();
	var country = $("#country").val();
	var postal = $("#hotelPostal").val();
	var fax = $("#hotelFax").val();
	
	var name1 = $("#name1").val();
	var phone1 = $("#phone1").val();
	var email1 = $("#email1").val();
	var position1 = $("#position1").val();
	var name2 = $("#name2").val();
	var phone2 = $("#phone2").val();
	var email2 = $("#email2").val();
	var position2 = $("#position2").val();
	
	if(title=='' || type==''){
		alert("Hotel title and Hotel description are required fields!");
		return true;
	}
	var hotel ={};
	var hotelContact ={};
	
	hotel.hdCode = id;
	hotel.hdName = title;
	hotel.hdCategory = type;
	hotel.hdDescription = desc;
	hotel.hdAddress = address;
	hotel.hdCity = city;
	hotel.hdState = state;
	hotel.hdCountry = country;
	hotel.hdEmail = email;
	hotel.hdPhone = phone;
	hotel.hdFax = fax;
	
	hotelContact.hcContactName1 = name1;
	hotelContact.hcEmail1 = email1;
	hotelContact.hcPhone1 = phone1;
	hotelContact.hcPosition1 = position1;
	hotelContact.hcContactName2 = name2;
	hotelContact.hcEmail2 = email2;
	hotelContact.hcPhone2 = phone2;
	hotelContact.hcPosition2 = position2;
	
	var hotelStr = JSON.stringify(hotel);  
	var hotelContStr = JSON.stringify(hotelContact); 
	
	$.ajax({
		url:"saveHotel",
		type:"POST",
		data :{"hotelStr":hotelStr,"hotelContStr":hotelContStr},
		success: function(json){
			clearFields();
			alert(json);
			$("#hotelFrm").attr("action","hotelMaster");
			$("#hotelFrm").submit();
		},
		error: function (xhr, ajaxOptions, thrownError){alert(xhr.statusText);},
		statusCode: {404: function() {alert("This page has been moved.");	}}
	}).done(function(){});
}

function clearFields(){
	$("#hotelCode").val("");
	$("#hotelType").val("");
	$("#hotelDesc").val("");
	$("#hotelAddress").val("");
	$("#hotelPhone").val("");
	$("#hotelCity").val("");
	$("#hotelEmail").val("");
	$("#hotelState").val("");
	$("#hotelCountry").val("");
	$("#hotelPostal").val("");
	$("#hotelFax").val("");
	
	$("#name1").val("");
	$("#phone1").val("");
	$("#email1").val("");
	$("#position1").val("");
	$("#name2").val("");
	$("#phone2").val("");
	$("#email2").val("");
	$("#position2").val("");
}
</script>
            <!-- /tasks table -->
            <br /><br /><br /><br /><br /><br /><br/>            
            
            <%@ include file="Footer.jsp" %>

        </div>
        <!-- /page content -->

    </div>
    <!-- /page container -->


</body>