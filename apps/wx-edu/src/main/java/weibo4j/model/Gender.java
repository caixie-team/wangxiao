package weibo4j.model;

/**
 * @author SinaWeibo
 * 
 */
public enum Gender {
	MALE, FEMALE;
	public static String valueOf(weibo4j.model.Gender gender) {
		int ordinal= gender.ordinal();
		if(ordinal==0)
			return "m";
		return "f";
	}
}
