import java.util.*;

/**
 * MarkovWord
 *   generate random text using Markov N model
 * 
 * @author  Furukawa, Atsushi 
 * @version 1.0.0
 */
public class MarkovWord implements IMarkovModel {
    // property
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    /**
     * constructor
     */
    public MarkovWord(int n) {
        myOrder = n;
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
        int index = myRandom.nextInt(myText.length-myOrder); // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        
        for(int k=0; k < numWords-myOrder; k++){
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
            key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    /**
     * getFollows
     *   generate markov model by key
     *   
     */
    private ArrayList<String> getFollows(WordGram key) {
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
    
    // Tester
    /**
     * testIndexOf
     *   test indexOf method works valid
     */
    public void testIndexOf() {
        
        String training = "this is just a test yes this is a simple test";
        setTraining(training);
        myOrder = 2;
        String[] targetWords = {"this", "is"};
        WordGram target = new WordGram(targetWords, 0, 2);
        
        System.out.println("\n -- test indexOf method");
        int pos = 0;
        pos = indexOf(myText, target, 0);
        System.out.println("[" + target + "] in start 0 " + pos);
        
        pos = indexOf(myText, target, 3);
        System.out.println("[" + target + "] in start 3 " + pos);
        
        String[] targetWords2 = {"this", "frog"};
        target = new WordGram(targetWords2, 0, 2);
        
        pos = indexOf(myText, target, 0);
        System.out.println("[" + target + "] in start 0 " + pos);
               
    }
    
}
