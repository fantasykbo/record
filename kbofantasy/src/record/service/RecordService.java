package record.service;

import java.util.ArrayList;

import record.dto.RecordDTO;


public interface RecordService {
	String eventData(String eventId);
	ArrayList<RecordDTO> eventList(String month);
	String eventListData(String year, String month);
	
}
