package restsim;

public class Diner implements Runnable {

	int id;
	Order order;
	int atime;
	private Tables tables;
	private Cooks cooks;
	private Diners diners;

	public Diner(int id, int atime, Order order, Tables tables, Cooks cooks,
			Diners diners) {
		this.id = id;
		this.atime = atime;
		this.order = order;
		this.tables = tables;
		this.cooks = cooks;
		this.diners = diners;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(atime);

			// FIND TABLE
			Table table = tables.getTable();
			System.out.println("Diner " + id + " was seated at table "
					+ table.id + " at " + Clock.getTime());

			// FIND COOK
			Cook cook = cooks.getCook();
			System.out.println("Cook " + cook.id + " got order from diner "
					+ id + " at " + Clock.getTime());
			cook.placeOrder(order);

			// WAIT FOR FOOD
			synchronized (order) {
				while (!order.ready) {
					order.wait();
				}
			}

			// GOT FOOD
			System.out.println("Cook " + cook.id + " served food to Diner "
					+ id + " at " + Clock.getTime());
			cooks.freeCook(cook);

			// START EATING
			Thread.sleep(30 * 60);

			// LEAVE
			tables.freeTable(table);
			diners.leave(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diner other = (Diner) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
