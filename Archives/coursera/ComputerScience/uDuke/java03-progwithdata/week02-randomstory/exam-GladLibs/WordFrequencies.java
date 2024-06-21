import edu.duke.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author 　Duke Software Team
 * @author 　
 * @version 1.0
 */
public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        findUniqueinFile(fr);
    }
    
    /**
     * findUniqueinFile
     *   unique word count in file
     */
    public void findUniqueinFile(File f) {
        FileResource fr = new FileResource(f);
        findUniqueinFile(fr);
    }
    
    public void findUniqueinFile(FileResource resource){
        for(String s : resource.words()){
            s = s.toLowerCase().trim();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    void printWordList() {
        for (int i=0; i<myWords.size(); i++) {
            String word = myWords.get(i);
            int cnt = myFreqs.get(i);
            System.out.println(cnt + "\t" + word);
        }
    }
    
    /**
     * 
     */
    public int getWordCount() {
        return myWords.size();
    }
    
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: "+ getWordCount());
        int index = findMax();
        // printWordList();
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }
    
    public void testerWithFile(String scenarioFile){
        System.out.println("\n  test with file:"+scenarioFile);
        FileResource fr = new FileResource(scenarioFile);
        findUniqueinFile(fr);
        int index = findMax();
        
        System.out.println("# unique words: "+myWords.size());
        System.out.println("max word/freq: "+myWords.get(index)+" / "+myFreqs.get(index));
    }
    
    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
