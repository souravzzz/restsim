package restsim;

public class Cook implements Runnable {

	int id;
	boolean busy;
	Cooks cooks;

	public Cook(int id, Cooks cooks) {
		this.id = id;
		this.cooks = cooks;
	}

	public synchronized void placeOrder(Order order) {
		prepareOrder(order);
		serveOrder(order);
		cooks.freeCook(this);
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

	private void serveOrder(Order order) {
		synchronized (order) {
			order.ready = true;
			order.notify();
		}
	}

	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}

}
