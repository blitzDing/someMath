package someMath;

import java.util.*;


public class Calculator 
{

	public static final char normalParenthesisOpeningChar = '(';
	public static final char normalParenthesisCloseingChar = ')';
	public static final char curlyParenthesisOpeningChar = '{';
	public static final char curlyParenthesisClosingChar = '}';
	public static final char bracketsOpeningChar = '[';
	public static final char bracketsClosingChar = ']';
	
	public static final char piChar = 'π';
	public static final char eChar = 'e';
	public static final char imaginUnitChar = 'i';
	public static final char goldenRatioChar = 'φ';
	public static final double goldenRatioValue = (1+Math.sqrt(5.0))/2.0;

	public static final char additionChar = '+';
	public static final char subthractionChar = '-';
	public static final char multiplicationChar = '*';
	public static final char divisionChar = '/';
	public static final char moduloChar = '%';
	public static final char exponentialChar = '^';
	
	public static final String greekSigmaPrefix = "Σ(";
	public static final String latinSigmaPrefix = "sigma(";
	public static final String greekPiPrefix = "Π(";
	public static final String latinPiPrefix = "PI(";
	
	public static final String sinPrefix = "sin(";
	public static final String cosPrefix = "cos(";
	public static final String tanPrefix = "tan(";
	public static final Set<String> trigonometricPrefixes = new HashSet<>(Arrays.asList(sinPrefix, cosPrefix, tanPrefix));

	public static final String logarithmNaturalisPrefix ="ln(";
	public static final String exponentialNaturalisPrefix = "exp(";
	
	final String mathExpressionPattern;
	final boolean isConcret;
	final boolean isPolynom;
	final boolean isTrigonometric;
	final boolean isExponential;
	final int nrOfVariables;
	
	public Calculator(String mathExpression)
	{
		if(!expressionIsWellformed(mathExpression))throw new IllegalArgumentException("Malformed Expression");
		
		this.mathExpressionPattern = mathExpression.trim();
		
		isConcret=extractVariables(mathExpression).isEmpty();
		isPolynom = expressionIsPolynomal(mathExpression);
		isTrigonometric = expressionIsTrigonometric(mathExpression);
		isExponential = expressionIsExponential(mathExpression);
		nrOfVariables = extractVariables(mathExpression).size();
	}
	
	public static boolean expressionIsWellformed(String mathExpression)
	{
		
		char opening = normalParenthesisOpeningChar;
		char closeing = normalParenthesisCloseingChar;
		
		if(!parenthesisIsOK(mathExpression, opening, closeing)) return false;
		
		return true;
	}
	
	public static Set<Character> extractVariables(String mathExpression)
	{
		Set<Character> variables = new HashSet<>();
		//TODO:
		return variables;
	}
	
	public static boolean expressionIsPolynomal(String mathExpression)
	{
		
		//TODO:
		return false;
	}
	
	public static boolean expressionIsTrigonometric(String mathExpression)
	{
		
		for(String trigonoPrefix: trigonometricPrefixes)
		{
			if(mathExpression.contains(trigonoPrefix)) return true;
		}
		
		return false;
	}
	
	public static boolean expressionIsExponential(String mathExpression)
	{
		
		if(mathExpression.contains(logarithmNaturalisPrefix))return true;
		if(mathExpression.contains(exponentialNaturalisPrefix))return true;
		//TODO: exponentChar needs to be followed 'concrete' Value.
		return false;
	}
	
	public static boolean parenthesisIsOK(String mathExpression, char opening, char closeing)
	{
		
		int nrOfOpenings = StringManipulation.countOccurrenceOfChar(mathExpression, opening);

		int nrOfCloseings = StringManipulation.countOccurrenceOfChar(mathExpression, closeing);
		
		if(nrOfOpenings!=nrOfCloseings) return false;
		int firstIndexOpening = mathExpression.indexOf(opening);
		int firstIndexCloseing = mathExpression.indexOf(closeing);

		if(firstIndexCloseing<firstIndexOpening)return false;
		
		
		int lastIndexOpening = mathExpression.lastIndexOf(opening);
		int lastIndexCloseing = mathExpression.lastIndexOf(closeing);
		
		if(lastIndexCloseing<lastIndexOpening)return false;
				
		return true;
	}
	
	public static Map<Integer, Integer> 
	isolateParenthesisContent(String mathExpression, char opening, char closeing)
	{
		Map<Integer, Integer> parenthesisContents = new HashMap<>();
		
		int firstIndexOfCloseing = mathExpression.indexOf(closeing);
		if(firstIndexOfCloseing==-1)return parenthesisContents;
		
		int indexOfCorrospondingOpening = 0;
		
		for(int n=firstIndexOfCloseing;n>0;n--)
		{
			char c = mathExpression.charAt(n);
			if(c==opening)
			{
				indexOfCorrospondingOpening = n;
				break;
			}
		}
		
		String parenthesisString = 
				mathExpression.substring(indexOfCorrospondingOpening, firstIndexOfCloseing+1);
		parenthesisContents.put(indexOfCorrospondingOpening, firstIndexOfCloseing+1);
		
		String filler = StringManipulation.customMonoRepeatChar(' ',parenthesisString.length());
		
		String smaller = mathExpression.replace(parenthesisString, filler);
	
		Map<Integer, Integer> map = isolateParenthesisContent(smaller, opening, closeing);
		parenthesisContents.putAll(map);
		
		
		return parenthesisContents;
	}
}