import java.util.*;

/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    /**
     * constructor
     * 
     */
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    /**
     * setTraining
     *   set training text for Markov model
     *   
     */
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    /**
     * getRandomText
     *   generate random text using Markov Model
     */
    abstract public String getRandomText(int numChars);

    /**
     * getFollows
     *   create follow character List for string key
     */
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()) {
            int index = myText.indexOf(key, pos);
            if (index < 0)   break;
            int i2 = index + key.length();
            if (i2 >= myText.length()) break;
            String ch = myText.substring(i2, i2+1);
            follows.add(ch);
            pos = index + 1;
        }
        return follows;
    }

}
