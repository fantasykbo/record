package record.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dto.RecordDTO;

public interface RecordDAO {
	ArrayList<RecordDTO> eventList(String month, Connection con) throws SQLException;
	
}