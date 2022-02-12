import edu.duke.*;
/**
 * CaesarBreaker
 * 
 * @author  Furukawa, Atsushi <atsushi@gmail.com>
 * @version 1.0.0
 * @date    2022-Feb-13
 */
public class CaesarBreaker
{
    /**
     * crack Caesar Cipher key and decrypt encrypted message
     */
    String decrypt(String encMessage) {
        int key = getKey(encMessage);
        CaesarCipher cc = new CaesarCipher();
        
        String message = cc.encrypt(encMessage, 26-key);
        return message;    
    }
    
    /**
     * crack Caesar Cipher two keys
     */
    String decryptTwoKeys(String encMessage) {
        int key1 = getKey(halfOfString(encMessage, 0));
        int key2 = getKey(halfOfString(encMessage, 1));
        
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encMessage, 26-key1, 26-key2);
        return message;
    }
    
    /**
     * divide message odd/even
     */
    String halfOfString(String message, int start) {
        StringBuilder buffer = new StringBuilder();
        
        int len = message.length();
        for (int i=0; i<len; i+=2) {
            int idx = i+start;
            if (idx<len) {
                buffer.append(message.charAt(idx));
            }
        }
        return buffer.toString();
    }
    /**
     * countLetters
     *   count letter to histgram
     */   
    int[] countLetters(String message) {
        final String ALPHABET="abvdefghijklmnopqrstuvwxyz";
        
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
    int maxIndex(int[] counts) {
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
        
    // test
    public void testDecrypt() {
        System.out.println("\n  1. decode one key caesar cipher");
        FileResource fr = new FileResource();
        String encMessage = fr.asString();
        String message = decrypt(encMessage);
        System.out.println("\nmessage\n" + message);
    }
    /**
     * test crack Caesar Cipher two keys
     */
    public void testDecryptTwoKeys() {
        System.out.println("\n  2. decode 2 keys caesar cipher");
        FileResource fr = new FileResource();
        String encMessage = fr.asString();
        String message = decryptTwoKeys(encMessage);
        System.out.println("\nmessage\n" + message);
    }

}
