import edu.duke.*;


/**
 * findAllGenes:テスト用DNAファイルからgeneを複数取得
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class findAllGenes
{
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
    
    void testing()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        String gene = "";
        
        gene = findSimpleGene(dna, "ATG", "TAA");
        int n = 0;
        int pos = -1;
        while (gene != "") {
            n = n + 1;
            System.out.println("gene ["+n+"]:" + gene);
            
            pos = dna.indexOf(gene);
            if (pos >= 0) {
                pos += gene.length();
                dna = dna.substring(pos);
            }
            gene = findSimpleGene(dna, "ATG", "TAA");
        }
        
    }
}
