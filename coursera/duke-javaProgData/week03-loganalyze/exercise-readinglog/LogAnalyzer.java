import java.util.*;
import edu.duke.*;

/**
 * LogAnalyzer
 *  analyze apache access log main
 *    parse access log & store to log entry
 *
 * @author  Furukawa Atsushi
 * @version 1.0.0
 * @date    2022-02-23
 */
public class LogAnalyzer
{
    // fields
    private ArrayList<LogEntry> records;

    /**
     * construnctor
     */
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    /**
     * readFile
     *   read acceess.log and analyze log
     *     then log data store to records
     * 
     */
    public void readFile(String logfile) {
        FileResource fr = new FileResource(logfile);
        for(String line: fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
    
    
    /**
     * printAll
     *   print all log entry in records
     */
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    /**
     * printAllHigherThanNum
     */
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }    
    }
    
    
    /**
     * countUniqueIPs
     */
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    /**
     * uniqueIPVisitsOnDay
     *   get unique ip addr list on same day
     */
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String>  ipList = new ArrayList<String>();
        
        for (LogEntry le : records) {
            String day = le.getAccessTime().toString();
            String ip  = le.getIpAddress();
            
            if ((day.indexOf(someday)>0)&&(!ipList.contains(ip))) {
                ipList.add(ip);
            }
        }
        return ipList;
    }
    
    /**
     * countUniqueIPsInRange
     *   count unique IPs on status code is in range
     */
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String>  ipList = new ArrayList<String>();
        
        for (LogEntry le : records) {
            String ip  = le.getIpAddress();
            int status = le.getStatusCode();
            
            if ((low<=status && status<=high) && (!ipList.contains(ip))) {
                ipList.add(ip);
            }
            
        }
        return ipList.size();
    }
}
