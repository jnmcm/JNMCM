package util;

public class ClassNameCleaner {
	
//	puts a space before each UPPERCASE excluding the first letter
    public String cleanName(Class c) {
    	String text = c.getSimpleName();
    	String cleanText = text.replaceAll("(.)([A-Z0-9]\\w)", "$1 $2");
    	return cleanText;
    }    
}
