package restsim;

public class Cook implements Runnable {

	int id;

	public Cook(int id) {
		this.id = id;
	}

	public void handleDiner(Diner diner) {
		prepareOrder(diner.order);
		serveFood(diner);
	}

	public void prepareOrder(Order order) {
		prepareFood(Food.BURGER, order.getNum(Food.BURGER));
		prepareFood(Food.FRIES, order.getNum(Food.FRIES));
		prepareFood(Food.COKE, order.getNum(Food.COKE));
	}

	private void prepareFood(Food type, int num) {
		Machine machine = Machine.get(type);
		for (int i = 0; i < num; i++) {
			machine.cookFood();
		}
	}

	private void serveFood(Diner diner) {
		diner.notify();
	}

	@Override
	public void run() {

	}

}
