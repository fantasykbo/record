package record.service;

import java.io.IOException;

import record.logic.ParseLogic;
import record.logic.ParseLogicImpl;


public class ParseServiceImpl implements ParseService {

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
}
