package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import record.dto.ParseDTO;

public interface ParseLogic {
	String eventData(String eventId) throws IOException;
	
}