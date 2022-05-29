import java.util.*;

/**
 * MatchAllFilter
 *   filter quakes by many filter and filter all is true
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class MatchAllFilter implements Filter
{
    // インスタンス変数 - コードに合わせて説明を書き換えます.
    private ArrayList<Filter>  FilterList;

    /**
     * MatchAllFilter クラスのインスタンスのためのコンストラクタ
     */
    public MatchAllFilter()
    {
        FilterList = new ArrayList<Filter>();
    }
    
    /**
     * addFilter
     *   add filter to FilterList
     */
    public void addFilter(Filter filter) {
        FilterList.add(filter);
    }
    
    
    /**
     * satisfies
     *   filter quakes by all filters in FilterList
     */
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : FilterList) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * get using filter names
     */
    public String getName() {
        StringBuilder buff = new StringBuilder();
        
        for (Filter f : FilterList) {
            buff.append(" " + f.getName());
        }
        return buff.toString().substring(1);
    }
}
