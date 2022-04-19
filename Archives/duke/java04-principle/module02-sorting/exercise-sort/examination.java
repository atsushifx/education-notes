import java.util.*;
import edu.duke.*;
/**
 * examination
 *   exam test prog for sorting algorithm
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class examination {
    /**
     * printQuakes
     *   地震情報を出力
     */
    private void printQuakes(ArrayList<QuakeEntry> quakes) {
        System.out.println("read data for " + quakes.size() + " quakes");
        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        }
        
    }
    
    /**
     * printLastQuake
     */
    private void printLastQuake(ArrayList<QuakeEntry> quakes) {
        int size = quakes.size();
        System.out.println("read data for " + size + " quakes");
        System.out.println("last quake:" + size + "\n" + quakes.get(size-1));
    }
    
    
    /**
     * exam1
     *   テスト用にソートとソート結果を出力
     */
    public void exam1() {
        System.out.println("\n -- examination : q.1-");
        
        // read datas
        EarthQuakeParser parser = new EarthQuakeParser();
        QuakeSortInPlace sorter = new QuakeSortInPlace();
        String source;
        ArrayList<QuakeEntry> list;
        
        // q.1
        System.out.println("\n q.1 sort by depth 50 pass");
        source = "data/earthQuakeDataDec6sample2.atom";
        list = parser.read(source);
        sorter.sortByLargestDepth(list, 50);
        printLastQuake(list);
        
        // q.2
        System.out.println("\n q.2 how many passes by sortByMagnitudeWithCheck");
        source = "data/earthQuakeDataWeekDec6sample2.atom";
        list = parser.read(source);
        sorter.sortByMagnitudeWithCheck(list);
        
        // q.2
        System.out.println("\n q.3 how many passes by sort by bubble sort");
        source = "data/earthQuakeDataWeekDec6sample1.atom";
        list = parser.read(source);
        sorter.sortByMagnitudeWithBubbleSortWithCheck(list);
        
    }
    
    /**
     * swap
     */
    void swap(ArrayList<Integer> arr, int i1, int i2) {
        if (i1>=0 && i2>=0 && i1!=i2) {
            Integer src = arr.get(i1);
            Integer dst = arr.get(i2);
            arr.set(i1, dst);
            arr.set(i2, src);
        }
    }
    
    /**
     * getMinIndex
     *   get index with minimum value
     */
    int getMinIndex(ArrayList<Integer> list, int from) {
        int minIndex = from;
        for (int i=from; i<list.size(); i++) {
            if (list.get(i) < list.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * selectionSort
     *   sort array list by selection Sort alghorithm
     *     途中経過を表示
     */
    ArrayList<Integer> selectionSort(ArrayList<Integer> list) {
        for (int i=0; i<list.size()-1; i++) {
            System.out.println("pass "+ i + " : " + list);
            
            int minIndex = getMinIndex(list, i);
            swap(list, i, minIndex);
        }
        return list;
    }
    
    /**
     * bubbleSort
     *   sort array list by bubble Sort alghorithm
     *     途中経過を表示
     */
    ArrayList<Integer> bubbleSort(ArrayList<Integer> list) {
        for (int i=0; i<list.size(); i++) {
            System.out.println("pass "+ i + " : " + list); // before sort 1 pass
            for (int j=0; j<list.size()-i ; j++) {
                if ((j+1<list.size()) && (list.get(j) > list.get(j+1))) {
                    swap(list, j, j+1);
                }
            }
        }
        return list;
    }
    
    /**
     * 
     */
    public void exam2() {
        System.out.println("\n  -- examination: q.4 - 5");
        
        System.out.println("\n q.4 - selection sort");
        ArrayList<Integer>  arr1 = new ArrayList<>(Arrays.asList(2, 4, 5, 9, 8, 1));
        selectionSort(arr1);
        System.out.println(arr1);
        
        System.out.println("\n q.5 - bubble sort");
        ArrayList<Integer>  arr2 = new ArrayList<>(Arrays.asList(4, 2, 5, 9, 8, 1));
        bubbleSort(arr2);
        System.out.println(arr2);
        
    }
    
}
