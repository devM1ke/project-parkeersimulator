package runner;

import model.Model;

public class MainClass {
		
	public MainClass() {
		
	}

	public static void main(String[] args) {
		Model model = new Model();
		model.giveRef(model);
		model.run();
	}
}