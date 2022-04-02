import java.util.*;

/**
 * MarkovModel
 *   generate random text from markov model
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovModel extends AbstractMarkovModel {
    
    // property
    private int myMarkov;
    
    /**
     * constructor
     */
    public MarkovModel(int n) {
        super();
        myMarkov = n;
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
            if (follows.isEmpty()) {
                break;
            }
            int index = myRandom.nextInt(follows.size());
            String ch = follows.get(index);
            sb.append(ch);
            key = key.substring(1) + ch;
        }            
        return sb.toString();
    }
}
