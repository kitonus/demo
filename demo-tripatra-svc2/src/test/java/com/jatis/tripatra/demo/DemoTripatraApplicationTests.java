package com.jatis.tripatra.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTripatraApplicationTests {

	@Test
	//correct method (not one line)
	public void test() {
		int x = 3;
		
		//correct: if without braces
		if (x == 3)
			System.out.print("Three");
		
		String[] names = new String[] {"Budi", "Wati"};
		//incorrect: confusing loop without braces
		for (String name : names) 
			System.out.println("inside loop");
			System.out.println("outside loop");//this may fool programmer's eyes
		
	}
	
	//one line method
	public void test2() { System.out.println("one line test2"); }

}
