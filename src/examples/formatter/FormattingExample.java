package examples.formatter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FormattingExample {

	public static void main(String argv[]) {
		DataFormatter f1 = DataFormatter.build().t("Course ").object(0).
				t(" had start date: ").day(1).t("/").month(1).t("-").year(1).
				end();
		Calendar mdsd = new GregorianCalendar(2019, 6, 2);
		Calendar opn = new GregorianCalendar(2017, 9, 1);
		// Course MDSD had start date 2/6-2019
		System.out.println(f1.format("MDSD",mdsd));
		// Course OPN had start date 1/9-2017
		System.out.println(f1.format("OPN",opn));
	}
	
}
