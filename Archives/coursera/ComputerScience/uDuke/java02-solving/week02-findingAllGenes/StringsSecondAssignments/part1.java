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
     * 
     */
    void testFindStopCodon()
    {
        //            01234567890123456789012
        String dna = "xxxyyTAGzTAAyyTAAxTAA";
        int pos = -1;
        boolean hasError = false;
        
        System.out.println("\n part1\n  start findStopCodonTest.");
        pos = findStopCodon(dna, 0, "TAA");
        if (pos != 9) {
            hasError = true;
            System.out.println("error on 1st codon:9 ");
        }
        
        pos = findStopCodon(dna, 9, "TAA");
        if (pos != 18) {
            hasError = true;
            System.out.println("error on 2nd codon 18: pos　= "+pos);
        }
    
        pos = findStopCodon(dna, 9, "TAG");
        if (pos != -1) {
            hasError = true;
            System.out.println("error on 3rd codon: TAG");
        }
        
        
        // end of test
        if (hasError) {
            System.out.println("test has error, quit");
        } else {
            System.out.println("test is finished.");
        }
    }

    /**
     * testFindGene
     */
    void testFindGene()
    {
        String dna, gene;
        boolean hasError = false;
        
        System.out.println("\n\n testFinGene start.");
        
        dna = "ATGCCCTAA";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
        if (gene.isEmpty()){ hasError = true; }
    
        // no start 
        dna = "ATCCTAATGA";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
        if (!gene.isEmpty()){ hasError = true; }
        
        // no stop
        dna = "ATGCTATGG";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
        if (!gene.isEmpty()){ hasError = true; }

        // no start
        dna = "TGCTATAGGTAA";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
        if (!gene.isEmpty()){ hasError = true; }
        
        
        // other stop with not 0 start
        dna = "GCATGCTATAG";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
        if (gene.isEmpty()){ hasError = true; }
        
        // other stop & fake stop
        dna = "ATGCTAAATTGATGC";
        gene = findGene(dna);
        System.out.println("DNA: " + dna + "\ngene:" + gene);
        if (gene.isEmpty()){ hasError = true; }        
        if (hasError) {
            System.out.println("error! test quit.");
        } else {
            System.out.println("test is finished.");
        }
    }
    
    /**
     * printAllGene 
     *   get all gene from dna & all print out
     */
    void printAllGene()
    {
        String dna = "ATGCCTTAAGGATGCATTGATATTAAATGCCCTATAAGTGAACG";
        
        String buff = dna;
        while (true) {
            String gene = findGene(buff);
            
            if (gene.isEmpty())  break;
            System.out.println("gene: " + gene);
            buff = buff.substring(gene.length());
        }
        
        
    }
}
