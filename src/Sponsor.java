import java.util.ArrayList;
import java.util.List;

/**
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */
public class Sponsor {
	private String name;
	private String sponsorLink;
	private String sponsorLogoLink;
	private List<Planting> plantings;
	
	public Sponsor() {
		plantings = new ArrayList<Planting>();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sponsorLink
	 */
	public String getSponsorLink() {
		return sponsorLink;
	}

	/**
	 * @param sponsorLink
	 *            the sponsorLink to set
	 */
	public void setSponsorLink(String sponsorLink) {
		this.sponsorLink = sponsorLink;
	}

	/**
	 * @return the sponsorLogoLink
	 */
	public String getSponsorLogoLink() {
		return sponsorLogoLink;
	}

	/**
	 * @param sponsorLogoLink
	 *            the sponsorLogoLink to set
	 */
	public void setSponsorLogoLink(String sponsorLogoLink) {
		this.sponsorLogoLink = sponsorLogoLink;
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
}
