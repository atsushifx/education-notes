import java.util.*;
/**
 * WordGramTester
 *   test WordGram class that works valid
 */
public class WordGramTester {
    /**
     * initWordGram
     *   create WordGram array to test
     */
    private ArrayList<WordGram> initWordGram(String source) {
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
        return list;
    }
    
    
    /**
     * testWordGram
     *   check WordGram creation
     */
    public void testWordGram(){
        System.out.println("\n  test WordGram");
        String source = "this is a test this is a test this is a test of words";
        ArrayList<WordGram> list = initWordGram(source);
        for(int i=0; i<list.size(); i++) {
            WordGram wg = list.get(i);
            System.out.println(i+"\t"+wg.length()+"\t"+wg);
        }
    }
    
    /**
     * testWordGramEquals
     *   check WordGram equal to another WordGram
     * 
     */
    public void testWordGramEquals() {
        System.out.println("\n  test equals");
        String source = "this is a test this is a test this is a test of words";
        ArrayList<WordGram> list = initWordGram(source);
        WordGram first = list.get(0);
        System.out.println("checking "+first);
        for(int k=0; k < list.size(); k++){
            //if (first == list.get(k)) {
              if (first.equals(list.get(k))) {
                System.out.println("matched at "+k+" "+list.get(k));
            }
        }
    }
    
    /**
     * testWordGramShift
     *   check WordGram equal to another WordGram
     * 
     */
    public void testWordGramShift() {
        System.out.println("\n  test WordGram shiftAdd");
        String source = "this is a test this is a test this is a test of words";
        ArrayList<WordGram> list = initWordGram(source);
        WordGram wg1 = list.get(4);
        wg1.shiftAdd("flower");
        String[] w = {"is", "a", "test", "flower"};
        WordGram wg2 = new WordGram(w, 0, 4);
        System.out.println("shited WordGram: [" + wg1 + "] matched " + wg1.equals(wg2));
        System.out.println("expected : " + wg2);
    }
}