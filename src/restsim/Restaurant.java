package restsim;

public class Restaurant {

	public static void main(String[] args) {
		Cook[] cooks = new Cook[3];
		Table[] tables = new Table[5];
		Diner[] diners = new Diner[10];

		for (int i = 0; i < cooks.length; i++) {
			cooks[i] = new Cook(i);
			new Thread(cooks[i]).start();
		}
		for (int i = 0; i < tables.length; i++) {
			tables[i] = new Table(i);
		}
		for (int i = 0; i < diners.length; i++) {
			Order order = new Order((i % 2) + 1, i % 3, i % 2);
			diners[i] = new Diner(i, order);
			new Thread(diners[i]).start();
		}

	}

}
