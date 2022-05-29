import edu.duke.*;
import java.util.Random;

/**
 * testCaesarCipher
 *   Caesar Cipher test
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class TestCaesarCipher
{
    private Random rand;
    
    TestCaesarCipher() {
        rand = new Random();
    }
    /**
     * messageTests
     *   test enctypt / decrypt from setred message
     *
     */
    public void messageTests() {
        System.out.println("\n  test.1 message encrypt/decrypt");
        CaesarCipher cc = new CaesarCipher(rand.nextInt(26));
        
        String orgMessage = "this is sample messasge for Caesar Cipher, Hello Atsushi!";
        String encMess = cc.encrypt(orgMessage);
        String decMess = cc.decrypt(encMess);
        
        System.out.println("Message\n"+orgMessage+"\n"+encMess+"\n"+decMess);
    }
    
    /**
     * simpleTests
     *   test message encrypt from file]
     * 
     */
    public void simpleTests() {
        System.out.println("\n  test.2  message from file");
        FileResource fr = new FileResource();
        
        CaesarCipher cc = new CaesarCipher(18);
        String message = fr.asString();
        String encMessage = cc.encrypt(message);
        String decMessage = cc.decrypt(encMessage);
        
        System.out.println("Message\n"+message+"\n"+encMessage+"\n"+decMessage);
    }
    
    /**
     * breakCipherTests
     *   test break Caesar Cipher
     * 
     */
    public void breakCipherTests() {
        System.out.println("\n  test.3  Caesar Cipher break test.");
        CaesarCipher cc = new CaesarCipher(rand.nextInt(26));
        CaesarBreaker cb = new CaesarBreaker();
        
        FileResource fr = new FileResource("datas/smallHamlet.txt");
        String message = fr.asString();
        String encMess = cc.encrypt(message);
        String breakMess = cb.decrypt(encMess);
        System.out.println("Message:\n"+message+"\n"+encMess+"\n"+breakMess);
    }
}


/**
 * CaesarBreaker
 *   creak encrypt message by Caesar Cipher
 */
class CaesarBreaker
{
    final String ALPHABET="abcdefghijklmnopqrstuvwxyz";
        
    /**
     * break
     *   crack Caesar Cipher key and decrypt encrypted message
     */
    String decrypt(String encMessage) {
        int key = getKey(encMessage);
        CaesarCipher cc = new CaesarCipher(key);
        
        String message = cc.decrypt(encMessage);
        return message;    
    }
    
    /**
     * countLetters
     *   count letter to histgram
     */   
    private int[] countLetters(String message) {
        
        int[] counts = new int[26];
        for (int i=0; i<message.length(); i++) {
            char c = Character.toLowerCase(message.charAt(i));
            int idx = ALPHABET.indexOf(c);
            if (idx >= 0) {
                counts[idx]++;
            }
        }
        return counts;
    }
    
    /**
     * maxIndex
     *   get max index from character number histgram
     */
    private int maxIndex(int[] counts) {
        int indexMax = -1;
        int max = -1;
        
        for (int i=0; i<counts.length; i++) {
            if ((indexMax<0)||(counts[i]>max)) {
                max = counts[i];
                indexMax = i;
            }
        }
        return indexMax;
    }
    
    /**
     * getKey
     *   calc caesar cipher key
     */
    int getKey(String encMessage) {
        int[] counts = countLetters(encMessage);      
        int index = maxIndex(counts);
        int key = (index - 4 + 26) % 26; // e
        
        return key;
    }
}
