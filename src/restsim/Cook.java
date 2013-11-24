package restsim;

public class Cook implements Runnable {

	int id;
	static Restaurant restaurant;

	public Cook(int id) {
		this.id = id;
	}

	// FIND MACHINES AND COOK FOOD
	private void prepare(Order order) {
		prepareFood(Food.BURGER, order.getNum(Food.BURGER));
		prepareFood(Food.FRIES, order.getNum(Food.FRIES));
		prepareFood(Food.COKE, order.getNum(Food.COKE));
	}

	private void prepareFood(Food type, int num) {
		Machine machine = Machine.get(type);
		machine.cookFood(id, num);
	}

	// SERVE FOOD TO DINER
	private void serve(Diner diner) {
		System.out.format("Cook %-2d served food to Diner %-2d at %d%n", id,
				diner.id, Clock.getTime());
		restaurant.diners.serveDiner(diner);
	}

	@Override
	public void run() {
		while (restaurant.diners.hasMore()) {
			// WAIT FOR NEW DINER
			Diner diner = restaurant.diners.getDiner();
			if (diner != null) {
				System.out.format(
						"Cook %-2d got order from Diner %-2d at %d%n", id,
						diner.id, Clock.getTime());
				prepare(diner.order);
				serve(diner);
			}
		}
	}

}
