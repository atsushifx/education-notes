
/**
 * クラス CaesarCipher の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class CaesarCipher
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private String encodeAlphabet;
    private int mainKey;
    
    
    /**
     * CaesarCipher
     */
    public CaesarCipher(int key)
    {
        key = (key + 26) % 26; // 正規化
        encodeAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
        mainKey = key;
    }
    
    /**
     * encrypt
     *   encrypt message by Caesar Cipher METHOD;
     */
    public String encrypt(String input) {
        StringBuilder buffer = new StringBuilder();
        
        for (int i=0; i<input.length(); i++) {
            char ch = input.charAt(i);
            int idx1 = ALPHABET.indexOf(ch);
            int idx2 = ALPHABET.indexOf(Character.toUpperCase(ch));
            if (idx1 >= 0) {
                ch = encodeAlphabet.charAt(idx1);
            } else if (idx2 >= 0) {
                ch = Character.toLowerCase(encodeAlphabet.charAt(idx2));
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }
    
    /**
     * decrypt
     *   decrypt encrypt message by Caesar Cipher
     */
    public String decrypt(String encMessage) {
        CaesarCipher decoder = new CaesarCipher(26 - mainKey);
        return decoder.encrypt(encMessage);
    }
}
