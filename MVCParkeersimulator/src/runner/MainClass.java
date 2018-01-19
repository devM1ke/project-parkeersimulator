package runner;

import controller.Simulator;

public class MainClass {
		
	public MainClass() {
		
	}

	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		simulator.giveRef(simulator);
		simulator.run();
	}
}