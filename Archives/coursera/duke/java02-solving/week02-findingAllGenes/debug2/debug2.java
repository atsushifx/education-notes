
/**
 * debug2 : find 'abc_' from input string.
 *   pattern 'abc_' to output 'bc_'
 *  
 *  @author atsushifx
 *  @date   2022-01-09
 *  @version 1.0.0
 */
public class debug2
{
    public void findAbc(String input){
       int index = input.indexOf("abc");
       
       System.out.println("\ninput:"+input);
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println("["+index+"] : "+found);
           index = input.indexOf("abc",index + 3);
       }
   }

   public void test(){
       System.out.println("\n\n  debug 2 test start.");
       //findAbc("abcd");
       //findAbc("abcdabc");
       
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       findAbc("abcabcabcabca”");
       System.out.println("\ntest is finished");
   }
}
