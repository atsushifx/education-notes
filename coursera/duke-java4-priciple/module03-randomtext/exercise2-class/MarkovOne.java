import java.util.*;

/**
 * MarkovOne
 *   create random text markov one model
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovOne extends AbstractMarkovModel {
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
    
    /**
     * toString
     */
    public String toString() {
        return toStringwithOrder(1);
    }
}
