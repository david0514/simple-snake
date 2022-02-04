import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Az irányításért felelős osztály
 * Segítségével irányíthatjuk a kígyót.
 */
public class Control extends Thread implements KeyListener {
	private volatile boolean isUpPressed, isDownPressed, isRightPressed, isLeftPressed;
	private volatile boolean isWPressed, isSPressed, isDPressed, isAPressed;
	private volatile boolean exit;
	private Snake snake;
	private boolean b;

	/**
	 * @param s A kígyó amit irányít.
	 * @param b ha true akkor a nyilakkal irányít, ha false akkor a w,a,s,d billentyűkkel. 
	 */
	public Control(Snake s, boolean b) {
		this.snake = s;
		this.b = b;
		exit = false;
	}

	
	/**
	 * A billenytű leütést finyelő szál leállítása.
	 */
	public void end() {
		exit = true;
	}

	public void keyTyped(KeyEvent e) {
	}
	
	/**
	 * Ha megnyomódik valamelyik billentyű a megfelelő Boolean változó true-ra állítódik
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			isUpPressed = true;
			break;
		case KeyEvent.VK_DOWN:
			isDownPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			isRightPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			isLeftPressed = true;
			break;
		case KeyEvent.VK_W:
			isWPressed = true;
			break;
		case KeyEvent.VK_S:
			isSPressed = true;
			break;
		case KeyEvent.VK_D:
			isDPressed = true;
			break;
		case KeyEvent.VK_A:
			isAPressed = true;
			break;
		}
	}

	/**
	 * Ha felengedjük a megnyomott billentyűt megfelelő Boolean változó false-ra állítódik vissza.
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			isUpPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			isDownPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			isRightPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			isLeftPressed = false;
			break;
		case KeyEvent.VK_W:
			isWPressed = false;
			break;
		case KeyEvent.VK_S:
			isSPressed = false;
			break;
		case KeyEvent.VK_D:
			isDPressed = false;
			break;
		case KeyEvent.VK_A:
			isAPressed = false;
			break;
		}
	}

	/**
	 * Ez figyeli a Boolean változók állapotát ha valamelyiknél true-t érzékel,
	 * az annak a változónak megfelelő irányt áttítja be haladási iránynak, ha változtatható.
	 * Majd beállítja hogy ne lehessen változtatni rajta a követhező lépésig.
	 */
	public void run() {
		while (!exit) {

			if (b) {
				if (isUpPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("up");
						snake.setChangeable(false);
					}
				}

				if (isDownPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("down");
						snake.setChangeable(false);
					}
				}

				if (isRightPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("right");
						snake.setChangeable(false);
					}
				}

				if (isLeftPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("left");
						snake.setChangeable(false);
					}
				}
			} else {

				if (isWPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("up");
						snake.setChangeable(false);
					}
				}
				if (isSPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("down");
						snake.setChangeable(false);
					}
				}
				if (isDPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("right");
						snake.setChangeable(false);
					}
				}
				if (isAPressed) {
					if (snake.getChangeable()) {
						snake.setDirection("left");
						snake.setChangeable(false);
					}
				}
			}

		}
	}
}