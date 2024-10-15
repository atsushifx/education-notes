
/**
 * クラス part1 の注釈をここに書きます.
 * 
 * @author atsushifx
 * @version 1.0.0
 */
public class part1 {
    String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex < 0) {
            return "";
        }
        
        int endIndex = dna.indexOf("TAA", startIndex+3);
        if (endIndex < 0) {
            return "";
        }
        
        String gene = dna.substring(startIndex, endIndex+3);
        if ((gene.length() % 3) != 0) {
            return "";
        }
        return gene;
    }
    
    public void testSimpleGene() {
        String gene = null;
        String dna = ""; // null string
        
        System.out.println("\n\nTest find gene\n");
        
        dna = "";
        gene = findSimpleGene(dna);
        System.out.println("dna:" + dna + " found:" + gene);
        
        dna = "ATTAA";
        gene = findSimpleGene(dna);
        System.out.println("dna:" + dna + " found:" + gene);
        
        dna = "GAATGAAGCAA"; // only start codon 'ATG'
        gene = findSimpleGene(dna);
        System.out.println("dna:" + dna + " found:" + gene);
        
        dna = "AGCAATAA"; // only end codon 'TAA'
        gene = findSimpleGene(dna);
        System.out.println("dna:" + dna + " found:" + gene);

        dna = "AGATGCAGTAAAGT"; // valid gene
        gene = findSimpleGene(dna);
        System.out.println("dna:" + dna + " found:" + gene);

        dna = "AGATGCAGTTAAAGT"; // invalid gene
        gene = findSimpleGene(dna);
        System.out.println("dna:" + dna + " found:" + gene);
    }
}
