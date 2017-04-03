package com.bjagud;

import org.junit.*;

public class StatsTest {

	@Test
	public void firstTest() {
		
		String expected = "Hello!";
		String res = App.hello();
		Assert.assertEquals(expected, res);
	}
}
