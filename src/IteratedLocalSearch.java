
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
        this.random = new Random(seed);
    }

    /**
     * Main method that should be called from Main.java
     */
    public Result run (int maxIterations){

        long startTime = System.currentTimeMillis();

        //generate initial solution
        int[] current = generateInitialSolution();

        //improve the initial solution with local search
        current = localSearch(current);

        //since current is the only solution we have, it is best
        bestSolution = copyArray(current);
        bestValue = evaluate(current);

        //each iteration: disturb , improve , decide whether to keep
        for (int i = 0; i < maxIterations; i++) {
            int[] candidate = perturb(current);
            candidate = localSearch(candidate);
            // ACCEPTANCE CRITERION
            if (evaluate(candidate) > evaluate(current)){
                current = candidate;
            }

            if (evaluate(candidate) > bestValue){
                bestSolution = copyArray(candidate);
                bestValue = evaluate(candidate);
            }
        }

        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;

        //can be change to accomodate result's constructor
        return new Result(bestValue, runtime);
    }

    private int[] generateInitialSolution(){

        int[] solution = new int[instance.getNumItems()];

        for(int i = 0; i < bestSolution.length; i++){
            bestSolution[i] = 0;
        }

        for(int i = 0; i < solution.length; i++){
            solution[i] = random.nextInt(2);
        }

        //the random solution might exceed the knapsack capacity, so we must fix it before before using
        while (!isValid(solution)){
            
            java.util.List<Integer> includedItems = new java.util.ArrayList<>();
            for (int i = 0; i < solution.length; i++){
                if (solution[i] == 1){
                    includedItems.add(i);
                }
            }

            if (includedItems.isEmpty()) break;

            int randomIndex = random.nextInt(includedItems.size());
            solution[includedItems.get(randomIndex)] = 0;
        }

        return solution;
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
