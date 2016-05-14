package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import record.dto.RecordDTO;

public interface ParseLogic {
	String eventData(String eventId) throws IOException;
	String eventListData(String year, String month);
}