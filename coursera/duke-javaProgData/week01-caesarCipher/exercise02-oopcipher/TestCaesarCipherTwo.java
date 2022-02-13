import edu.duke.*;
import java.util.Random;

/**
 * testCaesarCipher
 *   
 *
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class TestCaesarCipherTwo
{
    // for get random key
    private Random rand;

    /**
     * constructor
     * 
     */
    public TestCaesarCipherTwo()
    {
        rand = new Random();
    }

    /**
     * messageTests
     *   test enctypt / decrypt from setred message
     *
     */
    public void messageTests() {
        System.out.println("\n  test.1 message encrypt/decrypt");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(rand.nextInt(26), rand.nextInt(26));
        
        String message = "This is messager for Caesar Cipher two, encrypt with two keys.";
        String encMess = cc2.encrypt(message);
        String decMess = cc2.decrypt(encMess);
        
        System.out.println("message\n"+message+"\n"+encMess+"\n"+decMess);
    }

    /**
     * simpleTests
     *   test enctypt / decrypt message from file
     *
     */
    public void simpleTests() {
        System.out.println("\n  test.2 encrypt/decrypt test from file");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(rand.nextInt(26), rand.nextInt(26));
        
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        String encMess = cc2.encrypt(message);
        String decMess = cc2.decrypt(encMess);
        
        System.out.println("message\n"+message+"\n"+encMess+"\n"+decMess);
    }
    
    /**
     * crackTests
     *   test crack Caesar Cipher two keys
     *
     */
    public void crackTests() {
        System.out.println("\n  test.2 encrypt/decrypt test from file");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(rand.nextInt(26), rand.nextInt(26));
        CaesarBreaker cb2 = new CaesarBreaker();
        
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        String encMess = cc2.encrypt(message);
        String crackMess = cb2.crackMessage(encMess);
        
        System.out.println("message\n"+message+"\n"+encMess+"\n"+crackMess);
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
    String crackMessage(String encMessage) {
        int key1 = getKey(splitMessage2Half(encMessage, 0));
        int key2 = getKey(splitMessage2Half(encMessage, 1));
        
        CaesarCipherTwo decoder = new CaesarCipherTwo(key1, key2);
        String message = decoder.decrypt(encMessage);
        return message;    
    }
    
    /**
     * splitMessage2Half
     *   split message by odd / even : start (0/1)
     *   
     */
    private String splitMessage2Half(String message, int start) {
        StringBuilder buffer = new StringBuilder();
        
        int len = message.length();
        for (int i=start; i<len; i+=2) {
            if (i<len) {
                buffer.append(message.charAt(i));
            }
        }
        return buffer.toString();
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
    
}
