import java.util.ArrayList;
import java.util.List;

/**
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */
public class Planting {
	private String program;
	private String programLink;
	private String programLogoLink;
	private List<Location> locations;
	
	public Planting() {
		locations = new ArrayList<Location>();
	}
	
	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * @param program
	 *            the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	
	/**
	 * @return the programLink
	 */
	public String getProgramLink() {
		return programLink;
	}

	/**
	 * @param programLink
	 *            the programLink to set
	 */
	public void setProgramLink(String programLink) {
		this.programLink = programLink;
	}

	/**
	 * @return the programLogoLink
	 */
	public String getProgramLogoLink() {
		return programLogoLink;
	}

	/**
	 * @param programLogoLink
	 *            the programLogoLink to set
	 */
	public void setProgramLogoLink(String programLogoLink) {
		this.programLogoLink = programLogoLink;
	}

	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}