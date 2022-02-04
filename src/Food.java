import java.awt.Color;

/**
 * Étel amit meg tud enni a kígyó
 */
public class Food {
	private Coordinate c;
	public static Color color;
	private Boolean eated;
	private Boolean add;

	/**
	 * Beállítja az alap állapotot.
	 * @param c Étel koordinátája
	 */
	public Food(Coordinate c) {
		eated = false;
		add = false;
		this.c = c;
	}
	
	/**
	 * Szín beállítása
	 * @param c szín
	 */
	public static void setColor(Color c){
		color=c;
	}
	
	/**
	 * Aktuális szín lekérése
	 * @return szín
	 */
	public static Color getColor(){
		return color;
	}
	
	/**
	 * Állapot megadása
	 * @param b megette már a kígyó/nem ette még meg.
	 */
	public void setEated(Boolean b) {
		eated = b;
	}

	/**
	 * Állapot lekérdezése
	 * @return megette már a kígyó/nem ette még meg.
	 */
	public Boolean getEated() {
		return eated;
	}

	/**
	 * @param b hozzávan e adva a kígyóhoz
	 */
	public void setAdd(Boolean b) {
		add = b;
	}

	/**
	 * @return hozzávan e adva a kígyóhoz
	 */
	public Boolean getAdd() {
		return add;
	}

	/**
	 * koordináta lekérdezése
	 * @return koordináta
	 */
	public Coordinate getCoord() {
		return c;
	}

	/**
	 * koordináta megadása
	 * @param c koordináta
	 */
	public void setCoord(Coordinate c) {
		this.c = c;
	}

}