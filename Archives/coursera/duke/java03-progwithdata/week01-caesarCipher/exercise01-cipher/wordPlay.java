
/**
 * wordPlay
 * 
 * @author  Furukawa Atsushi <atsushifx@gmail.com>
 * @version 1.0.0
 * @date    2022-Feb-11
 */
public class wordPlay
{
    /**
     * isVowel
     *   check parameter ch is vowel (a,e,i,o,u)
     */
    boolean isVowel(Character ch) {
        String VOWEL = "aiueo";
        
        return (VOWEL.indexOf(Character.toLowerCase(ch)) >= 0);
    }

    /**
     * replaceVowels
     *   input string's vowel change to parameter ch
     */
    String replaceVowels(String phrase, Character ch) {
        StringBuilder buffer = new StringBuilder();
        
        for (int i=0; i<phrase.length(); i++) {
            Character ch2 = phrase.charAt(i);
            if (isVowel(ch2)) {
                buffer.append(ch);   
            } else {
                buffer.append(ch2);
            }
        }
        return buffer.toString();
    }
    
    /**
     * emphasize
     *   replace phrase in character ch
     *  
     */
    String emphasize(String phrase, Character ch) {
        StringBuilder buffer = new StringBuilder();
        ch = Character.toLowerCase(ch);
        
        for (int i=0; i<phrase.length(); i++) {
            Character ch2 = phrase.charAt(i);
            
            if (Character.toLowerCase(ch2) == ch) {
                if ((i%2) == 0) {   // even : odd number
                    buffer.append('*');
                } else {
                    buffer.append('+'); 
                }
            } else {
                buffer.append(ch2);
            }
        }
        return buffer.toString();
    }
    
    /**
     * test is vowel
     *   check charcter is vowel (a,i,u,e,o)
     */
    public void testisVowel() {
        System.out.println("\n  test isVowel()");
        System.out.println("u is " + isVowel('u')); 
        System.out.println("A is " + isVowel('A'));
        System.out.println("k is " + isVowel('k'));
        System.out.println("' ' is " + isVowel(' '));
    }
    
    /**
     * test replaceVowels 
     *   replace vowel character test
     */
    public void testreplaceVowels() {
        String message;
        
        System.out.println("\n  test replace vowel");
        message = "Hello world!";
        System.out.println(message + " ⇒ " + replaceVowels(message, '*'));
    }
    
    /**
     * test emphasize
     */
    public void testemphasize() {
        String phrase;   // テストフレーズ
        
        System.out.println("\n  test emphasize");
    
        phrase = "dna ctgaaactga";
        System.out.println(phrase + "⇒ " + emphasize(phrase, 'a'));
        
        phrase = "Mary Bella Abracadabra";
        System.out.println(phrase + "⇒ " + emphasize(phrase, 'A')); 
    }
}