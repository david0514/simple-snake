import java.awt.Color;
import java.util.LinkedList;

/**
 * A kígyó amit irányítania kell a játékosnak
 */
public class Snake {
	private static Integer speed=500;
	private Color color;
	private LinkedList<Food> foods;
	public Coordinate newCoord;
	private volatile boolean changeable;
	private volatile String direction;
	private volatile LinkedList<Coordinate> coords;

	/**
	 * @param s1 A kígyó fejének a koordinátája
	 * Hozzáadja a listához a kígyó koordinátáit, beállíjta alap tulajdonságait
	 */
	public Snake(Coordinate s1) {
		coords = new LinkedList<Coordinate>();
		foods = new LinkedList<Food>();
		coords.add(s1);
		coords.add(new Coordinate(s1.getX() - 1, s1.getY()));
		coords.add(new Coordinate(s1.getX() - 2, s1.getY()));
		coords.add(new Coordinate(s1.getX() - 3, s1.getY()));
		coords.add(new Coordinate(s1.getX() - 4, s1.getY()));
		direction = "right";
		changeable = true;
		color = Color.WHITE;
	}

	/**
	 * @param s1 A kígyó fejének a koordinátája
	 * Már meglévő kígyó objektumot állítja alaphelyzetbe
	 */
	public void setToDefault(Coordinate s1) {
		coords.clear();
		foods.clear();
		coords.add(s1);
		coords.add(new Coordinate(s1.getX() - 1, s1.getY()));
		coords.add(new Coordinate(s1.getX() - 2, s1.getY()));
		coords.add(new Coordinate(s1.getX() - 3, s1.getY()));
		coords.add(new Coordinate(s1.getX() - 4, s1.getY()));
		direction = "right";
		changeable = true;
	}

	/**
	 * @param b A haladási irány vátoztathatóságát adja meg. 
	 */
	public synchronized void setChangeable(boolean b) {
		changeable = b;
	}

	/**
	 * @return A haladási irány vátoztathatóságával tér vissza (Igen/nem)
	 */
	public synchronized boolean getChangeable() {
		return changeable;
	}

	/**
	 * @return A kígyó koordinátáival tér vissza.
	 */
	public synchronized LinkedList<Coordinate> getCoords() {
		return coords;
	}

	/**
	 * @param direction Új haladási irány.
	 */
	public synchronized void setDirection(String direction) {
		if (this.direction.equals("up") && direction.equals("down") == false) {
			this.direction = direction;
		}
		if (this.direction.equals("down") && direction.equals("up") == false) {
			this.direction = direction;
		}
		if (this.direction.equals("left") && direction.equals("right") == false) {
			this.direction = direction;
		}
		if (this.direction.equals("right") && direction.equals("left") == false) {
			this.direction = direction;
		}

	}

	/**
	 * @return Aktuális haladási iránnyal tér vissza.
	 */
	public synchronized String getDirection() {
		return direction;
	}

	/**
	 * @param f megevett étel
	 * az ételt megevett állapotra hozza és fozzáadja a kígyó megevett ételeinek listájához.
	 */
	public synchronized void eat(Food f) {
		f.setEated(true);
		foods.add(f);
	}

	/**
	 * Hogyha az étel már nincs a kígyóban hanem a kígyó után egy mezővel, akkor hozzáadja a kígyó koordinátáihoz az étel koordinátáját,
	 * és a megevett ételek listájából törli az adott ételt.
	 * Ezzel a kígyó egyel hosszabbodik.
	 */
	public synchronized void addIfEated() {
		Boolean b = false;
		if (foods.isEmpty() == false) {
			for (Coordinate c : coords) {
				if (foods.get(0).getCoord().getX() == c.getX() && foods.get(0).getCoord().getY() == c.getY()) {
					b = true;
				}
			}
			if (b == false) {
				coords.add(foods.get(0).getCoord());
				foods.remove(0);
			}
		}
	}

	/**
	 * A kígyó lép egyet, ezt úgy teszi hogy az adott haladási irány felé hoz létre egy új koordinátát,
	 * és azt adja hozzá a kígyó koordinátáinak elejére, tehát az új kordináta lesz a kígyó új feje,
	 * az utolsó koordinátát pedig törli, hogy ne változzon a kígyó mérete.
	 */
	public synchronized void step() {
		Coordinate newCoord = new Coordinate(coords.get(0));
		switch (direction) {
		case "right":
			newCoord.setX(coords.get(0).getX() + 1);
			break;
		case "left":
			newCoord.setX(coords.get(0).getX() - 1);
			break;
		case "up":
			newCoord.setY(coords.get(0).getY() - 1);
			break;
		case "down":
			newCoord.setY(coords.get(0).getY() + 1);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		coords.add(0, newCoord);
		coords.remove(coords.size() - 1);
	}

	/**
	 * @param c A kígyónak beállítani kívánt új szín.
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * @return A kígyó színével tér vissza.
	 */
	public Color getColor() {
		return color;
	}
	
	public static void setSpeed(Integer i) {
		speed=i;
	}
	public static Integer getSpeed() {
		return speed;
	}
}