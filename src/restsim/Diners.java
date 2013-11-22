package restsim;

public class Diners {

	private int finished;
	private Diner[] diners;

	public Diners(int num, Tables tables, Cooks cooks) {
		finished = 0;
		diners = new Diner[num];
		for (int i = 0; i < num; i++) {
			Order order = new Order((i % 2) + 1, i % 3, i % 2);
			diners[i] = new Diner(i, order, tables, cooks, this);
		}
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
			System.out.println("Last Diner " + diner.id
					+ " left restaurant at ");
			System.exit(0);
		}
	}
}
