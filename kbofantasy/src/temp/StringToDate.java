package temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class StringToDate {

	public static void main(String[] args) throws ParseException {
		String from = "2016-05-01 14:00:00";
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date to = transFormat.parse(from);
		System.out.println(to);
		GregorianCalendar c = new GregorianCalendar();
		c.setGregorianChange(to);
		System.out.println(c);
	}

}
