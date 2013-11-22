package restsim;

public class Order {

	boolean ready;
	private int items[] = new int[3];

	public Order(int burgers, int fries, int coke) {
		if (!isOrderValid(burgers, fries, coke)) {
			System.out.println("Invalid order!");
			System.exit(-1);
		}
		items[0] = burgers;
		items[1] = fries;
		items[2] = coke;
		ready = false;
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

	@Override
	public String toString() {
		return ("Burgers: " + items[0] + ", Fries: " + items[1] + ", Coke: " + items[2]);
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
