import edu.duke.*;
import java.util.*;

/**
 * tester
 *   test vignerer cipher encrypt & crack
 *   
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class tester
{
    /**
     * testReadDictionary
     * 
     */
    public void testReadDictionary() {
        System.out.println("\ntest -- create dictionary hash set");
        
        FileResource fr = new FileResource("dictionaries/English");
        VigenereBreaker cr = new VigenereBreaker();
        
        HashSet<String> dics = cr.readDictionary(fr);
        System.out.println("Dictionary:\n" + dics.size());
    }
   
    /**
     * testMostCommon
     *   get most common char from dictionary
     */
    public void testMostCommon() {
        System.out.println("\ntest -- get most common char from dictionary.");
        FileResource fr = new FileResource("dictionaries/English");
        VigenereBreaker cr = new VigenereBreaker();
        
        HashSet<String> dics = cr.readDictionary(fr);
        
        char mostcommon = cr.mostCommonCharln(dics);
    }
    
    /**
     * testAllLangs
     */
    public void testAllLangs() {
        System.out.println("\ntest -- test decrypt by all langs.");
        VigenereBreaker cr = new VigenereBreaker();
        FileResource fr = new FileResource("testdatas/athens_keyflute.txt");
        
        HashMap<String, Integer> languages = new HashMap<String, Integer>();
        String decrypt = cr.breakForAllLangs(fr.asString(), languages);
        System.out.println("lang/count\n" + languages);
        System.out.println("\n decrypt:\n" + decrypt.substring(0, 150));
    }
    
        
    /**
     * testBreakVigenere
     *   test count readble words
     */
    /*
    public void testBreakVigenere() {
        System.out.println("\ntest -- break cipher with dictionary");
        
        VigenereBreaker cr = new VigenereBreaker();
        FileResource fr = new FileResource("testdatas/athens_keyflute.txt");
        
        String decrypt = cr.breakVigenere(fr);
        decrypt = decrypt.substring(0, 300);
        
        System.out.println("break cipher:\n" + decrypt);
    }
    */
   
}
