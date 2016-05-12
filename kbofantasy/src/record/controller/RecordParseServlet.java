package record.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@WebServlet(name = "recordparse", urlPatterns = { "/recordparse.do" })
public class RecordParseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		//
		String gameId = "20160510SSLG02016";	// 게임ID - 나중에 get으로 리퀘스트 받아와야 함
		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + gameId + "&category=kbo";	// 파싱할 주소
		Document doc = Jsoup.connect(defUrl)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?category=kbo&gameId=" + gameId)	// 리퍼러 - 네이버에 방문경로 통보
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
						.get();

		Element script = doc.select("script").get(39);	// script 태그의 40번째 항목 가져오기

		Pattern p = Pattern.compile("(?is)_data : (.+?),\n\t_homeTeamCode");	// JSON 형태의 게임기록 데이터의 패턴을 정규식으로 정의
		Matcher m = p.matcher(script.html());	// 위에서 정의한 패턴을 40번째 script 태그가 있는 html 소스 안에서 매칭하도록 정의

		String gameData = "";	// 파싱해올 스트링 선택
		
		while(m.find()) {
			gameData = m.group(1);	// 
		}

		System.out.println(gameData);

		JSONParser gameDataJson = new JSONParser();
		try {
			JSONObject gameDataObj = (JSONObject)gameDataJson.parse(gameData);
			
			
			System.out.println(gameDataObj.get("gameInfo"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
//		PrintWriter pw = response.getWriter();
//		pw.write(gameDataJson.toJSONString());
	
	
	}
}
