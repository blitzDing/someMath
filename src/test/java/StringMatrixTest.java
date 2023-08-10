import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import consoleTools.TerminalTableDisplay;



class StringMatrixTest 
{


	@Test
	void anotherTest()
	{
		
		List<String> headers = Arrays.asList("Name", "Status", "Time");
		
		List<String> K = Arrays.asList("Robocop", "Vintage", "80's");

		List<String> M = Arrays.asList("Terminator",  "mild Cult in times of Dispair there is air", "This decade.");

		List<String> L = Arrays.asList("Wall-E", "cuteButRude", "2010's");
		
		List<List<String>> values = Arrays.asList(K, M, L);
		
        TerminalTableDisplay show = new TerminalTableDisplay(headers, values, '|',12);
        System.out.println(show);
        
        assert(true);
	}
}
