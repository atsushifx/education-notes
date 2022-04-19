import edu.duke.*; 
/**
 * MarkovRunnerWithInterface
 *   generate text by markov : implements by interface
 * 
 * @author Duke Software
 * @version 1.0
 */

public class MarkovRunnerWithInterface {
    /**
     * runModel
     *   generate and printout text
     */
    public long runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        // markov.setRandom(218);
        System.out.println("running with " + markov);
        long startTime = System.nanoTime();
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size);
        
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size);

    }
    
    /**
     * testHashMap
     */
    public void testHashMap() {
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        
        // 
        String trainingText = "yes-this-is-a-thin-pretty-pink-thistle";
        markov.setTraining(trainingText);
        markov.buildMap();
        int size = 50;
        markov.setRandom(42);
        
        System.out.println("running with " + markov);
        String st= markov.getRandomText(size);
        printOut(st);
    }
    
    /**
     * compareMethod
     *   test speed of Markov method
     */
    public void compareMethod() {
        String file = "data/hawthorne.txt";
        FileResource fr = new FileResource(file);
        String st = fr.asString().replace('\n', ' ');
        int size = 1000;
        
        // Normal Markov
        MarkovModel m1 = new MarkovModel(2);
        m1.setRandom(42);
        long t1 = runModel(m1, st, size);
        
        // Normal Markov
        EfficientMarkovModel m2 = new EfficientMarkovModel(2);
        m2.setRandom(42);
        long t2 = runModel(m2, st, size);
        
        double ntos = 1000000000d;
        
        // speed check
        System.out.println("\n -- markov speed --");
        System.out.println("Normal markov    : " + ((double)t1 / ntos) + " seconds");
        System.out.println("Efficient markov : " + ((double)t2 / ntos) + " seconds");
    }
    
    
    /**
     * printout
     *   printout generated text
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
