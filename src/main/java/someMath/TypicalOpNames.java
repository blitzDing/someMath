package someMath;

import java.util.HashSet;
import java.util.Set;

public enum TypicalOpNames
{

	add("Add"),
	multiply("multiply"),
	minus("minus"),
	div("divide"),
	pow("pow"),
	root("root"),
	log("log");

	private String fullName;
	private final Set<String> names = new HashSet<>();
	
	TypicalOpNames(String fullName)
	{
		this.fullName = fullName;
		names.add(fullName);
	}
	
	public String getFullName()
	{
		return fullName;
	}
	
	public boolean containsName(String fullName)
	{
		return names.contains(fullName);
	}
}
