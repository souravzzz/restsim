package restsim;

import java.util.Scanner;

public class Restaurant {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int nDiners = sc.nextInt();
		int nTables = sc.nextInt();
		int nCooks = sc.nextInt();

		Tables tables = new Tables(nTables);
		Cooks cooks = new Cooks(nCooks);
		Diners diners = new Diners(nDiners, tables, cooks);

		for (int i = 0; i < nDiners; i++) {
			int atime = sc.nextInt();
			int burgers = sc.nextInt();
			int fries = sc.nextInt();
			int coke = sc.nextInt();
			Order order = new Order(burgers, fries, coke);
			diners.add(i, atime, order);
		}

		sc.close();

		Clock.init();
		cooks.start();
		diners.start();
	}

}
