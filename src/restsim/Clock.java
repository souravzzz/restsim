package restsim;

public class Clock {

	private static long time = 0;

	public static long getTime() {
		return time;
	}

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
			Thread.sleep(10); // 1 minute = 10 millisecond
		} catch (InterruptedException e) {
		}
		time++;
		synchronized (Clock.class) {
			Clock.class.notifyAll();
		}
	}
}
