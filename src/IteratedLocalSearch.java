
import java.util.Random;

public class IteratedLocalSearch {

    /**
     * The algorithm explore the neighbourhood and then 
     * teleport to a new area when stuck
     * 
     * Step 1 : Generate initial solution
     * Step 2 : Local search 
     * Step 3 : Perturbation
     * Step 4 : Acceptance Criterion
     * Step 5 : Temination
     */

    private KnapsackInstance instance;
    private Random random;
    private int[] bestSolution;
    private int bestValue;

    public IteratedLocalSearch(KnapsackInstance instance, long seed){
        this.instance = instance;
        // this.seed // what do we do here
    }

    /**
     * Main method that should be called from Main.java
     */
    public Result run (int maxIterations){

    }

    private int[] generateInitialSolution(){

    }
    private int[] localSearch(int[] solution){

    }
    private int[] perturb(int[] solution){

    }
    private int evaluate(int[] solution){

    }
    private boolean isValid(int[] solution){

    }
    private int[] copyArray(int[] solution){
        
    }
    
}
