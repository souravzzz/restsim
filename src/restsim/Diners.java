package restsim;

public class Diners {

	private int seated = 0;
	private int finished = 0;
	private Diner[] diners;

	public Diners(int num) {
		if (num < 1) {
			System.out.println("Invalid no. of diners!");
		}
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

	// RETURNS A SEATED DINER READY TO ORDER
	public synchronized Diner getDiner() {
		try {
			while (seated == 0) {
				wait();
			}
			for (int i = 0; i < diners.length; i++) {
				Diner diner = diners[i];
				if (diner.status.equals(Status.SEATED)) {
					seated--;
					diner.status = Status.ORDERED;
					return diner;
				}
			}
		} catch (InterruptedException e) {
		}
		return null;
	}

	// SERVE FOOD TO A DINER
	public synchronized void serveDiner(Diner diner) {
		synchronized (diner.order) {
			diner.order.ready = true;
			diner.status = Status.EATING;
			diner.order.notify();
		}
	}

	// IS SIMULATION COMPLETE?
	public synchronized boolean hasMore() {
		return finished < diners.length;
	}

	// DINER FINDS A SIT
	public synchronized void sitdown(Diner diner) {
		seated++;
		diner.status = Status.SEATED;
		notify();
	}

	// DINER LEAVES RESTAURANT
	public synchronized void leave(Diner diner) {
		finished++;
		if (finished == diners.length) {
			System.out.format("Last Diner %2d leaves restaurant at %d%n",
					diner.id, Clock.getTime());
			System.exit(0);
		}
	}
}
