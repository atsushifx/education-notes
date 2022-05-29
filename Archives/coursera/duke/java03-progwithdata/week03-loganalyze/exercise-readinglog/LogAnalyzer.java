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
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> visits = new HashMap<String, Integer>();
        
        for (LogEntry le : records) {
            String ip  = le.getIpAddress();
            
            if (!visits.containsKey(ip)) {
                visits.put(ip, 1);
            } else {
                visits.put(ip, visits.get(ip) + 1);
            }
        }
        return visits;
    }
    
    /**
     * mostNumberVisitsByIP,
     */
    public int mostNumberVisitsByIP(HashMap<String, Integer> visits) {
        int max = 0;
        
        for (Integer n : visits.values()) {
            if (max==0 || n>max) {
                max = n;
            }
        }
        
        return max;
    }
    
    /**
     * iPsMostVisits
     *   get IP list from visits that most visits
     */
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visits) {
        ArrayList<String> ipList = new ArrayList<String>();
        
        int max = mostNumberVisitsByIP(visits);
        for (String ip: visits.keySet()) {
            if (visits.get(ip) == max) {
                ipList.add(ip);
            }
        }
        
        return ipList;
    }
    
    /**
     * iPsForDays
     *   get IP list day by day
     */
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysip = new HashMap<String, ArrayList<String>>();
        
        for (LogEntry le : records) {
            String mmmdd = mmmdd(le.getAccessTime());
            String ip = le.getIpAddress();
            ArrayList<String> ipList = null;
            if (daysip.containsKey(mmmdd)) {
                ipList = listIP(daysip.get(mmmdd), ip, false);
            } else {
                ipList = listIP(null, ip, false);
            }
            daysip.put(mmmdd, ipList);
        }
        
        return daysip;
    }
    
    /**
     * listIP
     *   create Unique IP List
     */
    ArrayList<String> listIP(ArrayList<String> ipList, String ip, boolean isUnique) {
        if (ipList == null) {
            ipList = new ArrayList<String>();
        }
        if (!ipList.contains(ip) || !isUnique) {
            ipList.add(ip);
        }
        return ipList;
    }
    
    /**
     * get date string by 'MMM DD' format
     */
    private String mmmdd(Date date) {
        String dateStr = date.toString();
        return dateStr.substring(4, 10);
    }
    
    /**
     * dayWithMostIPVisits
     */
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> visitsbyDay) {
        String oneday = "";
        int max = 0;
        
        for (String day : visitsbyDay.keySet()) {
            int visitsNum = visitsbyDay.get(day).size();
            
            if (max==0 || visitsNum > max) {
                max = visitsNum;
                oneday = day;
            }
        }
        return oneday;
    }
    
    
    /**
     * iPsWithMostVisitsOnDay
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> visitsbyDay, String oneday) {
        HashMap<String, Integer> visits = new HashMap<String, Integer>();
            
        for (String ip : visitsbyDay.get(oneday)) {
            if (!visits.containsKey(ip)) {
                visits.put(ip, 1);
            } else {
                visits.put(ip, visits.get(ip) + 1);
            }
        }
        return iPsMostVisits(visits);
    }
}
