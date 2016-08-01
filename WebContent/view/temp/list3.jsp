<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page session="true" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
    <title>Trirand jqGrid - jQuery based grid HTML5 component for Javascript</title>
    
	<link rel="stylesheet" type="text/css" media="screen" href="http://cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css" />
	<script type="text/javascript" src='<c:url value="/resources/jquery-1.11.3.js"/>'></script>
    <script type="text/javascript" src='http://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js'></script>
    
    <style>
    
tfoot{
display: none;
}    
    
    </style>

<script>

$(document).ready(function() {
    $('#example').DataTable({
    	 "language": {                "url": "./korean.lang"            }
    	
    	
    });
    
    var table = $('#example').DataTable();
    
    $('#example tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
    
    $('#btn1').click( function () {
        alert( table.rows('.selected').data().length+' row(s) selected' );
        
        
        var selected= table.rows('.selected').data();
        
        var selectedList = [];
        
        for (i = 0; i < selected.length; i++) {
            
        	
        	//alert(selected[i][0]);
        	
        	selectedList.push(selected[i][0]);
        }
        
        console.log(selectedList);
        
    } );
} );
	

 
   </script>

</head>

<body>

    
<input type="button" value="btn1" id="btn1"></input>

	<div style="width: 800px">
	아이디:<input type="text" id="id"></input>
    <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
            	
            	<th>id</th>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
 
        <tbody>
            <tr>
            	
            	<td>0</td>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            <c:forEach begin="1" end="300" var="i">
            <tr>
            	
            	 <td>${i}</td>
            	 <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            </c:forEach>
         </tbody>
</table>    
</div>
</body>
</html>

