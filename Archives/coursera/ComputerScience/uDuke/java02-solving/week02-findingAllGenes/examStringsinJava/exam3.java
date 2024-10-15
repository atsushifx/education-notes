import edu.duke.*;

/**
 * exam3
 *   processAllGene
 *   store All genes to Storage then print, cgRatio, longenst gene]
 *   
 * 
 * @author  atsushifx
 * @date    2022-01-09
 * @version 1.0.0
 *
 */
public class exam3
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
        return -1; // not found
    }

    /**
     * stopIndexDNA:
     *   get minimum index from 3 stop codon's index 
     */
    private int stopIndexDNA(String dna, int startIndex)
    {
        int stopIndex = -1;
        int index;
        
        index = findStopCodon(dna, startIndex, "TAA");
        stopIndex = index;
        if (index != -1) {
            if ((stopIndex==-1)||(index < stopIndex)) {
                stopIndex = index;
            }
        }
        
        index = findStopCodon(dna, startIndex, "TAG");
        if (index != -1) {
            if ((stopIndex==-1)||(index < stopIndex)) {
                stopIndex = index;
            }
        }
        
        index = findStopCodon(dna, startIndex, "TGA");
        if (index != -1) {
            if ((stopIndex==-1)||(index < stopIndex)) {
                stopIndex = index;
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
     * getAllGenes:
     *   get all gene from dna & all print out
     */
    StorageResource getAllGenes(String dna)
    {
        StorageResource genesList = new StorageResource();
        
        String buff = dna.toUpperCase();
        int index = 0;
        while (true) {
            String gene = findGene(buff, index);
            
            if (gene.isEmpty()){ 
                break;
            }
        
            genesList.add(gene);
            index = dna.indexOf(gene, index) + gene.length();
        }
        return genesList;
    }
    
    
    /**
     * cgRatio
     *   calc ratio by c,g in gene
     */
    float cgRatio(String gene)
    {
        int i;
        int count = 0;
        int size = gene.length();        
        
        // count c,g
        for (i=0; i<size; i++) {
            char c = gene.charAt(i);
            if (c=='C'||c=='G') {
                count++;
            }
        }
        return ((float)count) / size;
    }
    
    /**
     * 
     */
    int countCTG(String dna)
    {
        int count = 0;
        int pos = 0;
        
        while ((pos = dna.indexOf("CTG", pos)) != -1) {
            count++;
            pos += 3;
        }
        return count;
    }
    
    // for Process genes method
    /**
     * count genes number from genes resource
     *   if gene length longer than len
     */
    private int getGenesCount(StorageResource genes, int len)
    {
        int n = 0;
        for (String g : genes.data()) {
            if (g.length() > len) {
                n++;
            }
        }
        return n;
    }
    
    /**
     * 
     */
    private int getGenesCountRatio(StorageResource genes, float ratio)
    {
        int n = 0;
        
        for (String gene : genes.data()) {
            float r = cgRatio(gene);
            if (r > ratio) {
                n++;
                // System.out.println("["+r+"] "+gene);
            }
        }
        return n;
    }
    
    
    /**
     * get longest gene from genes storage resource
     */
    private String getLongestGene(StorageResource genes)
    {
        String longestGene = "";
        int maxLength = -1;
        
        for (String g: genes.data()) {
            int l = g.length();
            if (maxLength < l) {
                maxLength = l;
                longestGene = g;
            }
        }
        return longestGene;
        
    }
    
    /**
     * processGenes
     *   get gene from storage,
     *     then print all gene;
     */
    void processGenes(String dna)
    {
        StorageResource genes = getAllGenes(dna);
        
        System.out.println("Genes Number");
        int n1 = getGenesCount(genes, 0);
        System.out.println(n1);
    
        
        System.out.println("\ngene in length > 60");
        int n2 = getGenesCount(genes, 60);
        System.out.println(n2);
        
        System.out.println("gene in cgRatio > 0.35");
        int n3 = getGenesCountRatio(genes, 0.35f);
        System.out.println(n3);
        
        System.out.println("\n count CTG");
        System.out.println(countCTG(dna));
        
        System.out.println("\n longest gene");
        String lgene = getLongestGene(genes);
        System.out.println(lgene);
        System.out.println(lgene.length());
    }
        
    
    /**
     * testRealGene
     *   get real dna from file, and output gene
     *   process (lerngth no longer than 60)
     */ 
    void testProcessRealGenes()
    {
         FileResource fr = new FileResource("GRch38dnapart.fa");
         String dna = fr.asString();
         
         processGenes(dna);
    }
}