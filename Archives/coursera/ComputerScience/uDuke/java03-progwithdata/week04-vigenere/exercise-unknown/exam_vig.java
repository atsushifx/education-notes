import edu.duke.*;
import java.util.*;

/**
 * exam_vig
 *   examination: Breaking Vigenere cipher (multi language)
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class exam_vig
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private VigenereBreaker myCB;
    
    /**
     * exam_vig クラスのインスタンスのためのコンストラクタ
     */
    public exam_vig()
    {
        myCB = new VigenereBreaker();
    }

    /**
     * exam01
     *   test question: q.1 -
     */
    public void exam01() {
        System.out.println("\n test breaking vigenere cipher");
        
        // q.1
        System.out.println("\n  q.1,2 decrypt message3");
        FileResource fr = new FileResource("datas/secretmessage3.txt");
        myCB.breakVigenere(fr);
        
        // q.3, 4 
        System.out.println("\n  q 3,4  decrypt message 4");
        fr = new FileResource("datas/secretmessage4.txt");
        myCB.breakVigenere(fr);
        
    }
}
