
public class Coordinate {
	private int x;
	private int y;

	/**
	 * 
	 */
	public Coordinate() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Koordináta beállítása x,y int.ből
	 * @param x 
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Koordináta beállítása Koordinátából
	 * @param c
	 */
	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}

	/**
	 * Koordináta beállítása x,y int.ből
	 * @param x 
	 * @param y
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Koordináta x értékének beállítása
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Koordináta y értékének beállítása
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Koordináta x értékének lekérése
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Koordináta y értékének lekérése
	 * @return
	 */
	public int getY() {
		return y;
	}
}