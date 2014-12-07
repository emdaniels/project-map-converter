import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import com.google.gson.Gson;

/**
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */
public class CSVToJSON {

    private Data dataObject;
    private Year yearObject;
    private Sponsor topSponsorObject;
    private Planting plantingObject;
    private Sponsor sponsorObject;
    private Location locationObject;
    private Gson gson;

	public void convertCSV(String language, boolean debug, String fileToRead, String fileToWrite, boolean isPlanting) {
        String fileToWriteJS = fileToWrite + language + ".js";
        String line = "";
        String splitBy = ",";

        BufferedReader br = null;
        FileWriter dataJSONFile;
        BufferedWriter dataJSONWriter;

        initializeObjects();

		try {
			br = new BufferedReader(new FileReader(fileToRead));
            dataJSONFile = new FileWriter(fileToWriteJS);
            dataJSONWriter = new BufferedWriter(dataJSONFile);

			br.readLine(); // skip header
			
			while ((line = br.readLine()) != null) {
				line = StringEscapeUtils.escapeHtml4(line);

				// split on comma(',')
				String[] planting = line.split(splitBy);

				for (int i = 0; i < planting.length; i++) {
					planting[i] = planting[i].replace(";;", ", ");
				}

				String program = Constants.english.equals(language) ? planting[0] : planting[1];
				String year = planting[2]; // no change for bilingual
				String programLink = Constants.english.equals(language) ? planting[3] : planting[4];
				String programLogoLink = Constants.english.equals(language) ? planting[5] : planting[6];
				String sponsor = Constants.english.equals(language) ? planting[7] : planting[8];
				String sponsorLink = Constants.english.equals(language) ? planting[9] : planting[10];
				String sponsorLogoLink = Constants.english.equals(language) ? planting[11] : planting[12];
				String location = Constants.english.equals(language) ? planting[13] : planting[14];
				String address = planting[15];
				String city = planting[16];
				String province = planting[17];
				String region = Constants.english.equals(language) ? planting[18] : planting[19];
				//String totalPlanted = planting[20];
				//String environmentalBenefits = planting[21];
				String projectGoals = Constants.english.equals(language) ? planting[22] : planting[23];
				String longitude = planting[24]; // no change for bilingual
				String latitude = planting[25]; // no change for bilingual
				String imageLink = planting[26]; // no change for bilingual
				String species = Constants.english.equals(language) ? planting[27] : planting[28];
				String numberPlanted = planting[29]; // no change for bilingual

				if (debug) {
					System.out.println(line);
				}

                setSponsor(sponsor, location, program, year, address, sponsorLink, sponsorLogoLink);
                setLocation(location, program, year, address, city, province, region, projectGoals, longitude, latitude, imageLink, planting, language);
                setTree(species, numberPlanted);

                if (isPlanting){
                    setPlanting(program, year, programLink, programLogoLink);
                } else {
                    setSponsorPlanting(program, sponsor, year, programLink, programLogoLink);
                    setTopSponsor(sponsor, year, sponsorLink, sponsorLogoLink);
                }

                setYear(year);
			}

            setSponsorList();
            setLocationList();

            if (isPlanting){
                setPlantingList();
            } else {
                setSponsorPlantingList();
                setTopSponsorList();
            }

            setYearList();

            if (isPlanting){
                dataJSONWriter.write("jsonPlantingCallback(");
            } else {
                dataJSONWriter.write("jsonSponsorCallback(");
            }

            dataJSONWriter.write(gson.toJson(dataObject));
            dataJSONWriter.write(");");
            dataJSONWriter.close();
            dataJSONFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            resetObjects();
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

    private void setSponsor(String sponsor, String location, String program, String year, String address, String sponsorLink, String sponsorLogoLink){
        if (!sponsor.equals(sponsorObject.getName())
                || !location.equals(locationObject.getName())
                || !program.equals(plantingObject.getProgram())
                || !year.equals(yearObject.getYear())
                || !address.equals(locationObject.getAddress())) {
            List<Sponsor> sponsors = locationObject.getSponsors();

            if (sponsorObject.getName() != null) {
                sponsors.add(sponsorObject);
            }

            locationObject.setSponsors(sponsors);
            sponsorObject = new Sponsor();
            sponsorObject.setName(sponsor);
            sponsorObject.setSponsorLink(sponsorLink);
            sponsorObject.setSponsorLogoLink(sponsorLogoLink);
        }
    }

    private void setLocation(String location, String program, String year, String address, String city, String province, String region, String projectGoals, String longitude, String latitude, String imageLink, String[] planting, String language){
        if (!location.equals(locationObject.getName())
                || !program.equals(plantingObject.getProgram())
                || !year.equals(yearObject.getYear())
                || !address.equals(locationObject.getAddress())) {
            // the formula for environmental benefits is derived from the Tree Canada paper What Trees Can Do to Reduce Atmospheric CO2
            // available at https://treecanada.ca/en/resources/benefits-trees/
            locationObject.setEnvironmentalBenefits(String.format("%.0f", (locationObject.getTotalPlanted()/5.0 * 1.0 * 3.667)));
            List<Location> locations = plantingObject.getLocations();
            locations.add(locationObject);
            plantingObject.setLocations(locations);

            locationObject = new Location();
            locationObject.setName(location);
            locationObject.setAddress(address);
            locationObject.setCity(city);
            locationObject.setProvince(province);
            locationObject.setRegion(region);
            locationObject.setTotalPlanted(0);
            //locationObject.setEnvironmentalBenefits(environmentalBenefits);
            locationObject.setProjectGoals(projectGoals);
            locationObject.setLatitude(longitude + "," + latitude);
            locationObject.setImageLink(imageLink);

            if (planting.length == 32) {
                String additionalNotes = Constants.english.equals(language) ? planting[30] : planting[31];
                additionalNotes = additionalNotes.replaceAll(" - ", ", ");
                locationObject.setAdditionalNotes(additionalNotes);
            }
        }
    }

    private void setTree(String species, String numberPlanted){
        Tree treeObject = new Tree();
        treeObject.setTreeSpecies(species);
        treeObject.setNumberPlanted(numberPlanted);

        List<Tree> trees = locationObject.getTrees();
        trees.add(treeObject);
        locationObject.setTrees(trees);
        locationObject.setTotalPlanted(locationObject.getTotalPlanted() + Integer.parseInt(numberPlanted));
    }

    private void setSponsorPlanting(String program, String sponsor, String year, String programLink, String programLogoLink){
        // if it's a new name, we need to do some things
        if (!program.equals(plantingObject.getProgram()) || !sponsor.equals(topSponsorObject.getName()) || !year.equals(yearObject.getYear())) {
            // first, we need to insert the old planting object into the list of planting objects
            if (plantingObject.getProgram() != null) {
                List<Planting> plantings = topSponsorObject.getPlantings();
                plantings.add(plantingObject);
                topSponsorObject.setPlantings(plantings);
            }

            // now we need to make a new planting object
            plantingObject = new Planting();
            plantingObject.setProgram(program);
            plantingObject.setProgramLink(programLink);
            plantingObject.setProgramLogoLink(programLogoLink);
        }
    }

    private void setTopSponsor(String sponsor, String year, String sponsorLink, String sponsorLogoLink){
        if (!sponsor.equals(topSponsorObject.getName()) || !year.equals(yearObject.getYear())) {
            List<Sponsor> sponsors = yearObject.getSponsors();

            if (topSponsorObject.getName() != null) {
                sponsors.add(topSponsorObject);
            }

            yearObject.setSponsors(sponsors);
            topSponsorObject = new Sponsor();
            topSponsorObject.setName(sponsor);
            topSponsorObject.setSponsorLink(sponsorLink);
            topSponsorObject.setSponsorLogoLink(sponsorLogoLink);
        }
    }

    private void setPlanting(String program, String year, String programLink, String programLogoLink){
        // if it's a new name, we need to do some things
        if (!program.equals(plantingObject.getProgram()) || !year.equals(yearObject.getYear())) {
            // first, we need to insert the old planting object into the list of planting objects
            if (plantingObject.getProgram() != null) {
                List<Planting> plantings = yearObject.getPlantings();
                plantings.add(plantingObject);
                yearObject.setPlantings(plantings);
            }

            // now we need to make a new planting object
            plantingObject = new Planting();
            plantingObject.setProgram(program);
            plantingObject.setProgramLink(programLink);
            plantingObject.setProgramLogoLink(programLogoLink);
        }
    }

    private void setYear(String year){
        if (!year.equals(yearObject.getYear())) {

            if (yearObject.getYear() != null) {
                List<Year> years = dataObject.getYears();
                years.add(yearObject);
                dataObject.setYears(years);
            }

            yearObject = new Year();
            yearObject.setYear(year);
        }
    }

    private void setSponsorList(){
        List<Sponsor> sponsors = locationObject.getSponsors();
        sponsors.add(sponsorObject);
        locationObject.setSponsors(sponsors);
    }

    private void setLocationList(){
        List<Location> locations = plantingObject.getLocations();
        locations.add(locationObject);
        plantingObject.setLocations(locations);
    }

    private void setSponsorPlantingList(){
        List<Planting> plantings = topSponsorObject.getPlantings();
        plantings.add(plantingObject);
        topSponsorObject.setPlantings(plantings);
    }

    private void setTopSponsorList(){
        List<Sponsor> topSponsors = yearObject.getSponsors();
        topSponsors.add(topSponsorObject);
        yearObject.setSponsors(topSponsors);
    }

    private void setPlantingList(){
        List<Planting> plantings = yearObject.getPlantings();
        plantings.add(plantingObject);
        yearObject.setPlantings(plantings);
    }

    private void setYearList(){
        List<Year> years = dataObject.getYears();
        years.add(yearObject);
        dataObject.setYears(years);
    }

    public void initializeObjects(){
        dataObject = new Data();
        yearObject = new Year();
        topSponsorObject = new Sponsor();
        plantingObject = new Planting();
        sponsorObject = new Sponsor();
        locationObject = new Location();
        gson = new Gson();
    }

    private void resetObjects(){
        dataObject = null;
        yearObject = null;
        topSponsorObject = null;
        plantingObject = null;
        sponsorObject = null;
        locationObject = null;
        gson = null;
    }

}
