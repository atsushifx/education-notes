
/**
 * exam2:
 *  solve mystery dna function
 *   
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class exam2
{
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
      
        if (pos == -1) {
            return dna;
        }
        
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }
    
    
    /**
     * testing
     *   call mystery & output new dna
     */
    void testing()
    {
        String dna,newdna;
        System.out.println("\n\n Exam2 mystery DNA.\n");
        
        dna = "ACCGAC";
        newdna = mystery(dna);
        System.out.println("dna: "+ dna + " => " + newdna);
        
        dna = "ATGCCGAC";
        newdna = mystery(dna);
        System.out.println("dna: "+ dna + " => " + newdna);
        
        dna = "ACGTTCGAC";
        newdna = mystery(dna);
        System.out.println("dna: "+ dna + " => " + newdna);
        
        dna = "ACGTAGTGAGTAG";
        newdna = mystery(dna);
        System.out.println("dna: "+ dna + " => " + newdna);
        
        dna = "ACGTTAGTGATAA";
        newdna = mystery(dna);
        System.out.println("dna: "+ dna + " => " + newdna);
  
        System.out.println("\n\n test finished.");
    }
}
