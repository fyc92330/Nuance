package utilTest;

import org.chun.somnus.utils.DateTimeUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateTimeUtilTest {

	private static final LocalDateTime today = LocalDateTime.now();
	private static final Timestamp thisTime = Timestamp.valueOf(today);

	@BeforeAll
	static void beforeAll(){
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LocalDateTime Nature Format: " + today);
		System.out.println("Timestamp Nature Format: " + thisTime);
		System.out.println("--------------------------------------------------------------------------");
	}

	@Test
	void dateTime(){
		System.out.println(DateTimeUtil.DateTime.DATE_TIME.format(today));
		System.out.println(DateTimeUtil.DateTime.ISO_8601.format(today));
		System.out.println(DateTimeUtil.DateTime.UNDER_LINE.format(today));
	}


}
