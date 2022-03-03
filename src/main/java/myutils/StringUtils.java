package myutils;

public class StringUtils {

	public static String getString(String input, String defaultString) {
		return input == null ? defaultString : input;
	}

	public static String getString(String input) {
		return input == null ? "" : input;
	}

}
