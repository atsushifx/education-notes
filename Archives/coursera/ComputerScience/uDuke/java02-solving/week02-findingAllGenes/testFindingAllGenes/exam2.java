
/**
 * クラス exam2 の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class exam2
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
        /**
     * 
     */
    void testExam2()
    {
        String dna = "CTGCCTGCATGATCGTA";
        int pos = dna.indexOf("TG");
        int count = 0;
        
        System.out.println("\n\n Exam2: count 'TG'.");
        while (pos >= 0) {
            count++;
            pos = dna.indexOf("TG", pos); // error find same pos
            
            if (count > 10)  break;
        }
        System.out.println(count);
    }

   
}
