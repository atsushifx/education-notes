import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private int[] myKeys = null;   // crack key
    private HashMap<String, HashSet<String>> myDics; // dictionary list for each langs
    
    /**
     * constructor
     *   initialize dictionary
     */
    public VigenereBreaker() {
        ArrayList<String> myLangs = new ArrayList(Arrays.asList("English", "French", "Danish", "German", "Dutch", "Italian", "Portuguese", "Spanish"));
        myDics = new HashMap<String, HashSet<String>>();
        for (String lang : myLangs) {
            FileResource fr = new FileResource("dictionaries/" + lang);
            HashSet<String> dic = readDictionary(fr);
            myDics.put(lang, dic);
        }
    }
    
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
     * breakVigenere
     */
    public void breakVigenere() {
        FileResource fr = new FileResource();  // dialog
        HashMap<String, Integer> languages = new HashMap<String, Integer>();
        
        String decrypt = breakForAllLangs(fr.asString(), languages);
        System.out.println("lang/count\n" + languages);
        System.out.println("\n decrypt:\n" + decrypt.substring(0, 300));
    }
    
    /**
     * crackwithKey
     */
    String crackwithKey(String encrypted, int klength, char mostCommon) {
        myKeys = tryKeyLength(encrypted, klength, mostCommon);
        
        VigenereCipher dec = new VigenereCipher(myKeys);
        return dec.decrypt(encrypted);
    }
    
    /**
     * breakForAllLangs
     */
    public String breakForAllLangs(String encrypted, HashMap<String, Integer> languages) {
        int max =0;
        String encLang = "";
        for (String lang : myDics.keySet() ) {
            int[] ret = breakCipherwithDictionary(encrypted, myDics.get(lang));
            languages.put(lang,ret[0]);
            if (max <= 0 || ret[0]>max) {
                max = ret[0];
                encLang = lang;
            }
        }
        String decrypt = breakForLanguage(encrypted, myDics.get(encLang));
        return decrypt;
    }
    
    
    
    /**
     * breakForLanguage
     *   break cipher 1-100 key length
     */
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) 
    {
        char mostcommon = mostCommonCharln(dictionary);
        int[] ret = breakCipherwithDictionary(encrypted, dictionary);
        return crackwithKey(encrypted, ret[1], mostcommon);
    }
    
    /**
     * 
     */
    int[] breakCipherwithDictionary(String encrypted, HashSet<String> dictionary) {
        char mostcommon = mostCommonCharln(dictionary);
        
        int wordMax = 0;
        int keylength = 0;
        
        for (int i=1; i<=100; i++) {
            String tryDecrypt = crackwithKey(encrypted, i, mostcommon);
            
            int count = countWords(tryDecrypt, dictionary);
            if (count > 0) {
               if (wordMax==0 || count>wordMax) {
                   wordMax = count;
                   keylength = i;
                }
            }
        }
        
        // 複数リターン組立た
        int[] ret = new int[2];
        ret[0] = wordMax;
        ret[1] = keylength;
        return ret;
    }
    /**
     * readDictionary
     *   read Dictionary and set hash to keyword list
     */
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dics = new HashSet<String>();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (!dics.contains(word)) {
                dics.add(word);
            }
        }
        return dics;
    }
    
    /**
     * countWords
     *   count readable word from message ( check from dictionary)
     */
    public int countWords(String message, HashSet<String> dictionary) {
        String[] wordList = message.split("\\W+");
        int count = 0;
        
        for (int i=0; i<wordList.length; i++) {
            String word = wordList[i].toLowerCase();
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * mostCommonChaln
     */
    public char mostCommonCharln(HashSet<String> dictionary) {
        int[] counter = new int[256];
        int max = 0;
        char commonCh = '\0';
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char ch = Character.toLowerCase(word.charAt(i));
                
                if (Character.isLowerCase(ch)) {
                    counter[ch]++;
                    if (max<=0 || counter[ch]>max) {
                        max = counter[ch];
                        commonCh = ch;
                    }
                }
            }
        }
        return commonCh;
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
