
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
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
     *   genetate text and print out with MarkovOne
     */
    public void runMarkov() {
        System.out.println("\n -- run Markov word one");
        MarkovWordOne markovWord = new MarkovWordOne(); 
        //
        FileResource fr = new FileResource(); 
        String st = fr.asString();
        st = st.replace('\n', ' ');
        // runModel(markovWord, st, 200); 
        runModel(markovWord, st, 120, 175);
    } 

    /**
     * runMarkovTwo
     *   genetate text and print out with MarkovWordTwo
     *   
     */
    public void runMarkovTwo() {
        System.out.println("\n -- run Markov word Two");
        // training text
        String file = "data/confucius.txt";
        FileResource fr = new FileResource(file); 
        String st = fr.asString().replace('\n', ' ');
        
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 120, 549);
    } 

    /**
     * printOut
     *   print out genetated text
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

    /**
     * testIndexOf
     *   test IndexOf method in MarkovWordOne
     */
    public void testIndexOf() {
        MarkovWordOne mark = new MarkovWordOne();
        mark.testIndexOf();
    }
}
