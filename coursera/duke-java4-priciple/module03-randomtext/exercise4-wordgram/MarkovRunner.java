import edu.duke.*;

/**
 * MarkovRunner
 *   tester for MarkovWord
 * 
 * @author  Furukawa, Atsushi
 * @version 1.0.0
 */
public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("\n running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    
    /**
     * runMarkov
     *   test generate text
     */
    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord m = new MarkovWord(2); 
        runModel(m, st, 200); 
    } 

    /**
     * testHashMap
     *   test EfficientMarkovModel (with map)
     * 
     */
    public void testHashMap() {
        System.out.println("\n  test EfficientMatkov hashmap");
        
        // test 1
        String trText = "this is a test yes this is really a test";
        int size = 50;
        
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        runModel(markov, trText, size, 42);
        
        // test 2
        trText = "this is a test yes this is really a test yes a test this is wow";
        runModel(markov, trText, size, 42);
    }
    
    /**
     * compareMethods
     * 
     */
    public void compareMethods() {
        long startTime;
        long endTime;
        
        System.out.println("\n  test Markov speed");
        // training
        String file = "data/hawthorne.txt";
        FileResource fr = new FileResource(file);
        String trText = fr.asString().replace('\n', ' ');
        int size = 100;
        int seed = 42;
        
        // normal
        MarkovWord m1 = new MarkovWord(2);
        startTime = System.nanoTime();
        runModel(m1, trText, size, seed);
        endTime = System.nanoTime();
        long t1 = endTime - startTime;
        
        EfficientMarkovWord m2 = new EfficientMarkovWord(2);
        startTime = System.nanoTime();
        runModel(m2, trText, size, seed);
        endTime = System.nanoTime();
        long t2 = endTime - startTime;
        
        double ntos = 1000000000.0d;
        System.out.println("Normal Markov Word\t: " + (double)t1/ntos + " seconds.");
        System.out.println("Efficient Markov Word\t: " + (double)t2/ntos + " seconds.");
        
    }
    
    /**
     * printOut
     *   print out generated text
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
