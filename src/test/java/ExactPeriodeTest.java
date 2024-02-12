

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import allgemein.ExactPeriode;
import allgemein.LittleTimeTools;
import someMath.NaturalNumberException;
import someMath.NaturalNumber;
import static someMath.NaturalNumber.*;

class ExactPeriodeTest
{

	@Test
	void epTest() throws NaturalNumberException
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
		
		ExactPeriode ep4 = ep3.minusDays(new NaturalNumber(5)).plusMinutes(new NaturalNumber(2));
		System.out.println("fromLDT         : " + LittleTimeTools.timeString(fromLDT));
		System.out.println("Orignal toLDT:    " + LittleTimeTools.timeString(toLDT));
		System.out.println("New ExactPeriode: " + ep4);
		
		assert(ep4.getYears().equals(zero));
		assert(ep4.getMonths().equals(new NaturalNumber(2)));
		assert(ep4.getDays().equals(zero));
		assert(ep4.getHours().equals(zero));
		assert(ep4.getMinutes().equals(zero));
		assert(ep4.getSeconds().equals(zero));

	}
}
