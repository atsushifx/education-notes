
/**
 * DepthFilter
 *   filter quakes by depth
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class DepthFilter implements Filter
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private double depthMin;
    private double depthMax;

    /**
     * DepthFilter クラスのインスタンスのためのコンストラクタ
     */
    public DepthFilter(double min, double max)
    {
        depthMin = min;
        depthMax = max;
    }
    
    /**
     * satisfies
     *   depth filter
     */
    public boolean satisfies(QuakeEntry qe) {
        return ((depthMin <= qe.getDepth()) && (qe.getDepth() <= depthMax)); 
    }
    
    /**
     * get filter Name
     */
    public String getName() {
        return "Depth";
    }
}
