import edu.duke.*;

/**
 * part3:
 *   processAllGene
 *   store All genes to Storage then print, cgRatio, longenst gene]
 *   
 * 
 * @author  atsushifx
 * @date    2022-01-09
 * @version 1.0.0
 *
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
     * cgRatio
     *   calc ratio by c,g in gene
     */
    double cgRatio(String gene)
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
        double r = (double)count / (double)size;
        return r;
    }
    
    /**
     * 
     */
    int countCTG(String dna)
    {
        int count = 0;
        int pos = 0;
        
        dna = dna.toUpperCase();
        while ((pos = dna.indexOf("CTG", pos)) != -1) {
            count++;
            pos += 3;
        }
        return count;
    }

    /**
     * 
     */
    private int countGene(StorageResource genesList, int len)
    {
        int count = 0;
        
        for (String gene : genesList.data()) {
            int l = gene.length();
            if (l >= len) {
                count++;
            }
        }
        return count;        
    }
   
    /**
     * 
     */
    private int countGeneRatio(StorageResource genesList, double ratio)
    {
        int count = 0;
        
        for (String gene : genesList.data()) {
            double r = cgRatio(gene);
           
            if (r >= ratio) {
                count++;
            }
        }
        return count;        
    }
    /**
     * 
     */
    private String getLongestGene(StorageResource genesList)
    {
        String longestGene = "";
        int maxLength = -1;
        
        for (String gene : genesList.data()) {
            int l = gene.length();
            
            if (l > maxLength) {
                maxLength = l;
                longestGene = gene;
            }
        }
        return longestGene;
    }
    
    /**
     * processGenes
     *   get gene from storage,
     *     then print all gene;
     */
    void processGenes(StorageResource geneList)
    {
        System.out.println("all gene");
        int n = 0;
        for (String gene : geneList.data()) {
            n++;
            System.out.println("["+n+"] "+gene);
        }
        
        // length >= 9
        System.out.println("gene in length > 9");
        int n1 = countGene(geneList, 9);
        System.out.println(n1);
        
        // ratio
        System.out.println("gene in cgRatio > 0.35");
        int n2 = countGeneRatio(geneList, 0.35);
        System.out.println(n2);
        
        System.out.println("longest gene");
        String gene = getLongestGene(geneList);
        System.out.println(gene);
        System.out.println(gene.length());
    }
    
    /**
     * testProcessGenes:
     *   read dna from file and get All genes,
     *   then process genes in list
     */
    void testProcessGenes()
    {
        String dna = "";
        StorageResource geneList;
        
        System.out.println("\n part 3\n  test process genes.\n");
        //
        dna = "atgtaa"; // length 6
        geneList = getAllGenes(dna);
        processGenes(geneList);
        
        dna = "taatgtttaggtagcc"; // length 12
        geneList = getAllGenes(dna);
        processGenes(geneList);
        
        dna = "atgtttatttaa"; // retio <= 0.35
        geneList = getAllGenes(dna);
        processGenes(geneList);

        dna = "atgagcccatga"; // retio > 0.35
        geneList = getAllGenes(dna);
        processGenes(geneList);

        dna = "atgcagtaattatgcgttttgcttgagt"; // max length
        geneList = getAllGenes(dna);
        processGenes(geneList);
        System.out.println("\n  test finished.");
    }
    
    /**
     * 
     */
    void processGenes2(StorageResource geneList)
    {
        // length >= 9
        System.out.println("All gene");
        System.out.println(countGene(geneList, 0));
        
        System.out.println("gene in length >= 60");
        int n1 = countGene(geneList, 60);
        System.out.println(n1);
        
        System.out.println("gene in cgRatio > 0.35");
        int n2 = countGeneRatio(geneList, 0.35);
        System.out.println(n2);
        
        System.out.println("longest gene");
        String gene = getLongestGene(geneList);
        
        System.out.println(gene);
        System.out.println(gene.length());
    }
    
    /**
     * testRealGene
     *   get real dna from file, and output gene
     *   process (lerngth no longer than 60)
     */ 
    void testProcessRealGenes()
    {
         FileResource fr = new FileResource("brca1line.fa");
         String dna = fr.asString();
         StorageResource geneList = getAllGenes(dna);
    
         int n = 0;
         
         processGenes2(geneList);
    }
}