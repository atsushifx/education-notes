import edu.duke.*;
import java.util.*;

/**
 * exam01
 *   exam01 - unknown key length exam
 *   
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class exam_ukl
{
    private VigenereBreaker Brk;
    private HashSet<String> Dictionary;
    private String          SecretMessage = "datas/secretmessage2.txt";
    
    /**
     * constructor
     */
    public exam_ukl() {
        Brk = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        Dictionary = Brk.readDictionary(fr);
    }
    
    public void exam1() {
        System.out.println("\n  exam break secret message.");
        char mostcommon = 'e';
        FileResource fr = new FileResource(SecretMessage);
        String encrypted = fr.asString();
        int[] ret = Brk.breakCipherwithDictionary(encrypted, Dictionary, mostcommon);
        System.out.println("Q.1,2 keylength: " + ret[1] + ", words:" + ret[0]);
        
        String decrypt = Brk.crackwithKey(encrypted, ret[1], mostcommon);
        System.out.println("Q.3 decrypt:\n"+decrypt.substring(0, 150));
    }
    
    public void exam2() {
        System.out.println("\n  exam count words.");
        char mostcommon = 'e';
        FileResource fr = new FileResource(SecretMessage);
        String encrypted = fr.asString();
        
        String tryDecrypt = Brk.crackwithKey(encrypted, 38, mostcommon);
        int cnt = Brk.countWords(tryDecrypt, Dictionary);
        System.out.println("q.4  count words: " + cnt);
    }
}

