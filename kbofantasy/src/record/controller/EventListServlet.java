package record.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import record.dto.RecordDTO;
import record.service.RecordService;
import record.service.RecordServiceImpl;
// nsd 파일 묶어서 리스트화(비사용)
@WebServlet(name = "eventList", urlPatterns = { "/eventList.do" })
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		year = "2016";
		month = "05";
//		String pathurl = request.getParameter("pathurl");
		String forwardview = "";
		RecordService service = new RecordServiceImpl();
		
		forwardview = "record/eventList.jsp";
		String eventListData = service.eventListData(year, month);

//		req.setAttribute("pathurl", pathurl);
		request.setAttribute("eventListData", eventListData);

		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
