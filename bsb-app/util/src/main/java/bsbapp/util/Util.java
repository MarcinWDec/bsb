package bsbapp.util;

public class Util {
	
	public static boolean isBlank(String str){
		return (str==null || "".equals(str.trim()));
	}
	
	public static boolean isNumeric(String str){
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
