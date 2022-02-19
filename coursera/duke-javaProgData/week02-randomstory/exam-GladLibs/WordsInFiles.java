import java.util.*;
import java.io.*;
import edu.duke.*;

/**
 * WordsInFiles
 *   read words from file and count it.
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class WordsInFiles
{
    // Words Counter
    private HashMap<String, ArrayList<String>> WordsinFiles;
    
    /**
     * Construnctor
     */
    public WordsInFiles() {
        WordsinFiles = new HashMap<String, ArrayList<String>>();
    }
    
    
    /**
     * buildWordFileMap
     *   build Words in Files with file select
     */
    void buildWordFileMap(DirectoryResource dr) {
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    
    /**
     * addWordsFromFile 
     *   read words from parameter (File)
     *   
     */
    void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String filename = f.getName();
        
        for (String word : fr.words()) {
            if (!WordsinFiles.containsKey(word)) {
                WordsinFiles.put(word, null);
            }
            ArrayList filelist = WordsinFiles.get(word);
        
            if (filelist == null) {
                filelist = new ArrayList<String>();
            }
            if (!filelist.contains(filename)) {
                filelist.add(filename);
            }
            WordsinFiles.put(word, filelist);
        }
    }

    /**
     * maxNumber 
     */
    int maxNumber() {
        int maxFiles = 0;
        
        for (ArrayList<String> filelist : WordsinFiles.values()) {
            int fileNum = filelist.size();
            if (fileNum > maxFiles) {
                maxFiles = fileNum;
            }
        }
        return maxFiles;
    }
    
    /**
     * wordsInNumFiles
     *   make words list from contains file number
     */
    ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordslist = new ArrayList<String>();
        
        for (String word : WordsinFiles.keySet()) {
            ArrayList<String> files = WordsinFiles.get(word);
            
            if (files.size() == number) {
                wordslist.add(word);
            }
        }
        return wordslist;
    }
    
    /**
     * printFilesIn
     * 
     */
    void printFilesIn(String word) {
        ArrayList<String> filelist = WordsinFiles.get(word);
        
        if (filelist == null) {
            return;
        }
        
        System.out.println("\n"+word);
        for (int i=0; i<filelist.size(); i++) {
            System.out.println(filelist.get(i));
        }
    }

    
    /**
     * tester
     *   test add word from files 
     */
    public void tester() {
        System.out.println("\n  test words in files.");
        
        DirectoryResource dr = new DirectoryResource();
        
        buildWordFileMap(dr);
        
        ArrayList<String> words = wordsInNumFiles(2);
        System.out.println("max\t" + maxNumber());
        printFilesIn("dogs");
        System.out.println("words\t"+words);
        
    }
}
