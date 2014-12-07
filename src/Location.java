import java.util.ArrayList;
import java.util.List;

/**
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */
public class Location {
	private String name;
	private String address;
	private String city;
	private String province;
	private String region;
	private int totalPlanted;
	private String environmentalBenefits;
	private String projectGoals;
	private String coordinates;
	private String imageLink;
	private String additionalNotes;
	private List<Sponsor> sponsors;
	private List<Tree> trees;
	
	public Location() {
		sponsors = new ArrayList<Sponsor>();
		trees = new ArrayList<Tree>();
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the totalPlanted
	 */
	public int getTotalPlanted() {
		return totalPlanted;
	}

	/**
	 * @param totalPlanted
	 *            the totalPlanted to set
	 */
	public void setTotalPlanted(int totalPlanted) {
		this.totalPlanted = totalPlanted;
	}

	/**
	 * @return the environmentalBenefits
	 */
	public String getEnvironmentalBenefits() {
		return environmentalBenefits;
	}

	/**
	 * @param environmentalBenefits
	 *            the environmentalBenefits to set
	 */
	public void setEnvironmentalBenefits(String environmentalBenefits) {
		this.environmentalBenefits = environmentalBenefits;
	}

	/**
	 * @return the projectGoals
	 */
	public String getProjectGoals() {
		return projectGoals;
	}

	/**
	 * @param projectGoals
	 *            the projectGoals to set
	 */
	public void setProjectGoals(String projectGoals) {
		this.projectGoals = projectGoals;
	}

	/**
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setLatitude(String coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * @return the imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}

	/**
	 * @param imageLink
	 *            the imageLink to set
	 */
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	/**
	 * @return the additional notes
	 */
	public String getAdditionalNotes() {
		return additionalNotes;
	}

	/**
	 * @param additionalNotes
	 *            the additional notes to set
	 */
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
	/**
	 * @return the sponsors
	 */
	public List<Sponsor> getSponsors() {
		return sponsors;
	}

	/**
	 * @param sponsors the sponsors to set
	 */
	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}

	/**
	 * @return the trees
	 */
	public List<Tree> getTrees() {
		return trees;
	}

	/**
	 * @param trees the trees to set
	 */
	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}
}
