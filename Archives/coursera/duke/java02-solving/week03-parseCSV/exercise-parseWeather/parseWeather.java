import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * parseWeather
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class parseWeather
{
    CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        
        for (CSVRecord row : parser ) {
            if (coldest == null) {
                coldest = row;
            } else {
                double t = Double.parseDouble(row.get("TemperatureF"));
                double tc = Double.parseDouble(coldest.get("TemperatureF"));
                
                if (t>=0 && t<tc) {
                    coldest = row;
                }
            }
        }
        return coldest;
    }
    
    
    /**
     * filewithColdestTemparture
     * 
     * 最低気温を出したファイルの取得
     */
    File filewithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        double coldest = -1.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord row = coldestHourInFile(parser);coldestHourInFile(parser);
            double t = Double.parseDouble(row.get("TemperatureF"));
            
            if (coldestFile == null) {
                coldestFile = f;
                coldest = t;
            } else if( t < coldest) {
                coldestFile = f;
                coldest = t;
            }
        }
        return coldestFile;
    }
    
    /**
     * lowestHumidityInFile
     *   最小湿度を記録したRecordを取得
     */
    CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord result = null;
      
        for (CSVRecord row : parser) {
            if (result == null) {
                result = row;
            } else {
                String h = row.get("Humidity");
                
                if (!h.equals("N/A")) {
                    int lh = Integer.parseInt(result.get("Humidity"));
                    int h2 = Integer.parseInt(h);
                    if (h2 < lh) {
                        result = row;
                    }
                }
            }
        }
        return result;  
    }
    
    /**
     * lowestHumidityInManyFiles
     * 各ファイルの最小湿度を取得
     */
    CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord row = lowestHumidityInFile(parser);
            
            if (lowest == null) {
                lowest = row;
            } else {
                double lh = Double.parseDouble(lowest.get("Humidity"));
                double h  = Double.parseDouble(row.get("Humidity"));
                
                if (h < lh) {
                    lowest = row;
                }
            }
            
        }
        return lowest;
    }
    
    /**
     * averageTemperatureInFile
     *   平均気温を取得
     *   
     */
    double averageTemperatureInFile(CSVParser parser) {
        double total = 0.0;
        int count = 0;
        
        for (CSVRecord row : parser) {
            double tmp = Double.parseDouble(row.get("TemperatureF"));
            total += tmp;
            count++;
        }
        double avg = total / 24.0;
        
        return avg;
    }
    
    /**
     * averageTemperatureWithHighHumidityInFile
     */
    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0.0;
        int count = 0;
        
        for (CSVRecord row : parser) {
            double tmp = Double.parseDouble(row.get("TemperatureF"));
            int h = Integer.parseInt(row.get("Humidity"));
            
            if (h >= value) {
                total += tmp;
                count++;
            }
        }
        if (count > 0) {
            double avg = total / count;
            return avg;
        } else {
            
        }
        return 0.0;
    }
    
    // sub for tester
    /**
     * 指定ファイルの気温一覧
     */
    private void printTemperatureList(File f, String header) {
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        
        System.out.println(header);
        for (CSVRecord row : parser) {
            String date = row.get("DateUTC");
            String t = row.get("TemperatureF");
            
            System.out.println(date + ": " + t + "F"); 
        }
    }
    
    private void printColdestFile(File fc) {
        FileResource fr = new FileResource(fc);
        CSVParser parser = fr.getCSVParser();
        CSVRecord rc = coldestHourInFile(parser);
        
        System.out.println("Coldest day is in file " + fc.getName());
        System.out.println("Coldest temperature on that day was " + rc.get("TemperatureF") + "F");
        
    }
    
    //  test methods
    /**
     * testColdestInFile
     * 指定ファイルから最低気温を記録した日時を表示
     *  
     */
    public void testColdestHourInFile() {
        System.out.println("\n test coldest tmp.");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = coldestHourInFile(parser);
        
        System.out.print(" Time:" + csv.get("TimeEDT"));
        System.out.print(" Temperature:" + csv.get("TemperatureF") + "F");
        System.out.println("");
    }
    
    /**
     * testFilewithColdestTemperature
     */
    public void testFilewithColdestTemperature() {
        System.out.println("\n test selected files temp.");
        
        File fc = filewithColdestTemperature();
        
        printColdestFile(fc);
        printTemperatureList(fc, "All the Temperatures on the coldest day were:");
    }
    
    /**
     * testLowestHumidityInFile
     * test:　指定したファイルの最小湿度を表示
     */
    void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        
        System.out.println("\n  print Lowest Humidity  ");
        CSVParser parser = fr.getCSVParser();
            
        CSVRecord row = lowestHumidityInFile(parser);
        String humidity = row.get("Humidity");
        String lowdate = row.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + " at " + lowdate);
    }
    
    /**
     * testLowestHumidtyinManyFiles
     */
    public void testLowestHumidityinManyFiles() {
        System.out.println("\n test selected files humidity.");

        
        CSVRecord row = lowestHumidityInManyFiles();
        
        String humidity = row.get("Humidity");
        String lowdate = row.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + " at " + lowdate);
    }
    
    /**
     * testAverageTemperatureInFile
     *   平均気温取得テスト
     */
    public void testAverageTemperatureInFile() {
        System.out.println("\n  average tmp. in File");
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+avg);
    }
    
    /**
     * testAverageTemperatureWithHighHumidityInFile
     */
    public void testAverageTemperatureWithHighHumidityInFile() {
        System.out.println("\n average tmp. with High humidity");
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avg <= 0.0) {
            System.out.println("No temperatures with that humidity.");
        } else {
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }
}


