package kr.co.kmarket2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Kmarket2ApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Test
	void getCalendar() {
		System.out.println(System.currentTimeMillis()); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		String currentTimeMillis = sdf.format(System.currentTimeMillis());
		System.out.println(currentTimeMillis);
	}

}
