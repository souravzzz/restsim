package restsim;

public class Restaurant {

	Tables tables;
	Diners diners;
	Cooks cooks;

	public static void main(String[] args) {
		Tables tables = new Tables(5);
		Diners diners = new Diners(10, tables);
		Cooks cooks = new Cooks(3, diners);

		cooks.start();
		diners.start();
	}

}
