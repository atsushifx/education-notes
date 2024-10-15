
/**
 * CaesarBreakerTwo
 *   creak encrypt message by Caesar Cipher
 */
class CaesarBreakerTwo
{
    private String ALPHABET="abcdefghijklmnopqrstuvwxyz";
    private int key1;
    private int key2;
        
    /**
     * crackMessage
     *   crack Caesar Cipher key and decrypt encrypted message
     */
    String crackMessage(String encMessage) {
        key1 = getKey(splitMessage2Half(encMessage, 0));
        key2 = getKey(splitMessage2Half(encMessage, 1));
    
        CaesarCipherTwo decoder = new CaesarCipherTwo(key1, key2);
        String message = decoder.decrypt(encMessage);
        return message;    
    }
    
    /**
     * get crack key1, key2
     */
    int[] crackKeys() {
        int[] keys = new int[2];
        
        keys[0] = key1;
        keys[1] = key2;
        return keys;
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
