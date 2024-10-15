import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * parseExport
 *   輸出物csv読み込み
 *   
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class parseExport
{
    
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
     *   get country list from exportItem1,2 
     */
    String listExportersTwoProducts(CSVParser parser, String item1, String item2)
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
    int numberOfExporters(CSVParser parser, String exportItem)
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
    void bigExporters(CSVParser parser, String amount)
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
    
    /**
     * parse export country tester
     */
    public void tester() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String result;
        /*
        result = countryInfo(parser, "Nauru");
        System.out.println(result);
        */
        
        System.out.println("\n  exports 2 items");
        result = listExportersTwoProducts(parser, "cotton", "flowers");
        System.out.println(result);
        
        
        System.out.println("\n number of export countries");
        String item="cocoa";
        int n = numberOfExporters(parser, item);
        parser = fr.getCSVParser();
        System.out.println("Export:"+item+" countries:"+n);
        
        
        System.out.println("\n Big Exporters");
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
    
}

