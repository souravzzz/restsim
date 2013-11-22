package restsim;

public class Tables {

	private int free;
	private Table[] tables;

	public Tables(int num) {
		if (num < 1) {
			System.out.println("Invalid no. of tables!");
		}
		tables = new Table[num];
		for (int i = 0; i < num; i++) {
			tables[i] = new Table(i);
		}
		free = num;
	}

	public synchronized Table getTable() {
		try {
			while (free == 0) {
				wait();
			}
			for (int i = 0; i < tables.length; i++) {
				Table table = tables[i];
				if (!table.busy) {
					free--;
					table.busy = true;
					return table;
				}
			}
		} catch (InterruptedException e) {
		}
		return null;
	}

	public synchronized void freeTable(Table table) {
		free++;
		table.busy = false;
		notify();
	}
}
