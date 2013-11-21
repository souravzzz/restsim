package restsim;

public class Order {

	private int items[] = new int[3];
	private Diner diner;

	public Order(Diner diner, int burgers, int fries, int coke) {
		if (isOrderValid(burgers, fries, coke)) {
			System.out.println("Invalid order!");
			System.exit(-1);
		}
		this.diner = diner;
		items[0] = burgers;
		items[1] = fries;
		items[2] = coke;
	}

	public int getNum(Food type) {
		switch (type) {
		case BURGER:
			return items[0];
		case FRIES:
			return items[1];
		case COKE:
			return items[2];
		default:
			System.out.println("Invalid food type");
			return -1;
		}
	}

	public Diner getDiner() {
		return diner;
	}

	private boolean isOrderValid(int burgers, int fries, int coke) {
		if (burgers < 1)
			return false;
		if (fries < 0)
			return false;
		if (coke < 0 || coke > 1)
			return false;
		return true;
	}

}
