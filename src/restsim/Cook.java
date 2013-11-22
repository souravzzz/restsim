package restsim;

public class Cook implements Runnable {

	int id;
	boolean busy;
	static Restaurant restaurant;

	public Cook(int id) {
		this.id = id;
	}

	public synchronized void placeOrder(Order order) {
		prepareOrder(order);
		serveOrder(order);
		restaurant.cooks.freeCook(this);
	}

	private void prepareOrder(Order order) {
		prepareFood(Food.BURGER, order.getNum(Food.BURGER));
		prepareFood(Food.FRIES, order.getNum(Food.FRIES));
		prepareFood(Food.COKE, order.getNum(Food.COKE));
	}

	private void prepareFood(Food type, int num) {
		Machine machine = Machine.get(type);
		machine.cookFood(id, num);
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
