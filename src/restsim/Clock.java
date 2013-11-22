package restsim;

public class Clock {

	public static final long factor = 10; // 1 minute = factor millisecond
	private static long start;

	public static void init() {
		start = System.currentTimeMillis();
	}

	public static long getTime() {
		return (System.currentTimeMillis() - start) / factor;
	}

}
