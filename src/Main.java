import javax.swing.JFrame;

public class Main {
	/**
	 * Fő ciklus, lérethozza és megjeleníti a menüt.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake");
		frame.setSize(300, 400);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Menu q = new Menu();
		frame.add(q);
		frame.setVisible(true);
	}
}