package temp;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EventDateCrawl {

	public static void main(String[] args) {

		String defUrl = "http://sports.news.naver.com/kbaseball/schedule/index.nhn?teamCode=&month=09&year=2016&date=20160513";	// 파싱할 주소
		Document doc;
		try {
			doc = Jsoup.connect(defUrl)
							.timeout(5000)
							.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
							.get();

			if(doc!=null) {
				Elements elmts = doc.select("div.sch_tb2 table tbody tr");
				
				for (int i = 0; i < elmts.size(); i++) {
					Element elmt = elmts.get(i);
					String str = elmt.select("td span.td_hour").text() + ","
							   + elmt.select("td span.team_lft").text() + ","
							   + elmt.select("td span.team_rgt").text() + ","
							   + elmt.select("td span.td_btn a").attr("href");
					str = str.replace("/gameCenter/gameResult.nhn?category=kbo&gameId=", "");
					str = str.replace("/gameCenter/textRelay.nhn?category=kbo&gameId=", "");
					System.out.println(str);
				}
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
