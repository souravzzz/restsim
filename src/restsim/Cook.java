package restsim;

public class Cook implements Runnable {

	int id;
	boolean busy;
	Cooks cooks;
	Diners diners;

	public Cook(int id, Cooks cooks, Diners diners) {
		this.id = id;
		this.cooks = cooks;
		this.diners = diners;
	}

	public synchronized void handleDiner(Diner diner) {
		busy = true;
		System.out.println("Cook " + id + " took order from Diner " + diner.id
				+ " at ");
		prepareOrder(diner.order);
		serveFood(diner);
		busy = false;
	}

	private void prepareOrder(Order order) {
		prepareFood(Food.BURGER, order.getNum(Food.BURGER));
		prepareFood(Food.FRIES, order.getNum(Food.FRIES));
		prepareFood(Food.COKE, order.getNum(Food.COKE));
	}

	private void prepareFood(Food type, int num) {
		Machine machine = Machine.get(type);
		for (int i = 0; i < num; i++) {
			machine.cookFood(id);
		}
	}

	private void serveFood(Diner diner) {
		diners.serveDiner(diner);
	}

	@Override
	public void run() {
		while (diners.hasMore()) {
			Diner diner = diners.getDiner();
			handleDiner(diner);
		}
	}

}
