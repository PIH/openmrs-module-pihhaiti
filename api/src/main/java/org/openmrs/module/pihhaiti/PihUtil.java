package org.openmrs.module.pihhaiti;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.openmrs.Concept;

public class PihUtil {
	    
    public static String getConceptKey(Concept c) { 	
    	return c.getName(Locale.ENGLISH).getName();  	
    }
    
    public static String toString(Concept c) {
    	if (c == null) {
    		return "";
    	}
    	return c.getName().getName();
    }
    
    public static String toString(Map<Object, Object> m, String sep) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<Object, Object> e : m.entrySet()) {
			if (sb.length() > 0) {
				sb.append(sep);
			}
		    sb.append(e.getKey() + " -> " + e.getValue());
		}
		return sb.toString();
    }
    
    public static String toString(String separator, Object...elements) {
    	StringBuffer sb = new StringBuffer();
    	for (Object o : elements) {
    		if (notNull(o)) {
    			if (sb.length() > 0) {
    				sb.append(separator);
    			}
    			sb.append(o.toString());
    		}
    	}
    	return sb.toString();
    }
    
    public static boolean isNull(Object o) {
    	return o == null || o.equals("") || o.equals("&nbsp;");
    }
    
    public static boolean notNull(Object o) {
    	return !isNull(o);
    }
    
    public static <T extends Object> T nvl(T o, T replacement) {
    	return (isNull(o) ? replacement : o);
    }
    
    public static String nvlStr(Object o, Object replacement) {
    	return (isNull(o) ? nvl(replacement, "").toString() : o.toString());
    }
    
    public static <T extends Object> T decode(Object testIfNull, T valueIfNull, T valueIfNotNull) {
    	return (isNull(testIfNull) ? valueIfNull : valueIfNotNull);
    }
    
    public static String decodeStr(Object testIfNull, Object valueIfNull, Object valueIfNotNull) {
    	return (isNull(testIfNull) ? nvlStr(valueIfNull, "") : nvlStr(valueIfNotNull, ""));
    }
    
    public static boolean areEqual(Object o1, Object o2) {
    	Object obj1 = nvl(o1, "");
    	Object obj2 = nvl(o2, "");
    	return obj1.equals(obj2);
    }
    
    public static boolean areEqualStr(Object o1, Object o2) {
    	String obj1 = nvlStr(o1, "");
    	String obj2 = nvlStr(o2, "");
    	return obj1.equals(obj2);
    }
    
    public static boolean equalToAny(Object o1, Object...o2) {
    	if (o1 != null && o2 != null) {
    		for (Object o : o2) {
    			if (o1.equals(o)) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public static <T extends Object> T coalesce(T...tests) {
    	if (tests != null) {
	    	for (int i=0; i<tests.length; i++) {
	    		if (notNull(tests[i])) {
	    			return tests[i];
	    		}
	    	}
    	}
    	return null;
    }
    
    public static boolean anyNotNull(Object...tests) {
    	return coalesce(tests) != null;
    }
    
    public static int numNotNull(Object...tests) {
    	int count=0;
    	if (tests != null) {
	    	for (int i=0; i<tests.length; i++) {
	    		if (notNull(tests[i])) {
	    			count++;
	    		}
	    	}
    	}
    	return count;
    }
    
	public static boolean containsAny(Collection<String> c, String...toCheck) {
		if (c != null) {
			for (String s : toCheck) {
				if (c.contains(s)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean containsAll(Collection<String> c, String...toCheck) {
		if (c != null) {
			for (String s : toCheck) {
				if (!c.contains(s)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static String toCamelCase(String s) {
		StringBuilder sb = new StringBuilder();
		boolean nextUpper = false;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				nextUpper = true;
			}
			else {
				if (Character.isLetterOrDigit(c)) {
					sb.append((nextUpper ? Character.toUpperCase(c) : Character.toLowerCase(c)));
					nextUpper = false;
				}
			}
		}
		return sb.toString();
	}
	
	public static int compareTo(Date d1, Date d2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String s1 = df.format(d1);
		String s2 = df.format(d2);
		return s1.compareTo(s2);
	}
}
