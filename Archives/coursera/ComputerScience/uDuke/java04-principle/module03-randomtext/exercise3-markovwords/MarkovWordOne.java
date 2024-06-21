
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.replace('\n', ' ').split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            // debug: print follows 
            // System.out.println(key + " : " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    /**
     * getFollows
     *   generate markov model by key
     */
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int i = indexOf(myText, key, pos);
            if (i < 0){  break; }
            int i2 = i + 1;
            if (i2 >=myText.length) { break; }
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
    private int indexOf(String[] words, String target, int start) {
        for (int i=start; i<words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Tester
    /**
     * testIndexOf
     *   test indexOf method works valid
     */
    public void testIndexOf() {
        
        String training = "this is just a test yes this is a simple test";
        setTraining(training);
        
        System.out.println("\n -- test indexOf method");
        int pos = 0;
        pos = indexOf(myText, "this", 0);
        System.out.println("this in start 0 " + pos);
        
        pos = indexOf(myText, "this", 3);
        System.out.println("this in start 3 " + pos);
        
        pos = indexOf(myText, "frog", 0);
        System.out.println("frog in start 0 " + pos);
        
        pos = indexOf(myText, "test", 5);
        System.out.println("test in start 5 " + pos);
        
    }
    
}
