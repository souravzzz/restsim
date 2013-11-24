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
}
