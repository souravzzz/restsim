package restsim;

public class Clock {

	static long start;

	// static int tick;
	//
	// public static synchronized int getTime() {
	// return tick++;
	// }
	//
	// public static synchronized int updateTime(int oldTime, int increment) {
	// if (tick < oldTime + increment) {
	// tick = oldTime + increment;
	// }
	// return tick;
	// }

	public static void init() {
		start = System.currentTimeMillis();
	}

	public static long getRawTime() {
		return System.currentTimeMillis() - start;
	}

	public static String getTime() {
		long time = getRawTime();
		long mm = time / 60;
		long ss = time % 60;
		return String.format("%02d:%02d", mm, ss);
	}
}
