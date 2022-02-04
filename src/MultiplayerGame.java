import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * A kétjátékos mód játékmenetéért felelős
 */
public class MultiplayerGame extends Thread {
	private Random r;
	private Food food;
	private Snake s;
	private Snake s2;
	private Field f;
	private JLabel score1;
	private JLabel score2;
	private JButton button;
	private JLabel timeLabel;
	private Integer sc1, sc2, gameTime;

	/**
	 * @param s A pályán lévő 1-es kígyó
	 * @param s2 A pályán lévő 2-es kígyó
	 * @param f A pálya
	 * @param food Az étel
	 * @param score1 Az 1-es játékos pontszám Label-je
	 * @param score2 A 2-es játékos pontszám Label-je
	 * @param timeLabel Játékidő Label-je
	 * @param b A gomb amivel vissza lehet térni a menübe ha elvesztettük a játékot.(el is menti az eredményt egy fájlba)
	 */
	public MultiplayerGame(Snake s, Snake s2, Field f, Food food, JLabel score1, JLabel score2, JLabel timeLabel,
			JButton b) {
		this.s = s;
		this.s2 = s2;
		this.f = f;
		this.food = food;
		this.score1 = score1;
		this.score2 = score2;
		this.timeLabel = timeLabel;
		this.sc1 = 0;
		this.sc2 = 0;
		this.button = b;
		this.r = new Random();
		gameTime=0;;
	}

	/**
	 *  Új ételt generál, véletlenszerű de szabad helyre.
	 */
	public void addFood() {
		Boolean freePlace = false;
		Coordinate newCoord = new Coordinate();
		while (freePlace == false) {
			newCoord.set(r.nextInt(19), r.nextInt(19));
			freePlace = f.isFree(newCoord);
		}
		food = new Food(newCoord);
	}

	/**
	 * A játék menete.
	 * Először visszaszámolás történik majd megindulnak a kígyók. Folyamatosan frissül az eltelt idő és a pontszámok,
	 * illetve a kép is fél másodpercenként frissűl (A kígyó enyi idő alatt lép egyet)
	 * A billentyűhhel módosíthatjuk a directiont. Azonban lépésenként csak egyszer érzékeny a módosításokra.
	 * Ha megeszik a kígyó egy ételt, Új genegálódik és a kígyóhoz hozzáadódik egy egység.
	 * ha vége a vissza a menube gomb aktívvá válik. 
	 */
	public void run() {
		try {
			timeLabel.setText("Starts in 3 seconds");
			Thread.sleep(1000);
			timeLabel.setText("Starts in 2 seconds");
			Thread.sleep(1000);
			timeLabel.setText("Starts in 1 seconds");
			Thread.sleep(800);
			timeLabel.setText("Start!");
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalTime now1 = LocalTime.now(zone1);
		Integer timeStart = now1.getSecond() + now1.getMinute() * 60 + now1.getHour() * 3600;
		Integer timeNow=0;
		

		while (f.collision() == false) {
			try {
				now1 = LocalTime.now(zone1);
				timeNow = now1.getSecond() + now1.getMinute() * 60 + now1.getHour() * 3600;
				gameTime=timeNow - timeStart;
				timeLabel.setText("Game Time: " + Integer.toString(gameTime) + " s");
				f.repaint();
				s.setChangeable(true);
				s2.setChangeable(true);
				Thread.sleep(Snake.getSpeed());
				s.step();
				s2.step();

				if (s.getCoords().get(0).getX() == food.getCoord().getX()
						&& s.getCoords().get(0).getY() == food.getCoord().getY()) {
					s.eat(food);
					score1.setText("Score: " + Integer.toString(++sc1));
					addFood();
					f.foodUpdate(food);
				}
				if (s2.getCoords().get(0).getX() == food.getCoord().getX()
						&& s2.getCoords().get(0).getY() == food.getCoord().getY()) {
					s2.eat(food);
					score2.setText("Score: " + Integer.toString(++sc2));
					addFood();
					f.foodUpdate(food);
				}
				s.addIfEated();
				s2.addIfEated();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		f.repaint();
		f.end();
		timeLabel.setText(timeLabel.getText() + " Game over!");
		button.setText("Go to menu ");
		button.setEnabled(true);

	}
	
	/**
	 * @return Az elért eredménnyel tér vissza.
	 */
	public Result getResult(){
		return new Result(LocalDateTime.now(), gameTime,sc1,sc2);
	}
}