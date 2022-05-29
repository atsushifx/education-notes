/**
 * findingGene:
 *   for exam of find a gene from DNA(string)
 * 
 * @author atsushifx
 * @version 1.0.0
 */
public class findingGene {
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
    
    public void testing() {
        String startCodon = "ATG";
        String endCodon = "TAA";
        
        String gene = null;
        String dna = "";
        
        System.out.println("\n\nTest find gene\n");
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC"; // from test
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("dna:" + dna + "\t found:" + gene);
        
    }
}
