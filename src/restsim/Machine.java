package restsim;

public class Machine {

	Food foodType;
	int cookTime;
	boolean busy;

	public Machine(Food type) {
		foodType = type;
		switch (foodType) {
		case BURGER:
			cookTime = 5 * 60;
			break;
		case FRIES:
			cookTime = 3 * 60;
			break;
		case COKE:
			cookTime = 1 * 60;
			break;
		}
	}

	public synchronized void prepareFood() {
		busy = true;
		// wait
		busy = false;
	}

	public synchronized boolean isBusy() {
		return busy;
	}

}
