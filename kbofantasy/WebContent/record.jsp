<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
<%
	String eventData = (String)request.getAttribute("eventData");
%>
var eventData = '<%= eventData %>';
var obj = JSON.parse(eventData);
mydata = obj.gameInfo.aFullName;
alert(mydata);
document.getElementById("data").innerHTML = mydata;
</script>
<body>
<div id="data"></div>
</body>
</html>