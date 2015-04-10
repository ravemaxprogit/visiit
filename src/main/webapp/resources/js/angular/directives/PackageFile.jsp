<style>

        * {
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        [ng-drag] {
            -moz-user-select: -moz-none;
            -khtml-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        [ng-drag] {
            width: 100px;
            height: 90px;
            background: rgba(255, 0, 0, 0.5);
            color: white;
            text-align: center;
            padding-top: 20px;
            display: block;
            cursor: move;
        }

        [ng-drag].drag-over {
            border: solid 1px red;
        }

        [ng-drag].dragging {
            opacity: 0.5;
        }

        [ng-drop] {
            background: rgba(0, 0, 0, 0.25);
            text-align: center;
            display: block;
            position: relative;
            padding: 10px;
            width: 131px;
            height: 115px;
            float: left;
        }

        [ng-drop].drag-enter {
            border: solid 5px red;
        }

        [ng-drop] span.title {
            display: block;
            position: absolute;
            top: 50%;
            left: 50%;
            width: 200px;
            height: 20px;
            margin-left: -100px;
            margin-top: -10px;
        }

        [ng-drop] div {
            position: relative;
            z-index: 2;
        }

    </style>
    
         <div class="panel-body">
                   
                   <div class="row">
                            <div class="col-md-2  label-styl">File Type:</div>
                            <div>
                                 <!-- <span ng-click="isCategory=!isCategory" ng-hide="isCategory">
                                     <a href=""><i style="color:#21b384;" class="fa fa-image"></i></a> 
                                     &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                     <a href=""><i style="color:#808080;" class="fa fa-video-camera"></i></a>
                                    
                                 </span> -->
                                 <!-- 
                                  <span ng-click="isCategory=!isCategory" ng-hide="!isCategory">
                                     <a href=""><i style="color:#808080;" class="fa fa-image"></i></a> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
                                     <a href=""><i style="color:#21b384;" class="fa fa-video-camera"></i></a>
                                 </span> -->
                            </div>
                    </div>
                    
                  <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                              <div class="col-md-11">
                                 <img data-ng-show="imgfile" ng-src="{{imgfile}}" class="img-responsive" alt="Category Image" width="400" height="300">
                              </div>
                        </div><br>
                        <div class="row" ng-init="fileLength=4" data-ng-show="imgList.length < fileLength || imgList.length == null">
                           <div class="col-md-6">
                           <button type="file" ng-file-select ng-model="myFiles" ng-focus="uploadfileError();" ng-file-change="fileSelected($files, $event)" ng-multiple="true" accept="image/*,*.pdf" resetOnClick="true" class="btn btn-primary">Upload</button>
                           </div>
                           <div class="col-md-6">
                             <input type="button" data-ng-show="imgfile" value="Save" ng-click="saveImg(catTit)" class="btn btn-danger">
                           </div>
                        </div>
                        <div class="row">
							<span class="errorSpan" ng-show="fileError">{{fileError}}</span>
						</div>
						<div ng-if="selfilemod == '2'">
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Hot Deals' && packageOrder == '1'">Note : Tile Image size should be Width- 263 x Height- 306 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Trending' && packageOrder == '3'">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Best Sellers' && packageOrder == '6'">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Summer Specials' && packageOrder == '7'">Note : Tile Image size should be Width- 525 x Height- 275 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Get Aways' && packageOrder == '8'">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Hot Deals' && packageOrder == null">Note : Tile Image size should be Width- 263 x Height- 306 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Trending' && packageOrder == null">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Best Sellers' && packageOrder == null">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Summer Specials' && packageOrder == null">Note : Tile Image size should be Width- 525 x Height- 275 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == 'Get Aways' && packageOrder == null">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == '' && packageOrder == '2'">Note : Tile Image size should be Width- 263 x Height- 306 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == '' && packageOrder == '4'">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == '' && packageOrder == '5'">Note : Tile Image size should be Width- 258 x Height- 259 pixels</span>
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;" ng-if="packageSpecial == '' && packageOrder == '9'">Note : Tile Image size should be Width- 500 x Height- 750 pixels</span>
						</div>
						<div ng-if="selfilemod == '1'">
							<span style="color:hsl(208, 56%, 53%);font-weight:bold;">Note : Image size should be Width- 524 x height- 338 pixels</span>
							</div>
						</div>
						
                   
                    <div class="col-md-6">
                      <div class="row">
                         <div class="col-md-10">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="">Images</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="img in imgList" ng-drop="true" ng-drop-success="onDropComplete($index, $data,$event)">
                                            <td ng-drag="true" ng-drag-data="img" ng-class=""><img ng-src="{{img.imgData}}" alt="Category Image" width="80" height="60">
                                            <span>{{img.imgName}} |&nbsp;&nbsp;<a href="" ng-click="removePackageImg(img.imgName);"><i style="color:#333;" class="fa fa-trash"></i></a></span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                           </div> 
                      </div>
                    </div>
                     </div>
                  </div>

        