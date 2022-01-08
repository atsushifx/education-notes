import edu.duke.*;

/**
 * exam1 
 * read manylink.html　and search youtube link from page
 * 
 * @author atsushifx
 * @version 2022-01-09
 */
public class exam1 {
    /**
     * getLink
     *  get url link from website's 1line
     *  not found: return "";
     */
    private String getLink(String line)
    {
        int pos1 = line.indexOf("href=\"");
        if (pos1 == -1) {
            return "";
        }
        int pos2 = line.indexOf("\"", pos1+6);
        if (pos2 == -1) {
            return "";
        }
        return line.substring(pos1+6, pos2);
    }
    
    private String getLinkText(String line)
    {
        int pos1, pos2;
        
        pos1 = line.indexOf("\">");
        if (pos1 != -1) {
            line = line.substring(pos1+2);    
        }
        
        pos2 = line.indexOf("</a>");
        if (pos2 != -1) {
            line = line.substring(0, pos2);    
        }
        return line;
    }
    /**
     * html words link to link list
     *   read url & scan youtube link
     *   
     */
    StorageResource getLinkList(String url) 
    {
        URLResource ur = new URLResource(url);
        StorageResource links = new StorageResource();
        String link = "";
        String linkText = "";
        for (String line : ur.words()) {
            if (link.isEmpty()) {
                link = getLink(line);
                linkText = "";
            }
            
            String addText = getLinkText(line);
            if (!addText.isEmpty()) {
                linkText += " " + addText;
            }
            
            // add links
            int pos = line.indexOf("</a");
            if (pos != -1) { // href end
                if (!link.isEmpty()) {
                    links.add(link + " | " + linkText);
                }
                link = ""; // reset 
            }
        }
        return links;
    }
    
    /**
     * testing:
     *   read manylinks and print links
     *   
     */
    void youtubeLinks()
    {
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        StorageResource manyLinks = getLinkList(url);
        
        int n = 0;
        for (String link : manyLinks.data()) {
            if (link.toLowerCase().indexOf("youtube") != -1) {
                n++;
                System.out.println("["+n+"] "+link);
            }
        }
        
    }
}
