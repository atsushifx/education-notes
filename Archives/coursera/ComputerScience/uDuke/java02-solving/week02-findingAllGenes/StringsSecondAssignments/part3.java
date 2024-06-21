import edu.duke.*;

/**
 * part3: How Many Gene?
 *   count gene in dna
 * 
 * @author atsushifx
 * @version 2022.01.08
 */
public class part3
{
    /**
     * findStopCodon
     *   get stopCodon index from dna
     *   valid index : index multiple 3
     *
     * @return index : stopCodon Position
     *         -1    : can't find stopCodon 
     */
    private int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int index = dna.indexOf(stopCodon, startIndex + 3);
        
        while (index != -1) {
            int diff = index - startIndex;
            if ((diff % 3) == 0) {
                return index;
            }
            index = dna.indexOf(stopCodon, index + 1);
        }
        return -1;
    }

    /**
     * stopIndexDNA:
     *   get minimum index from 3 stop codon's index 
     */
    private int stopIndexDNA(String dna, int startIndex)
    {
        int stopIndex = -1;
        int idx;
        
        idx = findStopCodon(dna, startIndex, "TAA");
        if (idx != -1) {
            if ((stopIndex==-1)||(stopIndex > idx)) {
                stopIndex = idx;
            }
        }
        idx = findStopCodon(dna, startIndex, "TAG");
        if (idx != -1) {
            if ((stopIndex==-1)||(stopIndex > idx)) {
                stopIndex = idx;
            }
        }
        idx = findStopCodon(dna, startIndex, "TGA");
        if (idx != -1) {
            if ((stopIndex==-1)||(stopIndex > idx)) {
                stopIndex = idx;
            }
        }
        
        return stopIndex;
    }
    
    /**
     * findGene
     *   get gene from DNA:
     *     3 stop codon can use
     */
    String findGene(String dna)
    {
        int startIndex = dna.indexOf("ATG");
        
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = stopIndexDNA(dna, startIndex);
        if (stopIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);
    }
    

    /**
     * printAllGene 
     *   get all gene from dna & all print out
     */
    int countGenes(String dna)
    {    
        String buff = dna;
        int count = 0;
        
        while (true) {
            String gene = findGene(buff);
            
            if (gene.isEmpty())  break;
            
            count++;
            buff = buff.substring(gene.length());
        }
        return count;
    }
    
    
    /**
     * testCountGenes
     * check number of genes in DNA
     * and it is valid 
     */
    void testCountGenes()
    {
        boolean hasError = false;
        int count;   
        String dna;
        
        System.out.println("\n\n part 3\n  testCountGenes.");
        
        dna = "ATGTAAGATGCCCTAGT";
        count = countGenes(dna);
        if (count != 2){ System.out.println("DNA: "+dna); hasError = true; }
                
        dna = "ATGTAAGTATGTTAAT";
        count = countGenes(dna);
        if (count != 1){ System.out.println("DNA: "+dna); hasError = true; }
        
        
        dna = "GATATGTTAAGT";
        count = countGenes(dna);
        if (count != 0){ System.out.println("DNA: "+dna); hasError = true; }
                
        // other stop & fake stop
        if (hasError) {
            System.out.println("error! test quit.");
        } else {
            System.out.println("test is finished.");
        }
    }    
}
