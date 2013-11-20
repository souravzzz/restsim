package restsim;

public class Order {

	int items[] = new int[3];

	public Order(int burgers, int fries, int coke) {
		if (isOrderValid(burgers, fries, coke)) {
			items[0] = burgers;
			items[1] = fries;
			items[2] = coke;
		} else {
			System.out.println("Invalid order!");
		}
	}

	boolean isOrderValid(int burgers, int fries, int coke) {
		if (burgers < 1)
			return false;
		if (fries < 0)
			return false;
		if (coke < 0 || coke > 1)
			return false;
		return true;
	}

}
