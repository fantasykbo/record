package record.logic;

import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseLogicImpl implements ParseLogic{

	@Override
	public String eventData(String eventId) throws IOException {

		String eventData = "";

		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + eventId + "&category=kbo";	// ÆÄ½ÌÇÒ ÁÖ¼Ò
		Document doc = Jsoup.connect(defUrl)
						.timeout(5000)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?&gameId=" + eventId + "&category=kbo")	// ¸®ÆÛ·¯ - ³×ÀÌ¹ö¿¡ ¹æ¹®°æ·Î Åëº¸
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// À¯Àú¿¡ÀÌÀüÆ® Á¤ÀÇ
						.get();
		
		if(doc!=null) {
			Element elmt = doc.select("script").get(39);
			System.out.println(doc.select("script").size());
			System.out.println(elmt.toString());
			
			Pattern p = Pattern.compile("(?is)_data : (.+?),‚n‚t_homeTeamCode");	// JSON ÇüÅÂÀÇ °ÔÀÓ±â·Ï µ¥ÀÌÅÍÀÇ ÆĞÅÏÀ» Á¤±Ô½ÄÀ¸·Î Á¤ÀÇ
			Matcher m = p.matcher(elmt.html());	// À§¿¡¼­ Á¤ÀÇÇÑ ÆĞÅÏÀ» 40¹øÂ° script ÅÂ±×°¡ ÀÖ´Â html ¼Ò½º ¾È¿¡¼­ ¸ÅÄªÇÏµµ·Ï Á¤ÀÇ
			
			
			while(m.find()) {
				eventData = m.group(1);	// 
			}
		} else {
			System.out.println("·ÎÁ÷³Î");
		}
		eventData = eventData.replace("‚‚r‚‚n", "");
		System.out.println("logic : " + eventId + eventData);
		return eventData;
	}

	@Override
	public String eventListData(String year, String month) {
		
		String day = "";
		String data = "{";
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), (Integer.parseInt(month) - 1), 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);

		for (int i = 1; i <= lastDay; i++) {
		
			if(i < 10) {
				day = "0" + i;
			} else {
				day = "" + i;
			}
			String defUrl = "http://sportsdata.naver.com/ndata//kbo/" + year + "/" + month + "/" + year + month + day + ".nsd";	// ÆÄ½ÌÇÒ ÁÖ¼Ò
			Document doc;
			try {
				doc = Jsoup.connect(defUrl)
								.timeout(5000)
								.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// À¯Àú¿¡ÀÌÀüÆ® Á¤ÀÇ
								.get();
			} catch (IOException e) {
				doc = null;
			}
			String str = "";
			
			if(doc!=null) {
				Element elmt = doc.select("script").first();
				str = elmt.html();
				str = str.replace("document.domain=‚"naver.com‚";parent.sportscallback_gameList(document, ", "");
				str = str.replace(");", "");
				
			} else {
				str = "‚"none‚"";
			}

			str = "‚"" + year + month + day + "‚":" + str + ",";
			data += str;
		}
		data = data.substring(0, data.length() - 1);
	    data += "}";

	    return data;
	}
}
