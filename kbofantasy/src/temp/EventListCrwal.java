package temp;

import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class EventListCrwal {

	public static void main(String[] args) {

		String year  = "2016";
		String month = "06";
		String day = "";
		String data = "{";
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), (Integer.parseInt(month) - 1), 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		String str = "";
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
		System.out.println(data);
	}

}
