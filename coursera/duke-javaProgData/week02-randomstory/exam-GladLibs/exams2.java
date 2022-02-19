import edu.duke.*;
import java.io.*;
import java.util.*;

/**
 * exams2
 *   examination : Gladlibs
 *   
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class exams2
{
    /**
     * shakespeare refer
     */
    private static String ScenarioFile = "data/errors.txt";
    
    /**
     * exam1
     */
    public void exam1() {
        System.out.println("\n  -- Shakespeare count --");
        
        WordFrequencies wf = new WordFrequencies();
        System.out.println("\n q.3 Word Freqs");
        wf.testerWithFile(ScenarioFile);
        
        CharactersInPlay cip = new CharactersInPlay();
        cip.findCharactersinFile(ScenarioFile);
        cip.charactersWithNumParts(50, 0);
        
        System.out.println("\n  q.9 char in 10 - 15");
        cip.charactersWithNumParts(10, 15);
    }
    
    /**CodonCounter
     * exam2 codon
     */
    public void exam2() {
        System.out.println("\n  q.9 - dnaMystery2");
        FileResource fr = new FileResource("data/dnaMystery2");
        String dna = fr.asString();
        
        CodonCounter cc = new CodonCounter();
        
        System.out.println("\n q.9 unique codons");
        cc.buildCodonMap(1, dna);
        System.out.println("unique codons: " + cc.getUniqueCodons());
        
        System.out.println("\n q.10 common coddon");
        cc.buildCodonMap(2, dna);
        System.out.println("common codons: " + cc.getMostCommonCodon());
        
        System.out.println("\n q.10 coddon list");
        cc.buildCodonMap(0, dna);
        cc.printCodonCounts(7, 7);
    }
    
    /**
     * exam3 
     * word in Files
     * 
     */
    public void exam3() {
        System.out.println("\n -- Shakespeare");
        DirectoryResource dr = new DirectoryResource();
        WordsInFiles wif = new WordsInFiles();
        
        wif.buildWordFileMap(dr);
        
        System.out.println("\n  q.12: occur words in 7");
        ArrayList<String> words = wif.wordsInNumFiles(7);
        System.out.println("all files(7) = " + words.size());
        
        System.out.println("\n  q.13: occur words in 4 files");
        words = wif.wordsInNumFiles(4);
        System.out.println("4 files = " + words.size());
        
        System.out.println("\n  q.14 word not appear");
        wif.printFilesIn("sea");
        
        System.out.println("\n  q.15 word appear");
        wif.printFilesIn("tree");
    }
}
