/**
 * This application reads data from a CSV file, converts this data into a JSON structure, and writes it to a JS file.
 * The converter will accept an object type (Planting or Sponsor) and a language type (English or French) to build the JSON accordingly.
 *
 * @author James DeMond and Emily Daniels
 * @version 1.0 January 2014
 */

public class Converter {

	public static void main(String[] args) {
		boolean debug = false;
        String plantingFileToRead = "csvFiles/PlantingDataV7.csv";
        String plantingFileToWrite = "jsonFiles/plantings_";
        String sponsorFileToRead = "csvFiles/SponsorDataV3.csv";
        String sponsorFileToWrite = "jsonFiles/sponsors_";
        boolean isPlanting;

		// reading data from a csv file and convert to java object - planting
		System.out.println("Reading and converting to Planting JSON:");

        CSVToJSON planting = new CSVToJSON();
        isPlanting = true;

		System.out.println("English");
        planting.convertCSV(Constants.english, debug, plantingFileToRead, plantingFileToWrite, isPlanting);

		System.out.println("French");
        planting.convertCSV(Constants.french, debug, plantingFileToRead, plantingFileToWrite, isPlanting);

		System.out.println("Done!");
		
		// reading data from a csv file and convert to java object - sponsor
		System.out.println("Reading and converting to Sponsor JSON:");

		CSVToJSON sponsor = new CSVToJSON();
        isPlanting = false;

		System.out.println("English");
        sponsor.convertCSV(Constants.english, debug, sponsorFileToRead, sponsorFileToWrite, isPlanting);

		System.out.println("French");
        sponsor.convertCSV(Constants.french, debug, sponsorFileToRead, sponsorFileToWrite, isPlanting);

		System.out.println("Done!");

	}
}