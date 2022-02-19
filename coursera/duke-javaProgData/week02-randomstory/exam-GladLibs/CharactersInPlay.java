import edu.duke.*;
import java.util.*;


/**
 * CharactersInPlay
 *   count character plays
 *   
 * 
 * @author 　Furukawa Atsushi
 * @version 1.0.0
 */
public class CharactersInPlay
{
    // Fields
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myCounts;
    
    /**
     * constructor
     */
    public CharactersInPlay() {
        myCharacters = new ArrayList<String>();
        myCounts = new ArrayList<Integer>();
    }
    
    /**
     * update
     *   set list to character(in
     */
    void update(String person) {
        int index = myCharacters.indexOf(person);
        
        if (index < 0) { // not found 
            myCharacters.add(person);
            myCounts.add(1);
        } else {
            int cnt = myCounts.get(index);
            myCounts.set(index, cnt + 1);
        }
    }
    
    /**
     * findAllCharacters
     */
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        findCharactersinFile(fr);
    }
    
    /**
     * findCharactersinFile
     * 
     */
    void findCharactersinFile(String file) {
        FileResource fr = new FileResource(file);
        
        findCharactersinFile(fr);
    }
    
    /**
     * findCharactersinFile
     * 
     */
    void findCharactersinFile(FileResource fr) {
        for (String line : fr.lines()) {
            int dotindex = line.indexOf(".");
            String person = "";
            if (dotindex > 0) {
                person = line.substring(0, dotindex);
                // person = person.trim();
                update(person);
            }    
        }    
    }
    
    
    /**
     * tester
     *   test character plays count
     */
    public void tester() {
        System.out.println("\n  test.1 -- count character plays");
        /*
        FileResource fr = new FileResource("../datas/macbethSmall.txt");
        findCharactersinFile(fr);
        */
        findAllCharacters();
        charactersWithNumParts(3, 0);
    }
    
    /**
     * testerwithFilw
     *   test character plays count
     */
    public void testerwithFile(String scenarioFile) {
        // System.out.println("\n  qtest. with file:" + scenarioFile);
    
        FileResource fr = new FileResource(scenarioFile);
        findCharactersinFile(fr);
        
        charactersWithNumParts(3, 0);
        System.out.println("\n  q.6 -  charactert plays 10 to 15.");
        charactersWithNumParts(10, 15);
        
    }
    /**
     * 
     */
    void charactersWithNumParts(int min, int max) {
        for (int i=0; i<myCharacters.size(); i++) {
            String person = myCharacters.get(i);
            Integer cnt = myCounts.get(i);
        
            if (min<=cnt && (cnt<=max || max<=0)) {
                System.out.println(person + "\t" + cnt);
            }
        }
    }
    
    /**
     * printCharacters
     */
    void printAllCharacters() {
        for (int i=0; i<myCharacters.size(); i++) {
            String person = myCharacters.get(i);
            Integer cnt = myCounts.get(i);
            System.out.println(person + "\t" + cnt);
        }
    }
}


