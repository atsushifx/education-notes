import java.io.*;
import java.util.*;
import edu.duke.*;

/**
 * exams1
 *   for exams: execute question related class 
 *   　 and get answers
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class exams1
{
    // for test class
    private CodonCounter myCC;
    private WordsInFiles myWiF;
    
    /**
     * constructor
     * 
     */
    exams1() {
       myCC = new CodonCounter();
       myWiF = new WordsInFiles();
    }
    
    
    /**
     * exam1 -- codon
     */
    public void exam1() {
        System.out.println("\n  q.1- Codons");
        FileResource fr = new FileResource("data/dnaMystery1");
        String dna = fr.asString();
        
        System.out.println("\n  q.1 common codon");
        System.out.println(myCC.mostCommonCodonList(dna));
        
        System.out.println("\n  q.2 4 times");
        myCC.buildCodonMap(2, dna);
        myCC.printCodonCounts(4, 30);
        
        System.out.println("\n  q.3 6 times");
        myCC.buildCodonMap(1, dna);
        myCC.printCodonCounts(6, 30);
 
    }
    
    private int wordCount(DirectoryResource dr) {
        WordFrequencies wf = new WordFrequencies();
        
        for (File f : dr.selectedFiles()) {
            wf.findUniqueinFile(f);
        }
        return wf.getWordCount();
    }
    /**
     * exam2 -- word Freqs
     */
    public void exam2() {
        System.out.println("\n -- q.4 -- count words");
        DirectoryResource dr = new DirectoryResource();
        
        System.out.println("\n  q.4 unique words count");
        System.out.println("# unique words: "+ wordCount(dr));
    }
    
    
    public void exam3() {
        System.out.println("\n -- q.4 -- Shakespeare");
        DirectoryResource dr = new DirectoryResource();
    
        myWiF.buildWordFileMap(dr);
        
        System.out.println("\n  q.4");
        ArrayList<String> words = myWiF.wordsInNumFiles(5);
        System.out.println("files 5 = " + words.size());        
        
        System.out.println("\n  q.5");
        words = myWiF.wordsInNumFiles(4);
        System.out.println("files 4 = " + words.size());
        
        System.out.println("\n  q.6 keyword contain (sad)");
        myWiF.printFilesIn("sad");
        
        System.out.println("\n  q.7 keyword contain (red)");
        myWiF.printFilesIn("red");
    }
}


