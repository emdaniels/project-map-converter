import java.util.ArrayList;
import java.util.List;

/**
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */
public class Data {
	List<Year> years;
	
	
	public Data() {
		years = new ArrayList<Year>();
	}
	
	/**
	 * @return the year
	 */
	public List<Year> getYears() {
		return years;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYears(List<Year> years) {
		this.years = years;
	}
	
	
}
