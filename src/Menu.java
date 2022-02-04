import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Snake s;
	private Snake s2;
	private Food food;
	private Field field;
	private static String fieldType;
	private JButton singleplayerButton;
	private JButton multiplayerButton;
	private JButton resultsButton;
	private JButton settingsButton;
	private JButton exitButton;
	private JButton button;
	private Settings settingsFrame;
	private JFrame gameFrame;
	private JFrame resultsFrame;
	private MultiplayerGame g;
	private SingleplayerGame g2;
	private ResultsData data;
	
	/**
	 * A játék megnyitásakor beolvassa az eddig elért eredményeket.
	 * Beállítja az alapértelmezett módot,
	 * beállítja a szükséget objektumokat.
	 */
	@SuppressWarnings("unchecked")
	public Menu() {
		try {
            data = new ResultsData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("snakeResults.dat"));
            data.results = (ArrayList<Result>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
		fieldType = "no obstacle";
		setLayout(new GridLayout(5, 1));
		s = new Snake(new Coordinate(6, 1));
		s2 = new Snake(new Coordinate(6, 3));
		button = new JButton("");

		singleplayerButton = new JButton("Singleplayer game start");
		multiplayerButton = new JButton("Multiplayer game start");
		settingsButton = new JButton("Settings");
		exitButton = new JButton("Exit");
		resultsButton = new JButton("Results");

		add(singleplayerButton);
		add(multiplayerButton);
		add(resultsButton);
		add(settingsButton);
		add(exitButton);

		singleplayerButton.addActionListener(this);
		multiplayerButton.addActionListener(this);
		settingsButton.addActionListener(this);
		exitButton.addActionListener(this);
		resultsButton.addActionListener(this);

		Food.setColor(Color.YELLOW);
		Field.setBackgroundColor(Color.DARK_GRAY);
		Field.setObstaclesColor(Color.RED);
		
		button.addActionListener(this);
		button.setEnabled(false);

	}

	/**
	 * Ez felelős a navigáláshoz használt gombok elyesmélyeinek kezeléséért.
	 * felismeri hogy melyik gomot nyomták meg és a megfelelően jár el.
	 * 
	 *  singleplayerButton nyomásakor új egyjátékos játék indul.
	 *  multiplayerButton nyomásakor új játék indul két játékossal.
	 *  settingsButton megnyomásakor a játék parametereinek beállításához használt ablak jön létre.
	 *  resultsButton megnyomásakor a játékban elért eredmények jelennek meg.
	 *  button megnyomásakor visszatérünk a menübe,
	 *         illetve ha befejezett játékból térünk vissza akkor az eredményt is elmenti fájlba.
	 *  exitButton megnyomásakor bezáródik a program.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		food = new Food(new Coordinate(6, 6));
		
		if (e.getSource().equals(singleplayerButton)) {
			s.setToDefault(new Coordinate(6, 1));
			if (fieldType.equals("no obstacle")) {
				field = new Field(s, food);
			}
			if (fieldType.equals("one obstacle")) {
				field = new FieldOneObstacle(s, food);
			}
			if (fieldType.equals("two obstacles")) {
				field = new FieldTwoObstacles(s, food);
			}
			JLabel score1 = new JLabel("Score: 0");
			JLabel timeLabel = new JLabel("12:21");
			g2 = new SingleplayerGame(s, field, food, score1, timeLabel, button);
			
			gameFrame = new JFrame("Snake");
			gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			gameFrame.pack();
			score1.setFont(new Font("TimesRoman", Font.PLAIN, 34));
			timeLabel.setFont(new Font("TimesRoman", Font.PLAIN, 34));
			gameFrame.add(field, BorderLayout.CENTER);
			gameFrame.add(button, BorderLayout.SOUTH);
			gameFrame.add(timeLabel, BorderLayout.NORTH);
			gameFrame.add(score1, BorderLayout.EAST);
			gameFrame.add(field);
			gameFrame.setVisible(true);
			g2.start();
		}

		if (e.getSource().equals(multiplayerButton)) {
			s.setToDefault(new Coordinate(6, 1));
			s2.setToDefault(new Coordinate(6, 3));
			if (fieldType.equals("no obstacle")) {
				field = new Field(s, s2, food);
			}
			if (fieldType.equals("one obstacle")) {
				field = new FieldOneObstacle(s, s2, food);
			}
			if (fieldType.equals("two obstacles")) {
				field = new FieldTwoObstacles(s, s2, food);
			}
			JLabel score1 = new JLabel("Score: 0");
			JLabel score2 = new JLabel("Score: 0");
			JLabel timeLabel = new JLabel("");
			g = new MultiplayerGame(s, s2, field, food, score1, score2, timeLabel, button);
			g.start();
			gameFrame = new JFrame("Snake");
			gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			gameFrame.pack();
			score1.setFont(new Font("TimesRoman", Font.PLAIN, 34));
			score2.setFont(new Font("TimesRoman", Font.PLAIN, 34));
			timeLabel.setFont(new Font("TimesRoman", Font.PLAIN, 34));
			gameFrame.add(field, BorderLayout.CENTER);
			gameFrame.add(button, BorderLayout.SOUTH);
			gameFrame.add(timeLabel, BorderLayout.NORTH);
			gameFrame.add(score1, BorderLayout.EAST);
			gameFrame.add(score2, BorderLayout.WEST);
			gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			gameFrame.pack();
			gameFrame.setVisible(true);
		}

		if (e.getSource().equals(settingsButton)) {
			settingsFrame = new Settings(s, s2);
			settingsFrame.setBounds(0, 0, 300, 500);
			settingsFrame.setVisible(true);
		}

		if (e.getSource().equals(exitButton)) {
			System.exit(0);
		}
		
		if (e.getSource().equals(button)) {
			if(button.getText().equals("OK")==false) {
				gameFrame.dispose();
			
			
			if(button.getText().equals("Go to menu ")) {
				data.results.add(g.getResult());
			}else {
				data.results.add(g2.getResult());
			}
			}else {
				resultsFrame.dispose();
			}
			button.setText("");
			button.setEnabled(false);
			
			
			try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("snakeResults.dat"));
                oos.writeObject(data.results);
                oos.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
		}
		if (e.getSource().equals(resultsButton)) {
			
			resultsFrame = new JFrame("Snake results");
			resultsFrame.setSize(600, 400);
			resultsFrame.setVisible(true);
			
			this.setLayout(new BorderLayout());
	        JTable table = new JTable(data);
	        table.setFillsViewportHeight(true);
	        JScrollPane srollPane = new JScrollPane(table);
	        resultsFrame.add(button,BorderLayout.SOUTH);
	        button.setEnabled(true);
	        button.setText("OK");
	     
	        resultsFrame.add(srollPane,BorderLayout.CENTER);
			
		}
	}
	
	/**
	 * A pálya típusát Állítja be
	 * @param st A pálya típusa
	 */
	public static void setFieldType(String st) {
		fieldType = st;
	}

	/**
	 * A pálya típusát kéri le
	 * @return A pálya típusával tér vissza.
	 */
	public static String getFieldType() {
		return fieldType;
	}
}