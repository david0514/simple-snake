import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A pálya akadályok nélkül,
 * illetve ez az osztály felelős a játék elemeinek kirajzolásáért.
 */
public class Field extends JPanel {
	private static final long serialVersionUID = 1L;

	private Control control;
	private Control control2;
	private Food f;
	protected Snake s;
	protected Snake s2;
	protected Integer width;
	protected Integer height;
	protected Integer a;
	protected Boolean singleMode;

	private static Color backgroundColor;
	protected static Color obstaclesColor;
	
	/**
	 * Beállítja az objektumokat, és elindítja az irányításért felelős szálat.
	 * @param s 1-es kígyó
	 * @param f Étel
	 */
	public Field(Snake s, Food f) {
		this.singleMode = true;
		this.width = 25;
		this.height = 20;
		this.f = f;
		this.s = s;
		this.control = new Control(s, true);
		this.setFocusable(true);
		this.control.start();
		addKeyListener(this.control);
	}
	
	/**
	 * Beállítja az objektumokat, és elindítja az irányításért felelős szálakat.
	 * @param s 1-es kígyó
	 * @param s2 2-es kígyó
	 * @param f Étel
	 */
	public Field(Snake s, Snake s2, Food f) {
		this.singleMode = false;
		this.width = 25;
		this.height = 20;
		this.f = f;
		this.s = s;
		this.s2 = s2;
		this.control = new Control(s, true);
		this.control2 = new Control(s2, false);
		this.setFocusable(true);
		this.control.start();
		this.control2.start();
		addKeyListener(this.control);
		addKeyListener(this.control2);

	}
	
	/**
	 *Ez a függvény felelős a játék elemeinek kirajzolásáért.
	 * pálya, akadályok, kígyók, étel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.getBounds().getHeight() / height < getBounds().getWidth() / width) {
			a = (int) getBounds().getHeight() / height;
		} else {
			a = (int) getBounds().getWidth() / width;
		}

		setBackground(Color.WHITE);
		g.setColor(backgroundColor);
		g.fillRect((int) getBounds().getWidth() / 2 - (a * width) / 2,
				(int) getBounds().getHeight() / 2 - (a * height) / 2, a * width, a * height);

		g.setColor(Food.getColor());
		g.fillRect((int) getBounds().getWidth() / 2 - (a * width) / 2 + f.getCoord().getX() * a,
				(int) getBounds().getHeight() / 2 - (a * height) / 2 + f.getCoord().getY() * a, a, a);

		g.setColor(s.getColor());
		for (Coordinate c : s.getCoords()) {
			g.fillRect((int) getBounds().getWidth() / 2 - (a * width) / 2 + c.getX() * a,
					(int) getBounds().getHeight() / 2 - (a * height) / 2 + c.getY() * a, a, a);
		}
		if (singleMode == false) {
			g.setColor(s2.getColor());
			for (Coordinate c : s2.getCoords()) {
				g.fillRect((int) getBounds().getWidth() / 2 - (a * width) / 2 + c.getX() * a,
						(int) getBounds().getHeight() / 2 - (a * height) / 2 + c.getY() * a, a, a);
			}
		}
	}
	
	/**
	 * Ez a függvény ellenőrzi hogy a kígyók ötköztek e valamivel(ha igen az a játék végét jelenti)
	 * eelenörzi hogy a keretnek nekimentek-e, önmaguknak nekimentek-e,
	 * ha több játékos van akkor egymásnak nekimentek-e
	 * @return ütköztek(true)/nem ütföztek(false)
	 */
	public synchronized boolean collision() {
		for (int i = 1; i < s.getCoords().size() - 1; i++) {
			if (s.getCoords().get(0).getX() == s.getCoords().get(i).getX()
					&& s.getCoords().get(0).getY() == s.getCoords().get(i).getY()) {
				return true;
			}
		}
		if (s.getCoords().get(0).getX() < 0 || s.getCoords().get(0).getY() < 0 || s.getCoords().get(0).getX() >= width
				|| s.getCoords().get(0).getY() >= height) {
			return true;
		}
		if (singleMode == false) {
			for (int i = 1; i < s2.getCoords().size() - 1; i++) {
				if (s2.getCoords().get(0).getX() == s2.getCoords().get(i).getX()
						&& s2.getCoords().get(0).getY() == s2.getCoords().get(i).getY()) {
					return true;
				}
			}

			if (s2.getCoords().get(0).getX() < 0 || s2.getCoords().get(0).getY() < 0
					|| s2.getCoords().get(0).getX() >= width || s2.getCoords().get(0).getY() >= height) {
				return true;
			}
			for (int i = 1; i < s.getCoords().size() - 1; i++) {
				if (s2.getCoords().get(0).getX() == s.getCoords().get(i).getX()
						&& s2.getCoords().get(0).getY() == s.getCoords().get(i).getY()) {
					return true;
				}
			}
			for (int i = 1; i < s2.getCoords().size() - 1; i++) {
				if (s.getCoords().get(0).getX() == s2.getCoords().get(i).getX()
						&& s.getCoords().get(0).getY() == s2.getCoords().get(i).getY()) {
					return true;
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
		for (Coordinate coordin : s.getCoords()) {
			if (coord.getX() == coordin.getX() && coord.getY() == coordin.getY()) {
				return false;
			}
		}
		if (singleMode == false) {
			for (Coordinate coordin : s2.getCoords()) {
				if (coord.getX() == coordin.getX() && coord.getY() == coordin.getY()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Leállítja az irányításért felelős szálat/szálakat.
	 */
	public void end() {
		control.end();
		if (singleMode == false) {
			control2.end();
		}
	}
	
	public static void setBackgroundColor(Color c) {
		backgroundColor = c;
	}

	public static void setObstaclesColor(Color c) {
		obstaclesColor = c;
	}

	public static Color getBackgroundColor() {
		return backgroundColor;
	}

	public static Color getObstaclesColor() {
		return obstaclesColor;
	}

	/**
	 * @return ha a szélesség kisebb azzal tér vissza, ha a magasság akkor azzal.
	 */
	public Integer getFieldSize() {
		if (this.getBounds().getHeight() < getBounds().getWidth()) {
			return (int) getBounds().getHeight();
		} else {
			return (int) getBounds().getWidth();
		}
	}

	/** Frissíti az ételt
	 * @param f Étel
	 */
	public void foodUpdate(Food f) {
		this.f = f;
	}
}
