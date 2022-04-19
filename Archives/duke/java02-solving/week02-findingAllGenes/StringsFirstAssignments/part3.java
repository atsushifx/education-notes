
/**
 * part3: solve string
 * 
 * @author atsushifx
 * @version 2022.01.06
 */
public class part3 {
    /**
     * in stringb, is stringa appears over two times
     */
    boolean twoOccurences(String keyword, String stringb) {
        int i1 = stringb.indexOf(keyword);
        if (i1 < 0) {
            return false;
        }
        
        int i2 = stringb.indexOf(keyword, i1+keyword.length());
        return (i2 >= 0);
    }
    
    /**
     * lastpart
     * get lastpart of string find by keyword
     */
    String lastpart(String key, String stringb)
    {
        int i1 = stringb.indexOf(key);
        if (i1 < 0) {
            return stringb;
        } else {
            String last;
            
            last = stringb.substring(i1+key.length());
            return last;
        }
        
    }
    
    
    /**
     * testing:
     * test twoOccurebces by several string pair
     * 
     */
    public void testing() {
        boolean f;
        String str;
        String last;
        
        System.out.println("\n\n part4:\n keyword occurs.\n");
        
        str = "A story by Abby Long";
        f = twoOccurences("by", str);
        System.out.println("String:"+str+"\t  return:"+(f ? "true" : "false"));

        str = "banana";
        f = twoOccurences("a", str);
        System.out.println("String:"+str+"\t  return:"+(f ? "true" : "false"));

        str = "ctgtatgta";
        f = twoOccurences("atg", str);
        System.out.println("String:"+str+"\t  return:"+(f ? "true" : "false"));

        System.out.println("\n\n test :lastpart function.\n");
        
        str = "banana";
        last = lastpart("an", str);
        System.out.println("String:"+str+"\t  Last:"+last);
        
        str = "forest";
        last = lastpart("zoo", str);
        System.out.println("String:"+str+"\t  Last:"+last);
    }
}
