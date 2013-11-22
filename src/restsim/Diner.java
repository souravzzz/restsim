package restsim;

enum Status {
	WAITING, SEATED, ORDERED, EATING, FINISHED
};

public class Diner implements Runnable {

	int id;
	Status status;
	Order order;
	Tables tables;
	Diners diners;

	public Diner(int id, Order order, Tables tables, Diners diners) {
		this.id = id;
		this.status = Status.WAITING;
		this.order = order;
		this.tables = tables;
		this.diners = diners;
	}

	@Override
	public void run() {
		try {
			Table table = tables.getTable();
			diners.sitDiner(this);
			System.out.println("Diner " + id + " was seated at table "
					+ table.id + " at ");
			while (!status.equals(Status.EATING)) {
				Thread.yield();
			}
			System.out.println("Diner " + id + " was served at ");
			Thread.sleep(30);
			tables.freeTable(table);
			diners.finishDiner(this);
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
