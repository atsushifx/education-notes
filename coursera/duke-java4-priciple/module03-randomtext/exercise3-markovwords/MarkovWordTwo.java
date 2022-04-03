import java.util.*;
/**
 * MarkovWordTwo
 *  generate text by Markov model 2 words
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovWordTwo implements IMarkovModel {
    // property
    private String[] myText;
    private Random myRandom;
    
    /**
     * constructor
     */
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    /**
     * setRandom
     *   set random with seed
     */
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    /**
     * setTraining
     *   set Training text
     */
    public void setTraining(String text){
        myText = text.replace('\n', ' ').split("\\s+");
    }
    
    /**
     * getRandomText
     *   generate random text by Markov word two model
     *   
     */
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key1 = myText[index];
        //index = myRandom.nextInt(myText.length);  // random word to start with
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        
        for(int k=2; k < numWords; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            // debug: print follows 
            // System.out.println("[" + key1 + ", " + key2  + "] : " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    /**
     * getFollows
     *   generate markov model by key
     */
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int i = indexOf(myText, key1, key2, pos);
            if (i < 0){  break; }
            int i2 = i + 2;
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
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i=start; i<words.length-1; i++) {
            if (words[i].equals(target1) && words[i+1].equals(target2)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * testGetFollows
     */
    public void testGetFollows() {
        System.out.println("\n -- test get follows with two words");
        setTraining("this is just a test yes this is a simple test");
        ArrayList<String> f;
        
        f = getFollows("this", "is");
        System.out.println("[this is] : " + f);
        
        f = getFollows("is", "a");
        System.out.println("[is a] : " + f);
    }
}
