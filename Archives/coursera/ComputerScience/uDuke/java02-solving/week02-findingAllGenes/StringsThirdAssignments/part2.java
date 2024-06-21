
/**
 * part2:
 *   calc cgRatio, countCTG
 * 
 * @author  atsushifx
 * @date    2022-01-09
 * @version 1.0.0
 *
 */
public class part2
{
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
        
        while ((pos = dna.indexOf("CTG", pos)) != -1) {
            count++;
            pos += 3;
        }
        return count;
    }
    /**
     * cgRatio Test
     */
    void testCgRatio()
    {
        System.out.println("\n\n test cgRatio");
        
        String gene = "ATGCCATAG";
        double r = cgRatio(gene);
        
        System.out.println("gene: "+ gene + " cgRatio=" + r);
    }

    /**
     * count CTG test
     *   count ctg number in DNA
     */
    void testCountCTG()
    {
        System.out.println("\n\n part2\n  test count CTG");
        
        String dna = "ATGCCACCTGGGCTGCTGTAG"; // expect 3
        int cnt = countCTG(dna);
        
        System.out.println("dna: "+ dna + "\n  ctg = " + cnt);
        
        System.out.println("\n test finished.");
    }
}
