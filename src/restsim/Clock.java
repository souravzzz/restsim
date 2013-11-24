package restsim;

public class Clock {

	private static long time = 0;

	// RETURN CURRENT TIME
	public static long getTime() {
		return time;
	}

	// USED INSTEAD OF Thread.sleep()
	public static void sleep(int minutes) {
		long end = time + minutes;
		while (getTime() < end) {
			try {
				synchronized (Clock.class) {
					Clock.class.wait();
				}
			} catch (InterruptedException e) {
			}
		}
	}

	public static void tick() {
		try {
			Thread.sleep(20); // 1 minute = 20 millisecond
		} catch (InterruptedException e) {
		}
		time++;
		synchronized (Clock.class) {
			Clock.class.notifyAll();
		}
	}
}
