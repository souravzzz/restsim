package restsim;

public class Diners {

	private int finished;
	private Diner[] diners;

	public Diners(int num) {
		finished = 0;
		diners = new Diner[num];
	}

	public void add(int id, int atime, Order order) {
		diners[id] = new Diner(id, atime, order);
	}

	public void start() {
		for (int i = 0; i < diners.length; i++) {
			new Thread(diners[i]).start();
		}
	}

	public synchronized boolean hasMore() {
		return finished < diners.length;
	}

	public synchronized void leave(Diner diner) {
		finished++;
		if (finished == diners.length) {
			System.out.format("Last Diner %2d leaves restaurant at %d",
					diner.id, Clock.getTime());
			System.exit(0);
		}
	}
}
