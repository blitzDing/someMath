

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import allgemein.ExactPeriode;
import allgemein.LittleTimeTools;
import someMath.InterfaceNumberException;

class ExactPeriodeTest
{

	@Test
	void epTest() throws InterfaceNumberException
	{
		
		LocalDateTime fromLDT = LocalDateTime.of(2023, 2, 27, 0, 0);//The funny February.
		LocalDateTime toLDT = fromLDT.plusMonths(2).plusDays(5).minusMinutes(2);
		
		ExactPeriode ep = new ExactPeriode(fromLDT, toLDT);
		System.out.println("Ep: " + ep);
		
		ExactPeriode epMinus = new ExactPeriode(toLDT, fromLDT);
		System.out.println("negative EP: " + epMinus);
		
		fromLDT = LocalDateTime.of(2020, 2, 27, 0, 0);//The funny February again.
		toLDT = fromLDT.plusMonths(2).plusDays(5).minusMinutes(2);
		ExactPeriode ep3 = new ExactPeriode(fromLDT, toLDT);
		System.out.println("Leap Year Ep: " + ep3);
		
		assert(Math.abs(ep.getAbsoluteDays()-ep3.getAbsoluteDays())==1);
	}

}
