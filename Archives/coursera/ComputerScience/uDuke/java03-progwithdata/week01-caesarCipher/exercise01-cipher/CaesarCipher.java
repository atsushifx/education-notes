import edu.duke.*;

/**
 * CaesarCipher
 *   シーザー暗号
 *   
 * @author  Furukawa Atsushi <atsushifx@gmail.com>
 * @version (バージョン番号もしくは日付)
 */
public class CaesarCipher
{
    /**
     * encrypt
     *   encrypt message by Caesar Cipher
     */
    String encrypt(String input, int key) {
        String upAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lwAlphabet = "abcdefghijklmnopqrstuvwxyz";
        
        String encUp = upAlphabet.substring(key) + upAlphabet.substring(0, key);
        String encLw = lwAlphabet.substring(key) + lwAlphabet.substring(0, key);
        
        // encrypt 作成
        StringBuilder buffer = new StringBuilder();
        for (int i=0; i<input.length(); i++) {
            Character ch = input.charAt(i);
            int idx1 = upAlphabet.indexOf(ch);
            if (idx1 >= 0) {
                ch = encUp.charAt(idx1);
            }
            int idx2 = lwAlphabet.indexOf(ch);
            if (idx2 >= 0) {
                ch = encLw.charAt(idx2);
            }
            buffer.append(ch);
        }
        
        return buffer.toString();
    }
    
        /**
     * encrypt
     *   encrypt message by Caesar Cipher
     */
    String encryptTwoKeys(String input, int key1, int key2) {
        String upALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lwALPHABET = "abcdefghijklmnopqrstuvwxyz";
        
        String encUp1 = upALPHABET.substring(key1) + upALPHABET.substring(0, key1);
        String encLw1 = lwALPHABET.substring(key1) + lwALPHABET.substring(0, key1);
        String encUp2 = upALPHABET.substring(key2) + upALPHABET.substring(0, key2);
        String encLw2 = lwALPHABET.substring(key2) + lwALPHABET.substring(0, key2);
        
        // encrypt 作成
        StringBuilder buffer = new StringBuilder();
        for (int i=0; i<input.length(); i++) {
            Character ch = input.charAt(i);
            int idx1 = upALPHABET.indexOf(ch);
            if (idx1 >= 0) {
                if (i %2 == 0) {
                    ch = encUp1.charAt(idx1);
                } else {
                    ch = encUp2.charAt(idx1);
                }
            }
            int idx2 = lwALPHABET.indexOf(ch);
            if (idx2 >= 0) {
                if (i %2 == 0) {
                    ch = encLw1.charAt(idx2);
                } else {
                    ch = encLw2.charAt(idx2);
                }
            }
            buffer.append(ch);
        }
        
        return buffer.toString();
    }
    
    /** 
     * testEncrypt1
     *   test encrypt by phrase
     */
    public void testEncrypt1() {
        String phrase;
        
        System.out.println("\n Caesar Cipher 1: test by phrase");
 
        phrase = "FIRST LEGION ATTACK EAST FLANK!";
        System.out.println(phrase +" enc:" + encrypt(phrase, 23));

        phrase = "First Legion";
        System.out.println(phrase +" enc:" + encrypt(phrase, 23));

        phrase = "First Legion";
        System.out.println(phrase +" enc:" + encrypt(phrase, 17));
    }
    
    /** 
     * testCaesar
     *   encrypt message from file
     */
    public void testCaesar() {
        System.out.println("\n Caesar Cipher 2: test by file");
        int key = 17;
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        String encMessage = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encMessage);
    }
    
    /** 
     * testEncrypt1
     *   test encrypt by phrase
     */
    public void testEncryptTwoKeys() {
        String phrase;
        
        System.out.println("\n Caesar Cipher 3: test by phrase, two keys");
 
        phrase = "First Legion";
        System.out.println(phrase +" to " + encryptTwoKeys(phrase, 23, 17));
    }
    
    /**
     * exam01 helper cipher phrase, ...
     */
    public void exam01() {
        System.out.println("\n  exam01");
        
        String phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        System.out.println(" q.5\n key is " + key + "\n" + encrypt(phrase, key));
    
        phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8; int key2 = 21;
        System.out.println(" q.6\n key is " + key1  +", " + key2 + "\n" + encryptTwoKeys(phrase, key1, key2));    
    }
}
