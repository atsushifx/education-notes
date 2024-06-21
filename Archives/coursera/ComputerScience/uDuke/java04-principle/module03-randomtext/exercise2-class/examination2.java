import edu.duke.*;

/**
 * examination2
 *   get answer for Predictive Test
 *   
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class examination2 {
    // prop
    MarkovRunnerWithInterface myRunner;
    
    // constructor
    public examination2() {
        myRunner = new MarkovRunnerWithInterface();
    }

    /**
     * exam
     *   get answer : q.9 , 10
     */
    public void exam() {
        System.out.println("\n  q.9 hashMapInfo (number of keys)");
        String file = "data/confucius.txt";
        FileResource fr = new FileResource(file);
        String trText = fr.asString().replace('\n', ' ');
        int size = 200;
        int seed = 792;
        // 
        EfficientMarkovModel m = new EfficientMarkovModel(6);
        m.setTraining(trText);
        m.printHashMapInfo();
        
        System.out.println("\n  q.10 hashMapInfo (largest)");
        m = new EfficientMarkovModel(5);
        seed = 531;
        m.setTraining(trText);
        m.printHashMapInfo();
        
    }
}
