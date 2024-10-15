
/**
 * exam 3
 * modified exam 2; use with nr
 * 
 * @author (あなた�?�名前)
 * @version (バ�?�ジョン番号もしく�?�日�?)
 */
public class exam3
{
    // インスタンス変数 - コードに合わせて説明を書き換えま�?.
    /**
     * testExam 3: add code & test exam
     */
    void testExam32()
    {
        String dna = "CTGCCTGCATGATCGTA";
        int pos = dna.indexOf("TG");
        int count = 0;
        String newDNA = "";
        System.out.println("\n\n Exam2: count 'TG'.");
        
        while (count < 3) {
          count += 1;
          newDna = newDna + dna.substring(startPos,pos);
          startPos = pos+1;
          pos = dna.indexOf("T", startPos);
          if (pos == -1) {
            break;
          }
        }
    }
}
        
        
        \
   
}
