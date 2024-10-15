import edu.duke.*;

/**
 * examination1
 *   get answer for examination : WordGram
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class examination1
{
    // property
    MarkovRunner myRunner;

    /**
     * constructor
     */
    public examination1() {
        myRunner = new MarkovRunner();
    }
    
    /**
     * exam1
     *   q.1 -
     */
    public void exam1() {
        System.out.println("\n  -- exam for WordGram -- ");
        String file = "data/confucius.txt";
        FileResource fr = new FileResource(file);
        String trText = fr.asString().replace('\n', ' ');
        int size = 200;
        
        // q.1
        System.out.println("\n q.1 get text MarkovWord");
        MarkovWord m1 = new MarkovWord(3);
        //myRunner.runModel(m1, trText, size, 621);
        
        // q.2
        System.out.println("\n q.2 get text MarkovWord");
        m1 = new MarkovWord(5);
        //myRunner.runModel(m1, trText, size, 844);
        
        // q.3 
        System.out.println("\n q.3 Hash Map Info (num of key)");
        EfficientMarkovWord m2 = new EfficientMarkovWord(3);
        myRunner.runModel(m2, trText, size, 371);
        
        // q.4
        System.out.println("\n q.4 Hash Map Info (larget follow)");
        m2 = new EfficientMarkovWord(2);
        //myRunner.runModel(m2, trText, size, 65);
    }
}
