import java.util.*;
import edu.duke.*;
/**
 * examine1
 *   get answer for examination : word N-Gram
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class examine1
{
    //
    MarkovRunner myRunner;
    
    /**
     * constructor
     */
    examine1() {
        myRunner = new MarkovRunner();
    }
    
    /**
     * exam1
     *  answer : q.1-
     *  
     */
    public void exam1() {
        System.out.println("\n q.1 get text MarkovWordOne");
        
        String file = "data/confucius.txt";
        FileResource fr = new FileResource(file);
        String trText = fr.asString().replace('\n', ' ');
        int size = 120;
        
        MarkovWordOne m1 = new MarkovWordOne();
        myRunner.runModel(m1, trText, size, 139);
        
        // q.2
        System.out.println("\n q.2 MarkovWordOne indexOf method");
        myRunner.testIndexOf(); 
        
        // q.3
        System.out.println("\n q.3 generate text using MarkovWordTwo");
        MarkovWordTwo m2 = new MarkovWordTwo();
        myRunner.runModel(m2, trText, size, 832);
    }
}
