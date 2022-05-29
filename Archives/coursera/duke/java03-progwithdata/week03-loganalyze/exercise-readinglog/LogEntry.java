import  java.util.*;

/**
 * LogEntry
 *   parses web access log data for immutable.
 * 
 * @author  Furukawa Atsushi 
 * @version 1.0.0
 */
public class LogEntry
{
     private String ipAddress;
     private Date accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;

    /**
     * Constructor
     */
    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }
    
    /**
     * eac fields getter
     */
    public String getIpAddress() {
       return ipAddress;
    }
    public Date getAccessTime() {
         return accessTime;
    }   
    public String getRequest() {
         return request;
    }
    public int getStatusCode() {
         return statusCode;
    }
    public int getBytesReturned() {
         return bytesReturned;
    }
   
    public String toString() {
        return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
    }
}
