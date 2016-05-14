<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<%String eventData = (String) request.getAttribute("eventData");%>

 	$(document).ready(function() {

 		var eventData = '<%=eventData%>';
 		var obj = JSON.parse(eventData);

 	    var gDayArr = new Array('��', '��', 'ȭ', '��', '��', '��', '��');

 	    var gDate = obj.gameInfo.gdate.toString();
 		var gYear = gDate.substr(0,4);
 		var gMonth = gDate.substr(4,2);
 		var gDay = gDate.substr(6,2);
 		var d = new Date(gYear, gMonth - 1, gDay);
 		var gWeekday = gDayArr[d.getDay()];
		
		// ���ھ�� head
		var sbSize = obj.currentInning;
		var sbHwin = "";
		var sbThead = "<th></th>";
		for(i = 1; i <= sbSize; i++) {
			sbThead += "<th>" + i + "</th>";
		}
		$("#game-scoreboard thead tr").prepend(sbThead);

		// ���ھ�� �ϴ�
		$("#game-info").text(gYear + "." + gMonth + "." + gDay + "(" + gWeekday + ") " + obj.gameInfo.gtime + ", " + obj.gameInfo.stadium);

		for(i = 0; i < obj.etcRecords.length; i++) {
			$("#game-etc").append("<li><b>[" + obj.etcRecords[i].how + "]</b> " + obj.etcRecords[i].result + "</li>");
		}
		
		// H, A ���� ���� �ѷ��ִ� ���
		function record(f, s) {
	 		$("#" + s + "-team-logo").attr("src", "http://imgsports.naver.net/images/emblem/new/kbo/default/" + eval("obj.gameInfo." + s + "Code") + ".png");
			$("#" + s + "-team-score").text(eval("obj.scoreBoard.rheb." + f + ".r"));
			$("#" + s + "-team-info").append("<h1>" + eval("obj.gameInfo." + s + "FullName") + "<br/></h1>");
			$("#" + s + "-team-info h1").append("<small><span class=\"label label-default\">" + eval("obj." + f + "Standings.rank") + "��</span>"
					+ eval("obj." + f + "Standings.w") + " �� " + eval("obj." + f + "Standings.d") + "�� " + eval("obj." + f + "Standings.l") + "��<small>");
			$("#" + s + "-batter-title").text(eval("obj.gameInfo." + s + "FullName") + " Ÿ�ڱ��");
			$("#" + s + "-pitcher-title").text(eval("obj.gameInfo." + s + "FullName") + " �������");
			
			// ���ھ��
			var sbTbody = "<tr><td>" + eval("obj.gameInfo." + s + "Name") + "</td>";
			
			if(eval("obj.scoreBoard.inn." + f + ".length") < sbSize) {
				sbSize -= 1;
				sbHwin = "<td>-</td>";
			}
			for(i = 0; i < sbSize; i++) {
				sbTbody += "<td>" + eval("obj.scoreBoard.inn." + f + "[" + i + "]") + "</td>";
			}
			sbTbody += sbHwin
					 + "<td>" + eval("obj.scoreBoard.rheb." + f + ".r") + "</td>"
					 + "<td>" + eval("obj.scoreBoard.rheb." + f + ".h") + "</td>"
					 + "<td>" + eval("obj.scoreBoard.rheb." + f + ".e") + "</td>"
					 + "<td>" + eval("obj.scoreBoard.rheb." + f + ".b") + "</td></tr>";
					 
			$("#game-scoreboard tbody").append(sbTbody);

			// Ÿ�ڱ�� head
			var brSize = obj.currentInning;
			var brThead = "<th>��</th><th>������</th>";
			for(i = 1; i <= brSize; i++) {
				brThead += "<th>" + i + "</th>";
			}
			$("#" + s + "-batter-record thead tr").prepend(brThead);

			// Ÿ�ڱ�� body
			var brTbody = "";
			for(i = 0; i < eval("obj.battersBoxscore." + f + ".length"); i++) {
				brTbody += "<tr><td>" + eval("obj.battersBoxscore." + f + "[" + i + "].pos") + "</td>";
				brTbody += "<td><a href=\"http://www.koreabaseball.com/Record/Player/HitterDetail/Basic.aspx?playerId="
						+ eval("obj.battersBoxscore." + f + "[" + i + "].playerCode") + "\" target=\"_blank\">"
						+ eval("obj.battersBoxscore." + f + "[" + i + "].name") + "</a></td>";
				for(j = 1; j <= brSize; j++) {
					brTbody += "<td>" + eval("obj.battersBoxscore." + f + "[" + i + "].inn" + j) + "</td>";
				}
				brTbody += "<td>" + eval("obj.battersBoxscore." + f + "[" + i + "].ab") + "</td>";
				brTbody += "<td>" + eval("obj.battersBoxscore." + f + "[" + i + "].hit") + "</td>";
				brTbody += "<td>" + eval("obj.battersBoxscore." + f + "[" + i + "].rbi") + "</td>";
				brTbody += "<td>" + eval("obj.battersBoxscore." + f + "[" + i + "].run") + "</td>";
				brTbody += "<td>" + eval("obj.battersBoxscore." + f + "[" + i + "].hra") + "</td>";
				
				brTbody +="</tr>";
			}
			$("#" + s + "-batter-record tbody").append(brTbody);
			
			// ������� body
			var prTbody = "";
			for(i = 0; i < eval("obj.pitchersBoxscore." + f + ".length"); i++) {
				prTbody += "<tr><td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].name") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].wls") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].gameCount") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].w") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].l") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].s") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].inn") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].pa") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].bf") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].ab") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].hit") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].bbhp") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].hr") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].kk") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].r") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].er") + "</td>";
				prTbody += "<td>" + eval("obj.pitchersBoxscore." + f + "[" + i + "].era") + "</td>";
				prTbody += "</tr>";
			}
			$("#" + s + "-pitcher-record tbody").append(prTbody);

		}

		
		record("away", "a");
		record("home", "h");

 	
 	});
</script>

</head>
<body>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header text-primary">
						<h1>�����</h1>
					</div>
				</div>
			</div>
					<div class="row">
						<div class="col-md-2 text-center">
							<img id="a-team-logo" class="center-block img-responsive">
						</div>
						<div class="col-md-1">
							<h1 id="a-team-score" class="text-center"></h1>
						</div>
						<div class="col-md-6 text-center">
							<table id="game-scoreboard" class="table table-condensed">
								<thead>
									<tr>
										<th>R</th>
										<th>H</th>
										<th>E</th>
										<th>B</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
							<h4 id="game-info"></h4>
						</div>
						<div class="col-md-1">
							<h1 id="h-team-score" class="text-center"></h1>
						</div>
						<div class="col-md-2 text-center">
							<img id="h-team-logo" class="center-block img-responsive">
						</div>
					</div>
					<div class="row" draggable="true">
						<div id="a-team-info" class="col-md-3 text-left">
						</div>
						<div class="col-md-6">
							<ol id="game-etc" class="list-inline text-justify">
							</ol>
						</div>
						<div id="h-team-info" class="col-md-3 text-right">
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h3 id="a-batter-title" class="text-muted"></h3>
							<table id="a-batter-record" class="table table-bordered table-condensed table-striped">
								<thead>
									<tr>
										<th>Ÿ��</th>
										<th>��Ÿ</th>
										<th>Ÿ��</th>
										<th>����</th>
										<th>Ÿ��</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<h3 id="h-batter-title" class="text-muted"></h3>
							<table id="h-batter-record" class="table table-bordered table-condensed table-striped">
								<thead>
									<tr>
										<th>Ÿ��</th>
										<th>��Ÿ</th>
										<th>Ÿ��</th>
										<th>����</th>
										<th>Ÿ��</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<h3 id="a-pitcher-title" class="text-muted"></h3>
							<table id="a-pitcher-record" class="table table-bordered table-condensed table-striped">
								<thead>
									<tr>
										<th>������</th>
										<th>���</th>
										<th>���</th>
										<th>��</th>
										<th>��</th>
										<th>��</th>
										<th>�̴�</th>
										<th>Ÿ��</th>
										<th>����</th>
										<th>Ÿ��</th>
										<th>��Ÿ</th>
										<th>4��</th>
										<th>Ȩ��</th>
										<th>����</th>
										<th>����</th>
										<th>��å</th>
										<th>����å</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<h3 id="h-pitcher-title" class="text-muted"></h3>
							<table id="h-pitcher-record" class="table table-bordered table-condensed table-striped">
								<thead>
									<tr>
										<th>������</th>
										<th>���</th>
										<th>���</th>
										<th>��</th>
										<th>��</th>
										<th>��</th>
										<th>�̴�</th>
										<th>Ÿ��</th>
										<th>����</th>
										<th>Ÿ��</th>
										<th>��Ÿ</th>
										<th>4��</th>
										<th>Ȩ��</th>
										<th>����</th>
										<th>����</th>
										<th>��å</th>
										<th>����å</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>



</body>
</html>