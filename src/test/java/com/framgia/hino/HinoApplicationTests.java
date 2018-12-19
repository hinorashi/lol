package com.framgia.hino;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HinoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("Ahihi");
		assertTrue("You noob!", true);
	}

}
