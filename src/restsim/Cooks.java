package restsim;

public class Cooks {

	private Cook[] cooks;

	public Cooks(int num) {
		if (num < 1) {
			System.out.println("Invalid no. of cooks!");
		}
		cooks = new Cook[num];
		for (int i = 0; i < num; i++) {
			cooks[i] = new Cook(i);
		}
	}

	public void start() {
		for (int i = 0; i < cooks.length; i++) {
			new Thread(cooks[i]).start();
		}
	}

}
