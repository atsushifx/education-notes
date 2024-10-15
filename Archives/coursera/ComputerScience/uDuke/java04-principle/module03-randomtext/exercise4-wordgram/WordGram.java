/**
 * WordGram
 *   create words list and methods equalsTo, ...
 *   
 */
public class WordGram {
    // property
    private String[] myWords;
    private int myHash;

    /**
     * constructor
     */
    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    public void set(int index, String word) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in set "+index);
        }
        myWords[index] = word;
    }
    
    /**
     * wordAt
     *   get word from index
     */
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    /**
     * length
     *   get WordGram size
     */
    public int length(){
        return myWords.length;
    }

    /**
     * toString
     *   WordGram to string for output
     */
    public String toString(){
        String ret = "";
        for (int i=0; i<length(); i++) {
            ret += wordAt(i);
            ret += " ";
        }
        return ret.trim();
    }
    
    /**
     * equals
     *   check this wordgram and other wordgram has same word list
     */
    public boolean equals(Object o) {
        WordGram o2 = (WordGram) o;
        if (this.length() != o2.length()) {
            return false;
        }
        for (int i=0; i<this.length(); i++) {
            if (! this.wordAt(i).equals(o2.wordAt(i))) {
                return false;       
            }
        }
        return true;

    }
    
    /**
     * shiftAdd
     *   shit word list and add word to last
     *   
     */
    public WordGram shiftAdd(String word) {    
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for (int i=1; i<length(); i++) {
            set(i-1, wordAt(i));
        }
        set(length()-1, word);
        return out;
    }

    /**
     * hashCode
     *   get hash code to build hash map.
     */
    public int hashCode() {
         return toString().hashCode();
    }
}