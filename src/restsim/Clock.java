package restsim;

public class Clock {

	static long start;

	public static void init() {
		start = System.currentTimeMillis();
	}

	public static long getRawTime() {
		return System.currentTimeMillis() - start;
	}

	public static String getTime() {
		long mm = getRawTime();
		return String.format("%02d", mm);
	}
}
