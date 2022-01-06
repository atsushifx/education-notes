import edu.duke.*;

/**
 * part4
 * read manylink.html　output with youtube link
 * 
 * @author atsushifx
 * @version 2022-01-06
 */
public class part4 {
    /**
     * getYoutubeLinks
     *   read url & scan youtube link
     *   
     */
    void getYoutubeLinks(String url) 
    {
        URLResource ur = new URLResource(url);
        
        for (String link : ur.words()) {
            int i1 = link.indexOf("\"");
            int i2 = link.indexOf("\"", i1+1);
            if (i1>=0 && i2>=0) {
                link = link.substring(i1+1, i2).toLowerCase();
                if (link.indexOf("youtube.com") >= 0) {
                    System.out.println("link = " + link);
                }
            }      
        }
    }
    
    /**
     * testing:
     *   read manylinks and print links
     *   
     */
    void testing()
    {
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        getYoutubeLinks(url);
        
        /*
        System.out.println("\n\n part4:\n  print youtube links.\n");
        for (String link: links) {
            if (link != null) {
                System.out.println("link="+link);
            }
        }
        */
    }
}
