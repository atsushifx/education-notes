import edu.duke.*;

/**
 * part1: Finding many genes
 *   wrire code: findStopCodon
 * 
 * @author atsushifx
 * @version 2022.01.08
 */
public class part1
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
    String findGene(String dna, int searchPos)
    {
        int startIndex = dna.indexOf("ATG", searchPos);
        
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
     * getAllGene 
     *   get all gene from dna & all print out
     */
    StorageResource getAllGenes(String dna)
    {
        StorageResource genesList = new StorageResource();
        
        String buff = dna.toUpperCase();
        int index = 0;
        while (true) {
            String gene = findGene(buff, index);
            
            if (gene.isEmpty())  break;
            genesList.add(gene);
            index = buff.indexOf(gene, index) + gene.length();
        }
        return genesList;
    }
    
        
    /**
     * testAllGenes
    */
    void testAllGenes()
    {
        String dna = "ATGCCTTAAGGATGCATTGATATTAAATGCCCTATAAGTGAACG";
        
        System.out.println("\n\n test get All genes.");
        StorageResource genes = getAllGenes(dna);
        for (String g : genes.data()) {
            System.out.println("gene: " + g);
        }
        System.out.println("\n test finished.");
    }
}
