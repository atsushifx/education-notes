/**
 * クラス part2
 * find gene like part1 but start,end codon as parameter
 * 
 * @author atsushifx
 * @version 1.0.0
 */
public class part2 {
    String findSimpleGene(String dna, String startCodon, String endCodon) {
        char ch = '\0';
        boolean isUpper = true; //
        
        if (dna != "") {
            ch = dna.charAt(0);
            isUpper = ('A'<=ch)&&(ch<='Z');
        }
        
        startCodon = startCodon.toUpperCase();
        endCodon = endCodon.toUpperCase();
        dna = dna.toUpperCase();
           
        int startIndex = dna.indexOf(startCodon);
        if (startIndex < 0) {
            return "";
        }
        
        int endIndex = dna.indexOf(endCodon, startIndex+startCodon.length());
        if (endIndex < 0) {
            return "";
        }
        
        String gene = dna.substring(startIndex, endIndex+endCodon.length());
        if ((gene.length() % 3) != 0) {
            return "";
        }
        
        if (isUpper) {
            gene = gene.toUpperCase();
        } else {
            gene = gene.toLowerCase();
        }
        return gene;
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String endCodon = "TAA";
        
        String gene = null;
        String dna = ""; // null string
        
        System.out.println("\n\nTest find gene\n");
        
        dna = "";
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);
        
        dna = "ATTAA";
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);
        
        dna = "GAATGAAGCAA"; // only start codon 'ATG'
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);
        
        dna = "AGCAATAA"; // only end codon 'TAA'
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);

        dna = "ATGGGTTAAGTC"; // valid gene
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);
        
        dna = "gatgctataat"; // valid gene (lower)
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);

        dna = "AGATGCAGTTAAAGT"; // invalid gene
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);
    }
}
