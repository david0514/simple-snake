import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ResultsData extends AbstractTableModel{
private static final long serialVersionUID = 1L;
    ArrayList<Result> results = new ArrayList<Result>();

	/**
	 *Sorok száma a táblázatban
	 */
	@Override
	public int getRowCount() {
		return results.size();
	}

	/**
	 * Oszlopok száma a táblázatban
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 *Aktuális mező értéke
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Result result = results.get(rowIndex);
		switch(columnIndex) {
		case 0: return result.getDate();
		case 1: return result.getGameTime();
		case 2: return result.getScore1();
		default: return result.getScore2();
		}
	}
	
	/**
	 * Oszlopok nevei
	 */
	public String getColumnName(int columnIndex) {
		 switch(columnIndex) {
			case 0: return "Date";
			case 1: return "Game Time (sec)";
			case 2: return "Score 1";
			default: return "Score 2";
			}
	 }
	
	/**
	 * Oszlopok típusai
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c) {
		 Class[] columns = new Class[]{LocalDateTime.class, Integer.class, Integer.class, Integer.class};
	        return columns[c];
	    }

}