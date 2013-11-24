package restsim;

enum Status {
	OUTSIDE, SEATED, ORDERED, EATING, FINISHED
}

public class Diner implements Runnable {

	int id;
	Order order;
	int atime;
	Status status;
	static Restaurant restaurant;

	public Diner(int id, int atime, Order order) {
		this.id = id;
		this.atime = atime;
		this.order = order;
		this.status = Status.OUTSIDE;
	}

	@Override
	public void run() {
		try {
			Clock.sleep(atime);

			// FIND TABLE
			Table table = restaurant.tables.getTable();
			restaurant.diners.sitdown(this);
			System.out.format("Diner %-2d was seated at Table %-2d at %d%n",
					id, table.id, Clock.getTime());

			// WAIT FOR FOOD
			synchronized (order) {
				while (!order.ready) {
					order.wait();
				}
			}
			// GOT FOOD

			// START EATING
			Clock.sleep(30);

			// LEAVE TABLE
			System.out.format("Diner %-2d finished left Table %-2d at %d%n",
					id, table.id, Clock.getTime());
			restaurant.tables.freeTable(table);

			// LEAVE RESTAURANT
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
