import edu.duke.*;

/**
 * exams
 *   for exams: execute question related class 
 *   　 and get answers
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class exams
{
    private CodonCounter myCC;
    
    /**
     * constructor
     * 
     */
    exams() {
       myCC = new CodonCounter(); 
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
}
