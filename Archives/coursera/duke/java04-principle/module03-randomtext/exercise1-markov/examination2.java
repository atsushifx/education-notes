import java.util.*;
import edu.duke.*;

/**
 * examination2
 *   get answer for examination : N-Gram predict
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class examination2 {
    /**
     * exam1
     *   q.1 - 
     */
    public void exam1() {
        String file = "data/confucius.txt";
        int size = 350;
        int seed = 1024;
        
        System.out.println("\n q.2 get text using MarkovZero");
        // runMarkovZero(file, seed);
        
        /*
        System.out.println("\n q.3 testGetFollows");
        ArrayList<String> f = testGetFollowsWithFile("o");
        System.out.println("Follows('o') is " + f.size());
        
        System.out.println("\n q.4 testGetFollows (2)");
        f = testGetFollowsWithFile("he");
        System.out.println("Folloes('he') is " + f.size());
        */
        System.out.println("\n q.5 get text : MarkovOne");
        file = "data/romeo.txt";
        seed = 365;
        //runMarkovOne(file, size, seed);
        
        System.out.println("\n q.6 get text : MarkovFour");
        file = "data/romeo.txt";
        seed = 715;
        //runMarkovFour(file, size, seed);
        
        System.out.println("\n q.7 get text : MarkovModel");
        file = "data/romeo.txt";
        seed = 953;
        runMarkovModel(file, size, seed, 7);
        
    }
    
    /**
     * tester methods
     */
    private void runMarkovZero(String file, int seed) {
        FileResource fr = new FileResource(file);
        String trText = fr.asString().replace('\n', ' ');
        
        MarkovZero m0 = new MarkovZero();
        m0.setTraining(trText);
        m0.setRandom(seed);
        for(int k=0; k < 3; k++){
            String text = m0.getRandomText(100);
            printOut(text);
        }
    }
    
    /**
     * runMarkovOne
     *    create random text using MarkovOne
     */
    private void runMarkovOne(String file, int size, int seed) {
        MarkovOne markov = new MarkovOne();
        
        FileResource fr = new FileResource(file);
        String st = fr.asString().replace('\n', ' ');
        markov.setTraining(st);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(size);
            printOut(text);
        }   
    }
    
    /**
     * runMarkovFour
     *    create random text using MarkovFour
     */
    private void runMarkovFour(String file, int size, int seed) {
        MarkovFour markov = new MarkovFour();
         
        // training
        FileResource fr = new FileResource(file);
        String st = fr.asString().replace('\n', ' ');
        markov.setTraining(st);
        markov.setRandom(seed);
        for(int k=0; k < 1; k++){
            String text = markov.getRandomText(size);
            printOut(text);
        }
    }
    
    
    /**
     * runMarkovModel
     *    create random text using MarkoModel
     */
    private void runMarkovModel(String file, int size, int seed, int order) {
        MarkovModel markov = new MarkovModel(order);
        
        // training
        FileResource fr = new FileResource(file);
        String st = fr.asString().replace('\n', ' ');
        markov.setTraining(st);
        
        // generate
        markov.setRandom(seed);
        for(int k=0; k < 1; k++){
            String text = markov.getRandomText(size);
            printOut(text);
        }   
 
    }
    /**
     * testGetFollowsWithFile
     *   test get follows, traing text from file
     */
    private ArrayList<String> testGetFollowsWithFile(String key) {
        // System.out.println("\n  test get follows with file");
        
        // traing with file
        String file = "data/confucius.txt";
        FileResource fr = new FileResource(file);
        String st = fr.asString().replace('\n', ' ');
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows(key);
        return follows;
    }
    
    /**
     * printout
     *   printout generate text
     */
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
