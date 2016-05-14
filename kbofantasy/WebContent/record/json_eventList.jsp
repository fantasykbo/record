<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.util.Date"%>
<%@page import="record.dto.RecordDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<%
	String eventListData = (String) request.getAttribute("eventListData");
%>
	var eventListData = '<%= eventListData %>';
	
	var obj = JSON.parse(eventListData);

	alert(obj);

</script>
</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						KBO리그 일정/결과 <br>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul class="pager">
						<li><a href="#">← Prev</a></li>
						<span>테스트</span>
						<li><a href="#">Next →</a></li>
					</ul>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<td>
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<th>날짜</th>
								<th>시간</th>
								<th>경기</th>
								<th>중계/기록</th>
								<th>구장</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>