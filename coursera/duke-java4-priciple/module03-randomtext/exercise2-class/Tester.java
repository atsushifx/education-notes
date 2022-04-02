import java.util.*;
import edu.duke.*;

/**
 * Tester
 *  test method
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class Tester
{
    /**
     * testGetFollows
     *   test getFollows get valid markov model
     */
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        System.out.println("\n  test get follows");
        
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
        
        follows = markov.getFollows("th");
        System.out.println(follows);
    }
    
    /**
     * testGetFollowsWithFile
     *   test get follows, traing text from file
     */
    public void testGetFollowsWithFile() {
        AbstractMarkovModel markov = new MarkovModel(3);
        System.out.println("\n  test get follows with file");
        
        // traing with file
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        
        ArrayList<String> follows = markov.getFollows("am ");
        System.out.println(follows.size());
        
    }
}
