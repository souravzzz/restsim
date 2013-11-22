package restsim;

import java.util.Scanner;

public class Restaurant {

	Tables tables;
	Cooks cooks;
	Diners diners;

	public void simulate() {

		Scanner sc = new Scanner(System.in);
		int nDiners = sc.nextInt();
		int nTables = sc.nextInt();
		int nCooks = sc.nextInt();

		tables = new Tables(nTables);
		cooks = new Cooks(nCooks);
		diners = new Diners(nDiners);

		Diner.restaurant = this;
		Cook.restaurant = this;

		for (int i = 0; i < nDiners; i++) {
			int atime = sc.nextInt();
			int burgers = sc.nextInt();
			int fries = sc.nextInt();
			int coke = sc.nextInt();
			Order order = new Order(burgers, fries, coke);
			diners.add(i, atime, order);
		}

		sc.close();

		cooks.start();
		Clock.init();
		diners.start();
	}

}
