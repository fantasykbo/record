package record.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import record.dto.ParseDTO;
import record.service.ParseService;
import record.service.ParseServiceImpl;

@WebServlet(name = "record", urlPatterns = { "/record.do" })
public class RecordParseServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("EUC-KR");
		
//		String eventId = req.getParameter("eventId");
		String eventId = "20160510SSLG02016";
//		String pathurl = req.getParameter("pathurl");
		String forwardview = "";
		ParseService service = new ParseServiceImpl();
		
		forwardview = "record.jsp";
		String eventData = service.eventData(eventId);
		System.out.println("servlet : " + eventId + eventData);

//		req.setAttribute("pathurl", pathurl);
		req.setAttribute("eventData",eventData);

		RequestDispatcher rd = req.getRequestDispatcher(forwardview);
		rd.forward(req, res);
	}
}