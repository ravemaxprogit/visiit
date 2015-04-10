<%@ include file="Header.jsp" %>
<br><br><br><br><br><br>

<div ng-app="content" ng-controller="packageController">
	<center>
		 <h3>Array of objects</h3>
			  <ui-select multiple ng-model="multipleDemo.selectedPeople" theme="select2" ng-disabled="disabled" style="width: 700px;">
			    <ui-select-match placeholder="Select person...">{{$item.pkName}} </ui-select-match>
			    <ui-select-choices repeat="person in people | propsFilter: {pkName: $select.search, age: $select.search}">
			      <div ng-bind-html="person.pkName | highlight: $select.search"></div>
			      <small>
			        Code: <span ng-bind-html="''+person.pkCode | highlight: $select.search"></span>
			      </small>
			    </ui-select-choices>
			  </ui-select>
			  <p>Selected: {{multipleDemo.selectedPeople}}</p>
 	</center>
</div>

