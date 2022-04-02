import java.util.*;

/**
 * MarkovOne
 *   create random text markov one model
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovOne implements IMarkovModel
{
    private String myText = null;
    private Random myRandom;
    
    /**
     * constructor
     */
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
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
        int i = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(i, i+1);
        sb.append(key);
        for(int k=0; k < numChars; k++) {
            ArrayList<String> follows = getFollows(key);
            String ch = follows.get(myRandom.nextInt(follows.size()));
            sb.append(ch);
            key = ch;
        }            
        return sb.toString();
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
            if (++spos >= myText.length()) break;
            follows.add(myText.substring(spos, spos+1));
            index = spos;
        }
        return follows;
    }
}
