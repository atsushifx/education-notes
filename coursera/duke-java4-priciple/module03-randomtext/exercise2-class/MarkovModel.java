import java.util.*;

/**
 * MarkovModel
 *   generate random text from markov model
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovModel implements IMarkovModel
{
    // property
    private String myText = null;
    private Random myRandom;
    private int myMarkov;
    
    /**
     * constructor
     */
    public MarkovModel(int n) {
        myRandom = new Random();
        myMarkov = n;
    }
    
    /**
     * set random
     *   set Random seed
     */
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    /**
     * setTraining
     *   training text for markov model
     */
    public void setTraining(String s){
        myText = s.trim();
    }

    // Markov model
    /**
     * getFollows
     *   create follow character List for string key
     */
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = 0;
        while (index < myText.length()) {
            int spos = myText.indexOf(key, index);
            if (spos < 0)   break;
            spos += myMarkov;
            if (spos >= myText.length()) break;
            follows.add(myText.substring(spos, spos+1));
            index = spos;
        }
        return follows;
    }
    
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
        String key = myText.substring(i, i+myMarkov);
        sb.append(key);
        // next to end
        for(int k=myMarkov; k<numChars; k++) {
            ArrayList<String> follows = getFollows(key);
            String ch = follows.get(myRandom.nextInt(follows.size()));
            sb.append(ch);
            key = key.substring(1) + ch;
        }            
        return sb.toString();
    }
}
