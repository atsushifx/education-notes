import edu.duke.*;
import java.util.Random;

/**
 * testCaesarCipher
 *   
 *
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class TestCaesarCipherTwo
{
    // for get random key
    private Random rand;

    /**
     * constructor
     * 
     */
    public TestCaesarCipherTwo()
    {
        rand = new Random();
    }

    /**
     * messageTests
     *   test enctypt / decrypt from setred message
     *
     */
    public void messageTests() {
        System.out.println("\n  test.1 message encrypt/decrypt");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(rand.nextInt(26), rand.nextInt(26));
        
        String message = "This is messager for Caesar Cipher two, encrypt with two keys.";
        String encMess = cc2.encrypt(message);
        String decMess = cc2.decrypt(encMess);
        
        System.out.println("message\n"+message+"\n"+encMess+"\n"+decMess);
    }

    /**
     * simpleTests
     *   test enctypt / decrypt message from file
     *
     */
    public void simpleTests() {
        System.out.println("\n  test.2 encrypt/decrypt test from file");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(rand.nextInt(26), rand.nextInt(26));
        
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        String encMess = cc2.encrypt(message);
        String decMess = cc2.decrypt(encMess);
        
        System.out.println("message\n"+message+"\n"+encMess+"\n"+decMess);
    }
    
    /**
     * crackTests
     *   test crack Caesar Cipher two keys
     *
     */
    public void crackTests() {
        System.out.println("\n  test.2 encrypt/decrypt test from file");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(rand.nextInt(26), rand.nextInt(26));
        CaesarBreakerTwo cb2 = new CaesarBreakerTwo();
        
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        String encMess = cc2.encrypt(message);
        String crackMess = cb2.crackMessage(encMess);
        
        System.out.println("message\n"+message+"\n"+encMess+"\n"+crackMess);
    }
}

