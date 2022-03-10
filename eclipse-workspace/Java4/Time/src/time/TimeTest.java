package time;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", 
				seconds == 18305);
	}
	
	@Test
	public void testGetTotalSecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class, 
				()-> {
					Time.getTotalSeconds("10:00");
					}
				);
	}
	
	@Test
	void testGetMilliseconds() {
		int milliseconds = Time.getMilliseconds("10:59:59:005");
		assertTrue("The milliseconds were not calculated properly", 
				milliseconds == 5);
	}
	
	@Test
	void testGetMillisecondsBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {
					Time.getMilliseconds("10:59:59:9999");
				});
	}
	
	@Test
	void testGetMillisecondsBoundary() {
		int milliseconds = Time.getMilliseconds("10:59:59:999");
		assertTrue("The milliseconds were not calculated properly", 
				milliseconds == 999);
	}
	
	@Test
	public void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("10:10:10");
		assertTrue("The seconds were not calculated properly", 
				seconds == 36610);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:05:05", "06:08:05", "08:03:05"})
	void testGetSecondsGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds value is not correct", seconds == 5);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {
					Time.getSeconds("10:00");
				});
	}
	
	@Test
	void testGetSecondsBoundary() {
		int seconds = Time.getSeconds("05:05:05");
		assertTrue("The seconds value is not correct", seconds == 5);
	}

	@Test
	void testGetTotalMinutesGood() {
		int minutes = Time.getTotalMinutes("01:15:00");
		assertTrue("The minutes were not calculated properly",
				minutes == 15);
	}
	
	@Test 
	void testGetTotalMinutesBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {
					Time.getTotalMinutes("10");
				});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:05:00", "05:05:30", "05:05:59"})
	void testGetTotalMinutesBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The minutes value in not correct", minutes == 5);
	}

	@Test
	void testGetTotalHoursGood() {
		int hours = Time.getTotalHours("05:05:05");
		assertTrue("The hours value is not correct", hours == 5);
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {
					Time.getTotalHours("ab");
				});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:00:00", "05:30:00", "05:59:59"})
	void testGetTotalHoursBoundary(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours value is not correct", hours == 5);
	}
	
	

}
