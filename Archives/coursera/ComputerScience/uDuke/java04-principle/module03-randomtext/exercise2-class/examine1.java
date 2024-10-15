import java.util.*;
import edu.duke.*;

/**
 * examine
 *   get answer for interface and class exam
 *   
 * @author  Furukawa, Atsushi
 * @versionq1.0.0
 */
public class examine1 {
    /**
     * exam1
     *   q.3 - 
     */
    public void exam1() {
        String file = "data/romeo.txt";
        FileResource fr = new FileResource(file);
        String trainingText = fr.asString().replace('\n', ' ');
        int size = 200;
        
        // q.3 hashmap 
        System.out.println("\n  q.3 - hashMapInfo");
        EfficientMarkovModel m1 = new EfficientMarkovModel(5);
        m1.setRandom(615);
        runModel(m1, trainingText, size);
        m1.printHashMapInfo();
    }
    
    /**
     * runModel
     *   generate and printout text
     */
    public long runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        // markov.setRandom(218);
        System.out.println("running with " + markov);
        long startTime = System.nanoTime();
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    /**
     * printout
     *   printout generated text
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
