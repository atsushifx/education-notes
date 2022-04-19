
/**
 * クラス PhraseFilter の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class PhraseFilter implements Filter
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private String SearchPhrase;
    private String Indicates;   // start, end, any
    
    /**
     * PhraseFilter クラスのインスタンスのためのコンストラクタ
     */
    public PhraseFilter(String phrase, String indicates)
    {
       SearchPhrase = phrase;
       Indicates = indicates;
    }

    /**
     * satisfies
     *   filter quakes by phrase
     */
    public boolean satisfies(QuakeEntry qe) {
        String info = qe.getInfo();
        int idx = info.indexOf(SearchPhrase);
        return (Indicates=="start" && idx==0) ||
                    (Indicates=="end" && idx==info.length()-SearchPhrase.length()) ||
                    (Indicates=="any" && idx>=0);
    }
    
    /**
     * get filter name
     */
    public String getName() {
        return "Phrase";
    }
}
