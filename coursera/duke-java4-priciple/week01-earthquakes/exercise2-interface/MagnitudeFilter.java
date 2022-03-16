
/**
 * MagnitudeFilter
 *   filter quakes by magnitude min and max
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class MagnitudeFilter implements Filter
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private double magMin;
    private double magMax;

    /**
     * MagnitudeFilter
     *   constructor : set min and max magnitude
     */
    public MagnitudeFilter(double min, double max)
    {
        magMin = min;
        magMax = max;
    }
    
    /**
     * satisfies
     *   magnitude filter
     */
    public boolean satisfies(QuakeEntry qe) {
        return ((magMin <= qe.getMagnitude()) && (qe.getMagnitude() <= magMax)); 
    }
    
    /**
     * get filter name
     */
    public String getName() {
        return "Magnitude";
    }
}
