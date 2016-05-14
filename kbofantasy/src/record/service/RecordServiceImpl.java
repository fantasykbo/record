package record.service;

import static fw.JdbcTemplate.close;
import static fw.JdbcTemplate.getConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dao.RecordDAO;
import record.dao.RecordDAOImpl;
import record.dto.RecordDTO;
import record.logic.ParseLogic;
import record.logic.ParseLogicImpl;


public class RecordServiceImpl implements RecordService {

	@Override
	public String eventData(String eventId) {
		String eventData = new String();
		ParseLogic logic = new ParseLogicImpl();
		try {
			eventData = logic.eventData(eventId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("service : " + eventId + eventData);
		return eventData;
	}

	@Override
	public ArrayList<RecordDTO> eventList(String month) {
		ArrayList<RecordDTO> list = new ArrayList<RecordDTO>();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			list = dao.eventList(month, con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return list;
	}

	@Override
	public String eventListData(String year, String month) {
		String eventListData = new String();
		ParseLogic logic = new ParseLogicImpl();
		eventListData = logic.eventListData(year, month);
		return eventListData;
	}
}
