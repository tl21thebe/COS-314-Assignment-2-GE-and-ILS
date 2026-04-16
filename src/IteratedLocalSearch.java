import java.util.Random;

public class IteratedLocalSearch {

    private KnapsackInstance instance;
    private Random random;
    private int[] bestSolution;
    private int bestValue;

   
   
   
    public IteratedLocalSearch(KnapsackInstance instance, long seed){
   
        this.instance = instance;
   
        this.random = new Random(seed);
   
        this.bestSolution = new int[instance.getNumItems()];
   
        this.bestValue = 0;
   
   
    }


    
    public Result run (int maxIterations){


        
        long startTime = System.currentTimeMillis();


        
        int[] current = generateInitialSolution();
        
        current = localSearch(current);


        
        bestSolution = copyArray(current);
        
        bestValue = evaluate(current);


        
        for (int i = 0; i < maxIterations; i++) {
        
            int[] candidate = perturb(current);
        
            candidate = localSearch(candidate);
        
            
            
            if (evaluate(candidate) > evaluate(current)){
            
                current = candidate;
            
            }


            
            if (evaluate(candidate) > bestValue){
            
                bestSolution = copyArray(candidate);
            
                bestValue = evaluate(candidate);
            
            }
        }



        
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        
        return new Result(bestValue, runtime);
    }


    
    private int[] generateInitialSolution(){


        
        
        int[] solution = new int[instance.getNumItems()];


        
        for(int i = 0; i < solution.length; i++){
        
            solution[i] = random.nextInt(2);
        
        }


        
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


        
        int[] current = copyArray(solution);
        
        int currentValue = evaluate(current);
        
        boolean improved = true;


        
        while(improved){
        
            improved = false;
        
            for(int i = 0; i < current.length; i++){
        
                current[i] = 1 - current[i];
        
        
                int neighbourValue = evaluate(current);
        
                if (isValid(current) && neighbourValue > currentValue) {
        
                    currentValue = neighbourValue;
        
                    improved = true; 
        
                } else{
        
                    current[i] = 1 - current[i]; 
        
                }
        
            }
        
        }
        
        return current;
    }
    
    
    private int[] perturb(int[] solution){


        
        int[] perturbed = copyArray(solution);
        
        
        
        int k = 3;


        
        for(int i = 0; i < k; i++){
        
            int randomIndex = random.nextInt(perturbed.length); 
        
            perturbed[randomIndex] = 1 - perturbed[randomIndex];
        
        }


        
        while (!isValid(perturbed)) {
        
            java.util.List<Integer> includedItems = new java.util.ArrayList<>();
        
            for (int i = 0; i < perturbed.length; i++) {
        
                if (perturbed[i] == 1) {
        
                    includedItems.add(i);
        
                }
        
            }
        
            if (includedItems.isEmpty()) break;
            int randomIndex = random.nextInt(includedItems.size());
        
            perturbed[includedItems.get(randomIndex)] = 0;
        
        
        }


        
        return perturbed;
    }
    
    
    
    private int evaluate(int[] solution){
    
        int totalValue = 0;
    
        for (int i = 0; i < solution.length; i++){
    
    
            if (solution[i] == 1){
    
                totalValue += instance.getValues()[i];
    
            }
    
        }
    
        return totalValue;
    
    }
    


    
    private boolean isValid(int[] solution){
    
        int totalWeight = 0;
    
        for (int i = 0; i < solution.length; i++){
    
            if (solution[i] == 1){
    
                totalWeight += instance.getWeights()[i];
    
            }
    
        }
    
        return totalWeight <= instance.getCapacity();
    
    }
    

    
    private int[] copyArray(int[] solution){
    
        int[] copy = new int[solution.length];
    
        for (int i = 0; i < solution.length; i++){
    
            copy[i] = solution[i];
    
        }
    
        return copy;
    
    
    }
}
