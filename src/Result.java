import java.io.Serializable;
import java.time.LocalDateTime;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer scores1;
	Integer scores2;
	Integer gameTime;
	LocalDateTime date;
	
	/**
	 * @param dt az aktuális dátum-idő
	 * @param gt A játékidő
	 * @param sc1 1-es játékos pontszáma
	 * @param sc2 2-es játékos pontszáma
	 */
	public Result(LocalDateTime dt, Integer gt, Integer sc1, Integer sc2){
		this.date=dt;
		this.gameTime=gt;
		this.scores1=sc1;
		this.scores2=sc2;
	}
	
	/**
	 * @param dt az aktuális dátum-idő
	 * @param gt A játékidő
	 * @param sc1 A játékos pontszáma
	 */
	public Result(LocalDateTime dt, Integer gt, Integer sc1){
		this.date=dt;
		this.gameTime=gt;
		this.scores1=sc1;
		this.scores2=null;
	}

	

	/**
	 * @return 1-es játékos pontszáma.
	 */
	public Integer getScore1() {
		return scores1;
	}

	/**
	 * @return 2-es játékos pontszáma. Egyjátékos módban null-al tér vissza.
	 */
	public Integer getScore2() {
		return scores2;
	}
	/**
	 * @param i 1-es játékos pontszáma.
	 */
	public void setScore1(Integer i) {
		scores1=i;
	}
	/**
	 * @param i 2-es játékos pontszáma.
	 */
	public void setScore2(Integer i) {
		scores2=i;
	}
	/**
	 * @return Játékidő
	 */
	public Integer getGameTime() {
		return gameTime;
	}
	/**
	 * @param i Játékidő
	 */
	public void setGameTime(Integer i) {
		gameTime=i;
	}
	/**
	 * @return Dátum
	 */
	public LocalDateTime getDate() {
		return date;
	}
	/**
	 * @param d Dátum
	 */
	public void setDate(LocalDateTime d) {
		date=d;
	}
}