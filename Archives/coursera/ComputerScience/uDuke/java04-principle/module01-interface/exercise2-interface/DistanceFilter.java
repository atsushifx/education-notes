
/**
 * DistanceFilter
 * 　　filter quakes by distance from location
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class DistanceFilter implements Filter
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private Location from;
    private double distanceMax;

    /**
     * DistanceFilter クラスのインスタンスのためのコンストラクタ
     */
    public DistanceFilter(Location loc, double max)
    {
        from = loc;
        distanceMax = max;
    }
    
    /**
     * satisfies
     *  filter quakes by distance to quake central less than distanceMax
     *
     */
    public boolean satisfies(QuakeEntry qe) {
        double dis = from.distanceTo(qe.getLocation());
        return dis <= distanceMax;
    }
    
    /**
     * get filter name
     */
    public String getName() {
        return "Distance";
    }
}
