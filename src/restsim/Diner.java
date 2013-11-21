package restsim;

enum Status {
	WAITING, SEATED, EATING, FINISHED
};

public class Diner implements Runnable {

	int id;
	Status status;
	Order order;

	public Diner(int id, Order order) {
		this.id = id;
		this.status = Status.WAITING;
		this.order = order;
	}

	public Order getOrder() {
		if (!status.equals(Status.SEATED)) {
			System.out.println("Invalid state of diner");
			System.exit(-1);
		}
		return order;
	}

	@Override
	public void run() {
		try {
			// wait for free table
			status = Status.SEATED;
			System.out.println("Diner " + id + "was seated at tabe xxx at ");
			// wait to be served
			status = Status.EATING;
			Thread.sleep(30 * 60);
			status = Status.FINISHED;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
