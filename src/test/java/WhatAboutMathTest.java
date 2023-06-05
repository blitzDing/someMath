import org.junit.jupiter.api.Test;

public class WhatAboutMathTest
{
	
	@Test
	public void testMath()
	{
		System.out.println("***************************");
		double angle = Math.toDegrees(-0.8);
		double angle2 = Math.toDegrees(0.8);
		System.out.println("cos("+angle+"):"+Math.cos(-0.8));
		System.out.println("sin("+angle+"):"+Math.sin(-0.8));
		System.out.println("cos("+angle2+"):"+Math.cos(0.8));
		System.out.println("sin("+angle2+"):"+Math.sin(0.8));
		System.out.println("***************************");
	}
}
