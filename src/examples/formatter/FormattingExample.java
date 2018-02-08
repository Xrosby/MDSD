package examples.formatter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FormattingExample {

	public static void main(String argv[]) {
		DataFormatter f1 = DataFormatter.build().t("Course ").object(0).
				t(" had start date: ").day(1).t("/").month(1).t("-").year(1).
				end();
		Calendar mdsd = new GregorianCalendar(2018, 6, 2);
		Calendar opn = new GregorianCalendar(2017, 9, 1);
		System.out.println(f1.format("MDSD",mdsd));
		System.out.println(f1.format("OPN",opn));
	}
	
}
