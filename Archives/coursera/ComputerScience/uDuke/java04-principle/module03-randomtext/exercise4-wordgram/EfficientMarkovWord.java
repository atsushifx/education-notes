import java.util.*;
/**
 * EfficientMarkovWord
 *   Markov Model with hash map for efficiently
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class EfficientMarkovWord implements IMarkovModel {
    // property
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap = null;
    
    /**
     * constructor
     */
    public EfficientMarkovWord(int n) {
        myOrder = n;
        myRandom = new Random();
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.replace('\n', ' ').split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder); // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    
    /**
     * getFollows
     */
    private ArrayList<String> getFollows(WordGram key) {
        if (!myMap.containsKey(key)) {
            buildMapwithKey(key);
        }
        return myMap.get(key);
    }
    
    /**
     * getFollowsforMap
     *   generate markov model by key 
     *   to build hash map
     *   
     */
    private ArrayList<String> getFollowsforMap(WordGram key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int i = indexOf(myText, key, pos);
            if (i < 0){  break; }
            int i2 = i + myOrder;
            if (i2 >= myText.length) { break; }
            follows.add(myText[i2]);
            pos = i + 1;
        }
        return follows;
    }

    /**
     * indexOf
     *   search key from String words[]
     * 
     * @return int index (-1: not found)
     */
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i=start; i<words.length-myOrder+1; i++) {
            WordGram wg = new WordGram(words, i, myOrder);
            if (wg.equals(target)) {
                return i;
            }
        }   
        return -1;
    }
    
    // hashmap
    /**
     * buildMap
     *   create HashMap for getFollows(later use)
     * 
     */
    public void buildMap() {
        myMap = new HashMap<WordGram, ArrayList<String>>();
        // System.out.println("text size: " + myText.length);
        for (int i=0; i<=myText.length ; i++) {
            if (i+myOrder >= myText.length){ break; }
            WordGram key = new WordGram(myText, i, myOrder);
            buildMapwithKey(key);        
        }
    }
    
    /**
     * 
     */
    private void buildMapwithKey(WordGram key) {
        if (myMap.containsKey(key)) {
            return;
        }
        ArrayList<String> f = getFollowsforMap(key);
        myMap.put(key, f);
    }
    
    /**
     * print created hash map information
     */
    public void printHashMapInfo() {
        if (myMap == null) {
            System.out.println("myMap is null");
            return;
        }
        
        System.out.println("\n  -- Hash Map Info --");
        if (myMap.size() <= 10) {
            for (WordGram key : myMap.keySet()) {
                ArrayList<String> f = myMap.get(key);
                System.out.println(key + "\t: " + f);
            }
        }
        System.out.println("number of keys\t: " + myMap.size());
        System.out.println("largest value\t: " + getLargestValue());
        System.out.println("largest keys\t: " + getLargestKeys());
        System.out.println("\n");
    }
    
    /**
     * getLargestValue
     */
    private int getLargestValue() {
        int max = 0;
        for (ArrayList<String> f : myMap.values()) {
            if (f.size() > max) {
                max = f.size();
            }
        }
        return max;
    }
    
    /**
     * getLargestKeys
     *   get key list with max size value
     */
    private ArrayList<WordGram> getLargestKeys() {
        ArrayList<WordGram> ret = new ArrayList<WordGram>();
        int max = getLargestValue();
        
        for (WordGram key : myMap.keySet()) {
            ArrayList<String> f = myMap.get(key);
            if (f.size() == max) {
                ret.add(key);
            }
        }
        return ret;
    }
}
