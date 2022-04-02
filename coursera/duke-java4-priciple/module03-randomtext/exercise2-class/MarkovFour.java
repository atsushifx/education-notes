import java.util.*;

/**
 * MarkovFour
 *   create random text from markov four
 *   get character from predict 4-length string
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovFour extends AbstractMarkovModel {   
    /**
     * getRandomText
     *   generate random text with markov model
     */
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int mlen = 4;   // Markov Length
        StringBuilder sb = new StringBuilder();
        
        // first string
        int i = myRandom.nextInt(myText.length()-mlen);
        String key = myText.substring(i, i+mlen);
        sb.append(key);
        // next to end
        for(int k=mlen; k < numChars; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() <= 0)  break;
            String ch = follows.get(myRandom.nextInt(follows.size()));
            sb.append(ch);
            key = key.substring(1) + ch;
        }            
        return sb.toString();
    }
    
}
