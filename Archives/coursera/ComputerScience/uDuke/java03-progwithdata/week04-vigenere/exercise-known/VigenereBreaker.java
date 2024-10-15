import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private int[] myKeys = null;   // crack key
     
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder buff = new StringBuilder();
        int len = message.length();
        for (int i=whichSlice; i<len; i += totalSlices) {
            if (i < len) {
                buff.append(message.substring(i, i+1));
            }
        }
        return buff.toString();
    }    
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] keys = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for (int i=0; i<klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            int k = cc.getKey(sliced);
            keys[i] = k;
        }
        return keys;
    }
    
    /**
     * crackwithKey
     */
    String crackwithKey(String encrypted, int klength, char mostCommon) {
        myKeys = tryKeyLength(encrypted, klength, mostCommon);
        
        VigenereCipher dec = new VigenereCipher(myKeys);
        return dec.decrypt(encrypted);
    }
    
    public void breakVigenere() {
        
    }
    
    
    /**
     * output crack keys[]
     */
    public String toString() {
        if (myKeys == null) {
            return "";
        }
        
        StringBuilder buff = new StringBuilder();
        for (int i=0; i<myKeys.length; i++) {
            if (i > 0) {
                buff.append(" ");
            }
            buff.append(""+myKeys[i]);
        }
        return buff.toString();
    }
}
