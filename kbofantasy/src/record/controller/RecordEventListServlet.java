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
// 경기일정/결과 서블릿
@WebServlet(name = "recordEventList", urlPatterns = { "/recordEventList.do" })
public class RecordEventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String month = request.getParameter("month");
		month = "05";
//		String pathurl = request.getParameter("pathurl");
		String forwardview = "";
		RecordService service = new RecordServiceImpl();
		
		forwardview = "record/eventList.jsp";
		ArrayList<RecordDTO> eventList = service.eventList(month);

//		req.setAttribute("pathurl", pathurl);
		request.setAttribute("eventList", eventList);

		RequestDispatcher rd = request.getRequestDispatcher(forwardview);
		rd.forward(request, response);
	}
}
