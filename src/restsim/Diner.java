package restsim;

public class Diner implements Runnable {

	int id;
	Order order;
	int atime;
	static Restaurant restaurant;

	public Diner(int id, int atime, Order order) {
		this.id = id;
		this.atime = atime;
		this.order = order;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(atime);

			// FIND TABLE
			Table table = restaurant.tables.getTable();
			System.out.format("Diner %2d was seated at Table %2d at %d%n", id,
					table.id, Clock.getTime());

			// FIND COOK
			Cook cook = restaurant.cooks.getCook();
			System.out.format("Cook %2d got order from Diner %2d at %d%n",
					cook.id, id, Clock.getTime());
			cook.placeOrder(order);

			// WAIT FOR FOOD
			synchronized (order) {
				while (!order.ready) {
					order.wait();
				}
			}

			// GOT FOOD
			System.out.format("Cook %2d served food to Diner %2d at %d%n",
					cook.id, id, Clock.getTime());
			restaurant.cooks.freeCook(cook);

			// START EATING
			Thread.sleep(30);

			// LEAVE
			System.out.format("Diner %2d finished left Table %2d at %d%n", id,
					table.id, Clock.getTime());
			restaurant.tables.freeTable(table);
			restaurant.diners.leave(this);
		} catch (InterruptedException e) {
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
