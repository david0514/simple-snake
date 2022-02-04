import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Settings extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Snake s;
	private Snake s2;
	private JComboBox<String> fieldTypeList;
	private JComboBox<String> snake1ColorList;
	private JComboBox<String> snake2ColorList;
	private JComboBox<String> foodColorList;
	private JComboBox<String> backgroundColorList;
	private JComboBox<String> obstacleColorList;
	private JComboBox<Integer> speedList;
	private JButton setButton;

	/**
	 * @param s 1-es kígyó
	 * @param s2 2-es kígyó
	 * beállítja az ablakot, és komponenseit.
	 */
	Settings(Snake s, Snake s2) {
		String[] colors = { "Green", "Blue", "Yellow", "Red", "Pink", "White", "Dark Gray", "Black" };
		String[] fieldTypes = { "no obstacle", "one obstacle", "two obstacles" };
		Integer[] speeds = { 125, 250, 500,750,1000 };
		this.s = s;
		this.s2 = s2;

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new GridLayout(8, 2));
		JLabel fieldSetLabel = new JLabel("Field type:");
		JLabel snake1SetLabel = new JLabel("1. snake color:");
		JLabel snake2SetLabel = new JLabel("2. snake color:");
		JLabel foodSetLabel = new JLabel("Food color:");
		JLabel backgroundSetLabel = new JLabel("Backgound color:");
		JLabel obstacleSetLabel = new JLabel("Obstacles color:");
		JLabel speedSetLabel = new JLabel("Speed (ms/step):");

		fieldTypeList = new JComboBox<String>(fieldTypes);
		fieldTypeList.setSelectedItem(Menu.getFieldType());

		snake1ColorList = new JComboBox<String>(colors);
		snake1ColorList.setSelectedItem(colorToString(s.getColor()));

		snake2ColorList = new JComboBox<String>(colors);
		snake2ColorList.setSelectedItem(colorToString(s2.getColor()));

		foodColorList = new JComboBox<String>(colors);
		foodColorList.setSelectedItem(colorToString(Food.getColor()));

		backgroundColorList = new JComboBox<String>(colors);
		backgroundColorList.setSelectedItem(colorToString(Field.getBackgroundColor()));

		obstacleColorList = new JComboBox<String>(colors);
		obstacleColorList.setSelectedItem(colorToString(Field.getObstaclesColor()));
		
		speedList = new JComboBox<Integer>(speeds);
		speedList.setSelectedItem(Snake.getSpeed());

		setButton = new JButton("Set");
		setButton.addActionListener(this);

		add(fieldSetLabel);
		add(fieldTypeList);
		
		add(speedSetLabel);
		add(speedList);

		add(backgroundSetLabel);
		add(backgroundColorList);

		add(obstacleSetLabel);
		add(obstacleColorList);

		add(snake1SetLabel);
		add(snake1ColorList);

		add(snake2SetLabel);
		add(snake2ColorList);

		add(foodSetLabel);
		add(foodColorList);

		add(setButton);

	}

	/**
	 * átkonvertálja a Stringet Color-á.
	 * @param s Szín (String-ként)
	 * @return Szín (Color-ként)
	 */
	public Color stringToColor(String s) {
		switch (s) {
		case "Green":
			return Color.GREEN;
		case "Blue":
			return Color.BLUE;
		case "Yellow":
			return Color.YELLOW;
		case "Red":
			return Color.RED;
		case "Pink":
			return Color.PINK;
		case "White":
			return Color.WHITE;
		case "Dark Gray":
			return Color.DARK_GRAY;
		case "Black":
			return Color.BLACK;

		default:

			throw new IllegalArgumentException("Unexpected value: " + s);
		}
	}

	/**
	 * Átkonvertálja a Color-t String-é.
	 * @param s Szín (Color-ként)
	 * @return s Szín (String-ként)
	 */
	public String colorToString(Color s) {
		if (s.equals(Color.GREEN)) {
			return "Green";
		}
		if (s.equals(Color.BLUE)) {
			return "Blue";
		}
		if (s.equals(Color.YELLOW)) {
			return "Yellow";
		}
		if (s.equals(Color.RED)) {
			return "Red";
		}
		if (s.equals(Color.PINK)) {
			return "Pink";
		}
		if (s.equals(Color.WHITE)) {
			return "White";
		}
		if (s.equals(Color.DARK_GRAY)) {
			return "Dark Gray";
		}
		if (s.equals(Color.BLACK)) {
			return "Black";
		}
		return "White";

	}

	/**
	 * Elmenti a beállításokat, és visszatér a menübe.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(setButton)) {
			String sc = (String) snake1ColorList.getSelectedItem();
			s.setColor(stringToColor(sc));
			sc = (String) snake2ColorList.getSelectedItem();
			s2.setColor(stringToColor(sc));
			sc = (String) foodColorList.getSelectedItem();
			Food.setColor(stringToColor(sc));
			sc = (String) backgroundColorList.getSelectedItem();
			Field.setBackgroundColor(stringToColor(sc));
			sc = (String) obstacleColorList.getSelectedItem();
			Field.setObstaclesColor(stringToColor(sc));
			sc = (String) fieldTypeList.getSelectedItem();
			Menu.setFieldType(sc);
			Integer i= (Integer) speedList.getSelectedItem();
			Snake.setSpeed(i);
		}
		dispose();
	}
}