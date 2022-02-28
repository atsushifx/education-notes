import edu.duke.*;

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
     * testEncrypt
     *   vigenere cipher encrypt
     */
    public void testEcrypt() {
        System.out.println("\n  test  vigenere encrypt.");
        
        FileResource fr = new FileResource("../testdatas/titus-small.txt");
        int key[] = {17, 14, 12, 4}; // ROME
        
        VigenereCipher enc = new VigenereCipher(key);
        System.out.println("key:" + enc);
        System.out.println(enc.encrypt(fr.asString()));
    }
    
    /**
     * testSliceString
     */
    /*
    public void testSliceString() {
        String orgMessage="abcdefghijklm";
        VigenereBreaker cr = new VigenereBreaker();
        
        System.out.println("\n  test  slice string.");
        
        String slicedMessage = cr.sliceString(orgMessage, 0, 3);
        System.out.println("sliced [0,3]\n" + slicedMessage); // expect adgjm
        
        slicedMessage = cr.sliceString(orgMessage, 3, 4);
        System.out.println("sliced [3, 4]\n" + slicedMessage);
        
        slicedMessage = cr.sliceString(orgMessage, 1, 5);
        System.out.println("sliced [1, 5]\n" + slicedMessage);
    }
    */
   
    /**
     * testTryKeyLength
     */
    /*
    public void testTryKeyLength() {
        System.out.println("test try Key Length");
        FileResource fr = new FileResource("../testdatas/athens_keyflute.txt");
        VigenereBreaker cr = new VigenereBreaker();
        
        int[] keys = cr.tryKeyLength(fr.asString(), 5, 'e');
        System.out.println("crack athens_keyflute\n");
        for (int i=0; i<keys.length; i++) {
            System.out.print(keys[i]+" ");
        }
        System.out.println();
    }
    */
    
    /**
     * testBreakVinegere
     * 
     */
    public void testBreakVinegere() {
        System.out.println("\ntest -- break Vinegere cipher");
        FileResource fr = new FileResource();
        
        VigenereBreaker cr = new VigenereBreaker();
        String message = cr.crackwithKey(fr.asString(), 4, 'e');
        message = message.substring(0, 100);
        System.out.println("cracked\n" + cr + "\n" + message);
    }
}
