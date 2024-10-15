import java.util.*;
import edu.duke.*;

/**
 * examination1
 *   get answer for examinmation random text
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class examination1
{
    /**
     * exam1
     *   for q.1 - 4
     */
    public void exam1() { 
        MarkovZero markov = new MarkovZero(); 
        
        System.out.println("\n  q.2 run markov 0");
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        markov.setTraining(st);
        markov.setRandom(88);
        String text = markov.getRandomText(500); 
        printOut(text); 
        
        // q.3
        System.out.println("\n  q.3 getFollows with File");
        String file = "data/melville.txt";
        ArrayList<String> follows = testGetFollowsWithFile(file, "o");
        
        // q.4
        System.out.println("\n  q.4 getFollows with File");
        file = "data/melville.txt";
        follows = testGetFollowsWithFile(file, "th");
        
    }

    /**
     * exam2
     *   for q.5 - 7
     */
    public void exam2() { 
        System.out.println("\n  q.5 - text with MarkovOne");
        MarkovOne m1 = new MarkovOne();
        
        String file = "data/confucius.txt";
        FileResource fr = new FileResource(file);
        m1.setTraining(fr.asString().replace('\n', ' '));
        m1.setRandom(273);
        String text = m1.getRandomText(500);
        printOut(text);
         
        // q.6 
        System.out.println("\n  q.6 - text with MarkovFour");
        MarkovFour m4 = new MarkovFour();
        file = "data/confucius.txt";
        fr = new FileResource(file);
        m4.setTraining(fr.asString().replace('\n', ' '));
        m4.setRandom(371);
        text = m4.getRandomText(500);
        printOut(text);

        // q.7 
        System.out.println("\n  q.7 - text with MarkovModel");
        MarkovModel mm = new MarkovModel(8);
        file = "data/confucius.txt";
        fr = new FileResource(file);
        mm.setTraining(fr.asString().replace('\n', ' '));
        mm.setRandom(365);
        text = mm.getRandomText(500);
        printOut(text);
 
    }
    
    /**
     * testGetFollowsWithFile
     *   test get follows, traing text from files
     */
    private ArrayList<String> testGetFollowsWithFile(String file, String key) {
        MarkovOne markov = new MarkovOne();
        System.out.println("\n  test get follows with file");
        
        // traing with file
        FileResource fr = new FileResource(file);
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        
        ArrayList<String> follows = markov.getFollows(key);
        System.out.println(follows.size());
        return follows;
    }
    
    /**
     * printout
     *   printout generate text
     */
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        // System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
                return ;
            }
        }
        System.out.println("\n----------------------------------");
    }
}

