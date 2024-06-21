
/**
 * part2:
 *   count keyword occurs in Strings: how many counts
 *   
 * @author atsushifx
 * @version 2022.01.08
 */
public class part2
{

    /**
     * 
     */
    int howMany(String keyword, String str)
    {
        int count = 0;
        int pos = 0;
        
        while (true) {
            pos = str.indexOf(keyword, pos);
            if (pos == -1) { break; }
            count++;
            pos += keyword.length();
        }
        return count;
    }
    
    /**
     * testHowMany:
     *  test howmany method execue validty
     */
    void testHowMany()
    {
        boolean hasError = false;
        int count;
        String str, keyword;
        
        
        System.out.println("\n part2\n test  howMany.");
        
        str = "ATGAACGAATTGAATC";
        keyword = "GAA";
        count = howMany(keyword, str);
        System.out.println("count "+keyword+" in "+str);
        if (count != 3){ hasError = true; }
        
        str = "ATAAAA";
        keyword = "AA";
        count = howMany(keyword, str);
        System.out.println("count "+keyword+" in "+str);
        if (count != 2){ System.out.println("no overlap."); hasError = true; }
        
        if (hasError) {
            System.out.println("Error. test quit.");
        } else {
            System.out.println("Test is finshed");
        }
    }
}
