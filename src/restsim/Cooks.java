package restsim;

public class Cooks {

	int free;
	Cook[] cooks;

	public Cooks(int num) {
		free = num;
		cooks = new Cook[num];
		for (int i = 0; i < num; i++) {
			cooks[i] = new Cook(i, this);
		}
	}

	public void start() {
		for (int i = 0; i < cooks.length; i++) {
			new Thread(cooks[i]).start();
		}
	}

	public synchronized Cook getCook() {
		try {
			while (free == 0) {
				wait();
			}
			for (int i = 0; i < cooks.length; i++) {
				Cook cook = cooks[i];
				if (!cook.busy) {
					free--;
					cook.busy = true;
					return cook;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void freeCook(Cook cook) {
		free++;
		cook.busy = false;
		notify();
	}

}
