package restsim;

public class Diners {

	private int finished;
	private Diner[] diners;
	private Tables tables;
	private Cooks cooks;

	public Diners(int num, Tables tables, Cooks cooks) {
		finished = 0;
		diners = new Diner[num];
		this.tables = tables;
		this.cooks = cooks;
	}

	public void add(int id, int atime, Order order) {
		diners[id] = new Diner(id, atime, order, tables, cooks, this);
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
					+ " left restaurant  at " + Clock.getTime());
			System.exit(0);
		}
	}
}
