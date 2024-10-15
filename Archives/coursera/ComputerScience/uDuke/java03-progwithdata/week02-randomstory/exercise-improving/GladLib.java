import edu.duke.*;
import java.util.*;

/**
 * gladlib with HashMap
 */
public class GladLib {
    /**
     * keyword list
     */
    private HashMap<String, ArrayList<String>>  myMap;
    private HashMap<String, Boolean> myUsed;
    private Random myRandom;
    private String myDatasource;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeField(dataSourceDirectory);
        initializeFromSource(dataSourceDirectory);
        
    }
    
    public GladLib(String source){
        initializeField(source);
        initializeFromSource(source);
    }
    
    /**
     * initialize keywordMap & random
     */
    private void initializeField(String datasource) {
        myDatasource = datasource;
        myRandom = new Random();
        myMap = new HashMap<String, ArrayList<String>>();
        myUsed = new HashMap<String, Boolean>();
    }
    
    private void initializeFromSource(String source) {
        Set<String> keywordList = new HashSet<>(Arrays.asList("adjective", "noun", "verb", "color", "country", "name", "animal", "timeframe", "fruit"));
        
        for (String keyword : keywordList) {
            initKeywords(keyword);
        }
    }    
    
    /**
     * initializeList
     */
    private void initKeywords(String keyword) {
        if (myMap.containsKey(keyword)) { return; } // 初期化済み
        String datafile = myDatasource + "/" + keyword + ".txt";
        ArrayList<String> keywordList = readIt(datafile);
        myMap.put(keyword, keywordList);
        myUsed.put(keyword, false);
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        //
        String keyword = "**UNKNOWN**";
        if (!myMap.containsKey(label)) {
            // do nothing
            // initKeywords(label);
        } else {
            myUsed.put(label, true);
            keyword = randomFrom(myMap.get(label));    
            if (keyword == "") {
                keyword = "**UNKNOWN**";
            }
        }
        return keyword;
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                line = line.trim();
                if (line != "") {
                    list.add(line);
                }
            }
        }
        return list;
    }
    
    /**
     * totalWordsInMap
     *   count all keywords from readings
     */
    int totalWordsInMap() {
        int total = 0;
        for (ArrayList<String> keywords : myMap.values()) {
            total += keywords.size();
        }
        return total;
    }
    
    /**
     * totalWordsConsidered
     *   
     */
    int totalWordsConsidered() {
        int total = 0;
        for (String keyword : myMap.keySet()) {
            if (myUsed.get(keyword)) {
                total += myMap.get(keyword).size();
            }
        }
        return total;
        
    }
    
    /**
     * makeStory
     *   create story from template & keywords
     */
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        
        System.out.println("\n  total");
        System.out.println("keywords: " + totalWordsInMap());
        System.out.println("considered: " + totalWordsConsidered());
    }
    
}
