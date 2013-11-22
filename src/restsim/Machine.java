package restsim;

public enum Machine {
	BURGERMC(5 * 60), FRIESMC(3 * 60), COKEMC(1 * 60);

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
			System.out.println("Cook " + cookId + " started " + this + " at "
					+ Clock.getTime());
			busy = true;
			Thread.sleep(cookTime);
			busy = false;
			notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case BURGERMC:
			return "Burger machine";
		case FRIESMC:
			return "Fries machine ";
		case COKEMC:
			return "Cokes machine ";
		default:
			System.out.println("Invalid machine type");
			return null;
		}
	}
}
