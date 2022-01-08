import edu.duke.*;

/**
 * part1:
 * find abc from string
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class exam1
{
    /**
     * findABC
     * search 'abc' from input string
     * found abc is print out
     */
    public void findAbc(String input) {
        System.out.println("input:"+input);
        
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            if (index > input.length() - 4) {
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    
    /**
     * check findABC ecxecute validty
     */
    public void test() {
        System.out.println("\n\n  exam1 testing start.");
        findAbc("abcd");
        findAbc("woiehabchi");
        findAbc("eusabce");
        findAbc("abcbbbabcdddabc");  // throw
        findAbc("yabcyabc");     // throw
        findAbc("aaaaabc");
    }
}
