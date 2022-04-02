import edu.duke.*;

/**
 * MarkovRunner
 *   create random text from markov mosel
 * 
 * @author Duke Software
 * @version 1.0
 */
public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    /**
     * runMarkovOne
     *    create random text using MarkovOne
     */
    public void runMarkovOne() {
        MarkovOne markov = new MarkovOne();
        System.out.println("\n  create text using MarkovOne");
        
        // training
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        
        // generate
        markov.setRandom(42);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }   
    }
    
    /**
     * runMarkovFour
     *    create random text using MarkovFour
     */
    public void runMarkovFour() {
        MarkovFour markov = new MarkovFour();
        System.out.println("\n  create text using MarkovFour");
        
        // training
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        
        // generate
        markov.setRandom(25);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }   
 
    }
    
    /**
     * runMarkovModel
     *    create random text using MarkoModel
     */
    public void runMarkovModel() {
        MarkovModel markov = new MarkovModel(6);
        System.out.println("\n  create text using Markov Model");
        
        // training
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        
        // generate
        markov.setRandom(38);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }   
 
    }
    
    /**
     * printout
     *   printout generate text
     */
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
