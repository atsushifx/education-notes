
/**
 * exam
 *   examination about: Telling a random story
 *   
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class exam
{
    //
    private WordFrequencies  WordFreqs;
    private CharactersInPlay  CharsinPlay;  
    private String Scenario = "../datas/likeit.txt";
    /**
     * exam クラスのインスタンスのためのコンストラクタ
     */
    public exam()
    {
        WordFreqs = new WordFrequencies();
        CharsinPlay = new CharactersInPlay();
    }


    public void exam1() {
        System.out.println("\n  q.1 -  count words.");
        WordFreqs.testerWithFile(Scenario);
    }
    
    /**
     * exam 2
     *   character play counts
     */
    public void exam2() {
        System.out.println("\n  q.4 -  character play count.");    
        System.out.println("Scenario:" + Scenario);
        
        CharsinPlay.testerwithFile(Scenario);
    }
}
