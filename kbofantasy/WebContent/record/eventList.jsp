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
<%
	ArrayList<RecordDTO> eventList = (ArrayList<RecordDTO>) request.getAttribute("eventList");
	int size = eventList.size();

%>
</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						KBO���� ����/��� <br>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul class="pager">
						<li><a href="#">�� Prev</a></li>
						<span>�׽�Ʈ</span>
						<li><a href="#">Next ��</a></li>
					</ul>
					<table class="table table-bordered table-striped">
						<tbody>
						<% for(int i = 0; i < size; i++) { 
								RecordDTO dto = eventList.get(i);
								%>
							<tr>
								<td><%= dto.getEventDate() %></td>
								<td><%= dto.getEventDate() %></td>
								<td>
									<span><%= dto.getaTeamSName() %></span>
									<% if(dto.getEventStatus() != null && dto.getEventStatus().equals("4")) { %>
										<span><%= dto.getaScore() %> : <%= dto.gethScore() %></span>
									<% } else { %>
										<span> vs </span>
									<% } %>
									<span><%= dto.gethTeamSName() %></span>
								</td>
								<td><%= dto.getEventCode() %></td>
								<td><%= dto.getStadium() %></td>
							</tr>
							<% } %>
						</tbody>
						<thead>
							<tr>
								<th>��¥</th>
								<th>�ð�</th>
								<th>���</th>
								<th>�߰�/���</th>
								<th>����</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>