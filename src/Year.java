import java.util.ArrayList;
import java.util.List;

/**
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */
public class Year {
	String year;
	List<Planting> plantings;
	List<Sponsor> sponsors;
	
	public Year() {
		year = null;
		plantings = new ArrayList<Planting>();
		sponsors = new ArrayList<Sponsor>();
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * @return the plantings
	 */
	public List<Planting> getPlantings() {
		return plantings;
	}

	/**
	 * @param plantings
	 *            the plantings to set
	 */
	public void setPlantings(List<Planting> plantings) {
		this.plantings = plantings;
	}
	
	/**
	 * @return the sponsors
	 */
	public List<Sponsor> getSponsors() {
		return sponsors;
	}

	/**
	 * @param sponsors
	 *            the sponsors to set
	 */
	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}

}
