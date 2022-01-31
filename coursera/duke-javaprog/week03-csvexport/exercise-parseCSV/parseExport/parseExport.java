import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * クラス parseExport の注釈をここに書きます.
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class parseExport
{
    public void tester() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String result;
        /*
        result = countryInfo(parser, "Nauru");
        System.out.println(result);
        */
        /*
        result = listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println(result);
        */
        /*
        System.out.println("\n number of exporters\n");
        System.out.println(numberOfExporters(parser, "sugar"));
        */
        
        System.out.println("\n Big Exporters\n");
        bigExporters(parser, "$999,999,999,999");
        
    }
    
    /**
     * countryInfo
     *   
     */
    private String countryInfo(CSVParser parser, String country) 
    {
        for (CSVRecord record : parser ) {
            String recCountry = record.get("Country");
            
            if (recCountry.contains(country)) {
                String result = recCountry;
                result += ": " + record.get("Exports");
                result += ": " + record.get("Value (dollars)");
                return result;
            }
        }
        return "NOT FOUND";
    }
    
    /**
     * listExportersTwoProducts
     *   get country list from exportItrm1,2 
     */
    private String listExportersTwoProducts(CSVParser parser, String item1, String item2)
    {
        String countryList = "";
        
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            String country = record.get("Country");
            
            if (exports.contains(item1) && exports.contains(item2)) {
                countryList += country + "\n";
            }
        }
        return countryList;
    }
    
    /**
     * numberOfExporters
     *   export
     */
    private int numberOfExporters(CSVParser parser, String exportItem)
    {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * bigExporters
     */
    private void bigExporters(CSVParser parser, String amount)
    {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String result = country + " " + value;
                System.out.println(result);
            }
        }
    }
}
