package record.logic;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseLogicImpl implements ParseLogic{

	@Override
	public String eventData(String eventId) throws IOException {

		String eventData = "";	// 파싱해올 스트링 선택
		
//		eventId = "20160510SSLG02016";	// 게임ID - 나중에 get으로 리퀘스트 받아와야 함
		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + eventId + "&category=kbo";	// 파싱할 주소
		Document doc = Jsoup.connect(defUrl)
						.timeout(5000)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?&gameId=" + eventId + "&category=kbo")	// 리퍼러 - 네이버에 방문경로 통보
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
						.get();
		
		if(doc!=null) {
			Element elmt = doc.select("script").get(39);
			System.out.println(doc.select("script").size());
			System.out.println(elmt.toString());
			
			Pattern p = Pattern.compile("(?is)_data : (.+?),굈굏_homeTeamCode");	// JSON 형태의 게임기록 데이터의 패턴을 정규식으로 정의
			Matcher m = p.matcher(elmt.html());	// 위에서 정의한 패턴을 40번째 script 태그가 있는 html 소스 안에서 매칭하도록 정의
			
			
			while(m.find()) {
				eventData = m.group(1);	// 
			}
		} else {
			System.out.println("로직널");
		}
		eventData = eventData.replace("굚r굚n", "");
		System.out.println("logic : " + eventId + eventData);
		return eventData;
	}
}
