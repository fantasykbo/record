package temp;

import java.io.IOException;

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
import org.jsoup.select.Elements;

@WebServlet(name = "livescore", urlPatterns = { "/livescore.do" })
public class LiveScoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		//
		String forwardview="";
		String Url = "http://sportsdata.naver.com/ndata//kbo/2016/05/20160510OBSK02016.nsd";	// ÆÄ½ÌÇÒ ÁÖ¼Ò
		Document doc = Jsoup.connect(Url).get();
		
		Elements script = doc.getElementsByTag("script");
		JSONObject kboObj = null;
		JSONParser jsonp = new JSONParser();
		String kbo = script.html();
		kbo = kbo.replace("document.domain=‚"naver.com‚";parent.sportscallback_relay(document, ", "");
		kbo = kbo.replace(");", "");
		System.out.println(kbo+"first");
		try {
			kboObj = (JSONObject)jsonp.parse(kbo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("kboObj", kboObj);
		System.out.println(kboObj.toJSONString()+"second");
		forwardview ="/liveScore/Parse_nsd.jsp";
//		String str = (String) kboObj.
		
		/*RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);*/
	}
}

