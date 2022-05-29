
/**
 * CaesarCipherTwo
 *   encrypt/decrypt message Caesar Cipher with 2 keys
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class CaesarCipherTwo
{
    // Fields
    private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private String[] EncodedAlphabet;
    private int[] MainKey;
    
    /**
     *　Constructor
     *   create encode alphabet table and save key
     */
    CaesarCipherTwo(int key1, int key2) {
        MainKey = new int[2];
        EncodedAlphabet = new String[2];
        
        // key1
        key1 = (key1 + 26) % 26; // even(0 start)
        EncodedAlphabet[0] = ALPHABET.substring(key1) + ALPHABET.substring(0, key1);
        MainKey[0] = key1;
        
        // key2
        key2 = (key2 + 26) % 26; // even(0 start)
        EncodedAlphabet[1] = ALPHABET.substring(key2) + ALPHABET.substring(0, key2);
        MainKey[1] = key2;
    }
    
    /**
     * encrypt
     *   encrypt message by Carsar Cipher with two Keys
     */
    public String encrypt(String message) {
        StringBuilder buffer = new StringBuilder();
        int len = message.length();
        
        for (int i=0; i<len; i++) {
            char ch = message.charAt(i);
            ch = encodeChar(ch, i%2);
            buffer.append(ch);
        }
        return buffer.toString();
    }
    
    /**
     * decrypt
     *   decrypt messaage by Caesar Cipher with two keys
     */
    public String decrypt(String encryptMessage) {
        CaesarCipherTwo decoder = new CaesarCipherTwo(26 - MainKey[0], 26 - MainKey[1]);
        return decoder.encrypt(encryptMessage);
    }
    
    
    /**
     * encodeChar
     *   encrypt 1 character if char is ALPHABET
     */
    private char encodeChar(char ch, int index) {
        int idx1 = ALPHABET.indexOf(ch);
        int idx2 = ALPHABET.indexOf(Character.toUpperCase(ch));
        
        if (idx1 >= 0) {
            ch = EncodedAlphabet[index].charAt(idx1);
        } else if (idx2 >= 0) {
            ch = Character.toLowerCase(EncodedAlphabet[index].charAt(idx2));
        }
        return ch;
    }
}
