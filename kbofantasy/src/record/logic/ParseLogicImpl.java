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

		String eventData = "";	// �Ľ��ؿ� ��Ʈ�� ����
		
//		eventId = "20160510SSLG02016";	// ����ID - ���߿� get���� ������Ʈ �޾ƿ;� ��
		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + eventId + "&category=kbo";	// �Ľ��� �ּ�
		Document doc = Jsoup.connect(defUrl)
						.timeout(5000)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?&gameId=" + eventId + "&category=kbo")	// ���۷� - ���̹��� �湮��� �뺸
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// ����������Ʈ ����
						.get();
		
		if(doc!=null) {
			Element elmt = doc.select("script").get(39);
			System.out.println(doc.select("script").size());
			System.out.println(elmt.toString());
			
			Pattern p = Pattern.compile("(?is)_data : (.+?),�n�t_homeTeamCode");	// JSON ������ ���ӱ�� �������� ������ ���Խ����� ����
			Matcher m = p.matcher(elmt.html());	// ������ ������ ������ 40��° script �±װ� �ִ� html �ҽ� �ȿ��� ��Ī�ϵ��� ����
			
			
			while(m.find()) {
				eventData = m.group(1);	// 
			}
		} else {
			System.out.println("������");
		}
		eventData = eventData.replace("��r��n", "");
		System.out.println("logic : " + eventId + eventData);
		return eventData;
	}
}
