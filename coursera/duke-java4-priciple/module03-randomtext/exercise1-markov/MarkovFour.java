import java.util.*;

/**
 * MarkovFour
 *   create random text from markov four
 *   get character from predict 4-length string
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovFour
{
    private String myText = null;
    private Random myRandom;
    
    /**
     * constructor
     */
    public MarkovFour() {
        myRandom = new Random();
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
            int pos2 = spos + key.length();
            if (pos2 >= myText.length()) break;
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
        int mlen = 4;   // Markov Length
        StringBuilder sb = new StringBuilder();
        
        // first string
        int i = myRandom.nextInt(myText.length()-mlen);
        String key = myText.substring(i, i+mlen);
        sb.append(key);
        // next to end
        for(int k=mlen; k < numChars; k++) {
            ArrayList<String> follows = getFollows(key);
            String ch = follows.get(myRandom.nextInt(follows.size()));
            sb.append(ch);
            key = key.substring(1) + ch;
        }            
        return sb.toString();
    }
    
}
