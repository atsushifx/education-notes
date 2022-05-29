import edu.duke.*;

/**
 * exam1: Finding many genes
 *   
 * 
 * @author atsushifx
 * @date 2022.01.08
 * @version 1.0.0
 */
public class examFindingAllGenes
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
     * testFindGene
     */
    void exam1()
    {
        String dna, gene;
        
        System.out.println("\n\n exam1 start.");
        
        dna = "AATGCTAACTAGCTGACTAAT";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
    }



}
