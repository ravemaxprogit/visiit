
<%@ include file="Header.jsp" %>
<body ng-app="content">
    <!-- Page container -->
    
    <div class="page-container">  
    <%@ include file="Navigator.jsp" %>

        <!-- Page content -->
        <div ng-controller="packageController" class="page-content">
        
        <!-- Breadcrumbs line -->
            <br/>
            <div class="breadcrumb-line">
                <ul class="breadcrumb">
                    <li><a href="index-2.html">Home</a></li>
                    <li class="active">Dashboard</li>
                </ul>
            </div>
            <!-- /breadcrumbs line -->
            
            <div class="panel-body">
                         <div class="row">  
                            
                            <div class="col-md-6" ng-click="getAllPack()">
	                                 <ctypeahead items="items" prompt="Start typing a Package" title="pkName" subtitle="pkCode" model="pkName" modobj="packObj" modby="pkName" on-select="onPackSelected()" />
                            </div> 
                            <div class="col-md-2">&nbsp;</div>
                            <div class="form-actions text-middle">
                                 <a href="" btn btn-danger><i class="fa fa-search"></i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
                                 <a href="" btn btn-danger><i class="fa fa-refresh"></i></a>
                            </div>
                        </div>
                </div></br>
     
            <div class="well block">
                <div class="tabbable"> 
                    <ul  ng-init="tab = 0" class="nav nav-pills">
                        <li ng-class="{active:tab===$index}" ng-repeat="mod in packmodule"><a ng-click="setPackmod($index,mod)">{{mod}}</a></li>
                    </ul>
                    
                    <div class="tab-content pill-content">
                     <div class="panel panel-default">
                          <div class="panel-heading">
                          <div class="row">
                          <div ng-hide="tab === 3">
                          <div class="col-md-6"><h6 class="panel-title"><i class="icon-pencil3"></i>{{packmod}}</h6></div>
                          <div class="col-md-6"><h6 class="left-header">{{pkName}}</h6></div>
                          </div>
                          <div ng-show="tab === 3">
                          <div class="col-md-4"><h6 class="panel-title"><i class="icon-pencil3"></i>{{packmod}}</h6></div>
                          <div class="col-md-4"><h6 class="left-header">{{pkName}}</h6></div>
                          <div class="col-md-4">
                          <select ng-init="selfilemod=0" ng-model="selfilemod" ng-change="swapfilemod(selfilemod)" name="selfilemod" id="selfilemod" class="form-control">
								        <option ng-repeat="fmod in filemod" value="{{fmod.id}}">{{fmod.name}}</option>
					         </select>
                          </div>
                          </div>
                          </div></div>
                        <div ng-show="tab === 0">
                            <package-detail></package-detail>
                        </div>
                        <div ng-show="tab === 1">
                           <package-activity></package-activity>
                        </div>
                        <div ng-show="tab === 2">
                            <package-price></package-price>
                        </div>
                        <div ng-show="tab === 3">
                             <package-file></package-file>
                        </div>
                        <div ng-show="tab === 4">
                           <package-attraction></package-attraction>
                        </div>
                        <div ng-show="tab === 5">
                            <package-hotel></package-hotel>
                        </div>
                        <div ng-show="tab === 6">
                            <package-condition></package-condition>
                        </div>
                    </div>
                  </div>
                </div>
            </div>
                <input type="hidden" ng-model="pkId">

            <br /><br /><br /><br /><br /><br /><br/>            
            
            <%@ include file="Footer.jsp" %>

        </div>
        <!-- /page content -->

    </div>
    <!-- /page container -->

</body>
