import java.util.*;

/**
 * EfficientMarkovModel
 *   MarkovModel efficiently with HashMap
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    // property
    private int myMarkov;
    private HashMap<String, ArrayList<String>> myMarkovCache;
    
    /**
     * constructor
     */
    public EfficientMarkovModel(int n) {
        super();
        myMarkov = n;
        myMarkovCache = new HashMap<String, ArrayList<String>>();
    }
    
    /**
     * setTraining
     *   set training text for Markov model
     *   
     */
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
    }
    // Markov mode
    /**
     * getRandomText
     *   generate random text with markov model
     */
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        // first string
        int i = myRandom.nextInt(myText.length()-myMarkov);
        String key = myText.substring(i, i + myMarkov);
        sb.append(key);
        
        // next to end
        for(int k=myMarkov; k<numChars; k++) {
            ArrayList<String> follows = getFollows(key);    
            if (follows==null || follows.isEmpty()) {
                break;
            }
            int index = myRandom.nextInt(follows.size());
            String ch = follows.get(index);
            sb.append(ch);
            key = key.substring(1) + ch;
        }            
        return sb.toString();
    }
    
    /**
     * toString
     */
    public String toString() {
        return toStringwithOrder(myMarkov);
    }
    
    // override getFollows
    /**
     * getFollows
     *   get follows(markov model) from cache
     */
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> f = myMarkovCache.get(key);
        return f;
    }
    
    
    // using HashMap
    /**
     * buildMap
     *   build markov model map
     */
    public void buildMap() {
        myMarkovCache.clear();
        for (int i=0; i<myText.length(); i++) {
            int i2 = i + myMarkov;
            if (i2 > myText.length()){ break; }
            String key = myText.substring(i, i2);
            
            buildMaponekey(key);
            // printHashMapInfo(); 
        }
    }
    
    /**
     * buildMaponekey
     *   build Markov model map with key
     */
    private void buildMaponekey(String key) {
        if (myMarkovCache.containsKey(key)) {
            return;
        }
        ArrayList<String> follows = super.getFollows(key);
        myMarkovCache.put(key, follows);
    }
    
    
    /**
     * printHashMapInfo
     *   print out myMarkovCache information
     */
    public void printHashMapInfo() {
        System.out.println("\n  -- markov cache info --");
        if (false) {
            for(String key: myMarkovCache.keySet()){
                System.out.println(key + " : " + myMarkovCache.get(key));
            }
        }
        System.out.println("number of keys : " + myMarkovCache.size());
        System.out.println("largest value  : " + getLargestValue());
        System.out.println("largest key    : " + getLargestKey());
    }
    
    /**
     * getLargestValue
     * 　　get largest value from myMarkovCache
     */
    private int getLargestValue() {
        int max = 0;
        for (ArrayList<String> f : myMarkovCache.values()) {
            if (f.size() > max) {
                max = f.size();
            }
        }
        return max;
    }
    
    /**
     * getLargestKey
     * 　　get largest value from myMarkovCache
     */
    private ArrayList<String> getLargestKey() {
        ArrayList<String> maxKey = new ArrayList<String>();
        int max = getLargestValue();
        for (String key : myMarkovCache.keySet()) {
            ArrayList<String> f = myMarkovCache.get(key);
            if (f.size() == max) {
                maxKey.add(key);
            }
        }
        return maxKey;
    }
}
