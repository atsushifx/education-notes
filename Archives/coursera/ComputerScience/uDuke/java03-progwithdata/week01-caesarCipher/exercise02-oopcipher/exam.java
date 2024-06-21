import edu.duke.*;

/**
 * exam
 *   make answer for week01 examination
 *   
 * @author  Furukawa Atsushi
 * @version 1.0.0
 * @date    2022-Feb-14
 */
public class exam
{
    /**
     * answer1
     *   exam for cryptgtraphtの答え q.1-q.7
     * 
     */
    public void answers1() {
        System.out.println("\n  exam for cryptgraphy");
        
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encMess = cc.encrypt(message);
        System.out.println("\n  q.1  message encrypt.\n"+message + "\n" + encMess);
    
        message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwo cc2 = new CaesarCipherTwo(21, 8);
        encMess = cc2.encrypt(message);
        System.out.println("\n  q.2  encrypt with 2 keys.\n"+message + "\n" + encMess);
        
        System.out.println("\n  q.4 count words.");
        WordLength counter = new WordLength();
        counter.countFile("errors.txt");
        
        System.out.println("\n  q.5 count words.");
        counter.countFile("manywords.txt");
        
        encMess = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        cc2 = new CaesarCipherTwo(14, 24);
        message = cc2.decrypt(encMess);
        System.out.println("\n  q.6 decrypt cipher\n"+encMess+"\n"+message);
        
        encMess = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        CaesarBreakerTwo cb2 = new CaesarBreakerTwo();
        message = cb2.crackMessage(encMess);
        System.out.println("\n  q.7 crack caesar cipher\n"+encMess+"\n"+message);
        
    }
    
    /**
     * answers2
     *   exam for cryptgtraphtの答え q.8 -
     * 
     */
    public void answers2() {
        FileResource fr = new FileResource("datas/mysteryTwoKeysQuiz.txt");
        String encMess = fr.asString();
        CaesarBreakerTwo cb2 = new CaesarBreakerTwo(); 
        String message = cb2.crackMessage(encMess);
        
        System.out.println("\n  q.8 mystery Quiz..\n"+message);
        
        int[] keys = cb2.crackKeys();
        System.out.println("\n  q.9 crack key\n key = "+keys[0]+", "+keys[1]);
    }
}
