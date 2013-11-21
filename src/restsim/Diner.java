package restsim;

enum Status {
	WAITING, SEATED, EATING, FINISHED
};

public class Diner implements Runnable {

	Status status;
	int id;

	public Diner(int id) {
		this.id = id;
		status = Status.WAITING;
	}

	public Order getOrder() {
		if (!status.equals(Status.SEATED)) {
			System.out.println("Invalid state of diner");
			System.exit(-1);
		}
		return new Order(this, 3, 2, 1);
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
