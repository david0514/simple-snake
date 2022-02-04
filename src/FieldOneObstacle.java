import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A pálya egy akadályal,
 * illetve ez az osztály felelős a játék elemeinek kirajzolásáért.
 * A sima pályából(Field) származik anyi a különbség hogy itt van egy akadály.
 */
public class FieldOneObstacle extends Field {
	private static final long serialVersionUID = 1L;
	ArrayList<Coordinate> obstacle = new ArrayList<Coordinate>();

	/**
	 * Beállítja az objektumokat, és elindítja az irányításért felelős szálat.
	 * @param s 1-es kígyó
	 * @param f Étel
	 */
	public FieldOneObstacle(Snake s, Food f) {
		super(s, f);
		for (int i = 6; i <= 19; i++) {
			obstacle.add(new Coordinate(i, 10));
		}
	}
	
	/**
	 * Beállítja az objektumokat, és elindítja az irányításért felelős szálakat.
	 * @param s 1-es kígyó
	 * @param s2 2-es kígyó
	 * @param f Étel
	 */
	public FieldOneObstacle(Snake s, Snake s2, Food f) {
		super(s, s2, f);
		for (int i = 6; i < 19; i++) {
			obstacle.add(new Coordinate(i, 10));
		}
	}

	/**
	 *Ez a függvény felelős a játék elemeinek kirajzolásáért.
	 * pálya, akadályok, kígyók, étel, akadály
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(obstaclesColor);
		for (Coordinate c : obstacle) {
			g.fillRect((int) getBounds().getWidth() / 2 - (a * width) / 2 + c.getX() * a,
					(int) getBounds().getHeight() / 2 - (a * height) / 2 + c.getY() * a, a, a);
		}
	}

	/**
	 * Ez a függvény ellenőrzi hogy a kígyók ötköztek e valamivel(ha igen az a játék végét jelenti)
	 * eelenörzi hogy a keretnek nekimentek-e, önmaguknak nekimentek-e,
	 * ha több játékos van akkor egymásnak nekimentek-e
	 * @return ütköztek(true)/nem ütföztek(false)
	 */
	public synchronized boolean collision() {
		if (super.collision() == true) {
			return true;
		} else {
			for (int i = 0; i < obstacle.size(); i++) {
				if (obstacle.get(i).getX() == s.getCoords().get(0).getX()
						&& obstacle.get(i).getY() == s.getCoords().get(0).getY()) {
					return true;
				}
			}

			if (singleMode == false) {
				for (int i = 0; i < obstacle.size(); i++) {
					if (obstacle.get(i).getX() == s2.getCoords().get(0).getX()
							&& obstacle.get(i).getY() == s2.getCoords().get(0).getY()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * A kérdezett kordináta szabad-e (ezt használja új étel helyének generálásához)
	 * @param coord Kérdezett koordináte
	 * @return szabad(true)/foglalt(false)
	 */
	public Boolean isFree(Coordinate coord) {
		if (super.isFree(coord) == false) {
			return false;
		}
		for (Coordinate c : obstacle) {
			if (c.getX() == coord.getX() && c.getY() == coord.getY()) {
				return false;
			}
		}
		return true;
	}
}