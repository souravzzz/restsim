package restsim;

public enum Machine {
	BURGERMC(5), FRIESMC(3), COKEMC(1);

	private int cookTime;
	private boolean busy;

	Machine(int time) {
		cookTime = time;
		busy = false;
	}

	public static Machine get(Food type) {
		switch (type) {
		case BURGER:
			return BURGERMC;
		case FRIES:
			return FRIESMC;
		case COKE:
			return COKEMC;
		default:
			System.out.println("Invalid food type");
			return null;
		}
	}

	public synchronized void cookFood(int cookId) {
		try {
			while (busy) {
				wait();
			}
			System.out.println("Cook " + cookId + " started " + this + " at ");
			busy = true;
			Thread.sleep(cookTime);
			busy = false;
			notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
