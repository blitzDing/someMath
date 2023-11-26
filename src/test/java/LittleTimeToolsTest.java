

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import allgemein.ExactPeriode;
import someMath.InterfaceNumberException;

class LittleTimeToolsTest {

	@Test
	void test() throws InterfaceNumberException
	{
		
		LocalDateTime fromLDT = LocalDateTime.now();
		LocalDateTime toLDT = fromLDT.plusHours(1);
		
		ExactPeriode ep = new ExactPeriode(fromLDT, toLDT);
		System.out.println(ep);
		
		/*
		ExactPeriode np2 = ExactPeriode.ofYears(-1);
		System.out.println(np2);
		*/
	}

}
