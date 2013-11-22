package restsim;

public class Restaurant {

	public static void main(String[] args) {
		Tables tables = new Tables(5);
		Cooks cooks = new Cooks(3);
		Diners diners = new Diners(10, tables, cooks);

		cooks.start();
		diners.start();
	}

}
