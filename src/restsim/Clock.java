package restsim;

public class Clock {

	private static long start;

	public static void init() {
		start = System.currentTimeMillis();
	}

	public static long getTime() {
		return System.currentTimeMillis() - start;
	}

}
