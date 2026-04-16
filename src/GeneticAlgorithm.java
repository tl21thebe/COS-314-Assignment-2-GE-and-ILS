import java.util.*;

public class GeneticAlgorithm {
    private KnapsackInstance instance;
    private Random random;
    private int populationSize = 100;
    private int generations = 500;
    private double crossoverRate = 0.8;
    private double mutationRate = 0.01;
    private int elitismCount = 2;
    private int tournamentSize = 3;
    
    private int[][] population;
    private int[] fitness;
    private int bestFitness;
    private int[] bestSolution;
    


    
    public GeneticAlgorithm(KnapsackInstance instance, long seed) {
    
        this.instance = instance;
    
        this.random = new Random(seed);
    
        this.population = new int[populationSize][instance.getNumItems()];
    
        this.fitness = new int[populationSize];
    
    }
    

    
    public Result run(int maxGenerations) {
    
        long startTime = System.currentTimeMillis();
    
        
        
        initializePopulation();
        
        evaluatePopulation();
        

        
        for (int gen = 0; gen < maxGenerations; gen++) {
        
            int[][] newPopulation = new int[populationSize][instance.getNumItems()];
        
            
            
        
            
            int[] bestIndices = getBestIndices(elitismCount);
            
            for (int i = 0; i < elitismCount; i++) {
            
                newPopulation[i] = population[bestIndices[i]].clone();
            
            }
            

            
         
            
            for (int i = elitismCount; i < populationSize; i += 2) {
            
                int[] parent1 = tournamentSelection();
            
                int[] parent2 = tournamentSelection();
            
                
                
                int[] child1, child2;
                
                if (random.nextDouble() < crossoverRate) {
                
                    child1 = uniformCrossover(parent1, parent2);
                
                    child2 = uniformCrossover(parent2, parent1);
                
                } 
                
                else {
                
                    child1 = parent1.clone();
                
                    child2 = parent2.clone();
                
                }
                

                
                mutate(child1);
                
                mutate(child2);
                

                
                newPopulation[i] = child1;
                
                if (i + 1 < populationSize) newPopulation[i + 1] = child2;
            }

            
            
            population = newPopulation;
            
            evaluatePopulation();



        }

        
        
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        
        return new Result(bestFitness, bestSolution, runtime);
    }

    
    

    
    private void initializePopulation() {
    
        for (int i = 0; i < populationSize; i++) {
    
            population[i] = new int[instance.getNumItems()];
    
            for (int j = 0; j < instance.getNumItems(); j++) {
    
                population[i][j] = random.nextBoolean() ? 1 : 0;
    
            }
    
            repairSolution(population[i]);
    
        }
    
    }
    

    
    private void repairSolution(int[] solution) {
    
        int totalWeight = 0;
    
        for (int i = 0; i < instance.getNumItems(); i++) {
    
            if (solution[i] == 1) totalWeight += instance.getWeights()[i];
    
        }
    
        
        
        List<Integer> indices = new ArrayList<>();
        
        for (int i = 0; i < instance.getNumItems(); i++) {
        
            if (solution[i] == 1) indices.add(i);
        
        }
        
        Collections.shuffle(indices, random);
        

        
        while (totalWeight > instance.getCapacity() && !indices.isEmpty()) {
        
            int idx = indices.remove(0);
        
            solution[idx] = 0;
        
            totalWeight -= instance.getWeights()[idx];
        
        }
    }
    
    
    private void evaluatePopulation() {
    
        for (int i = 0; i < populationSize; i++) {
    
            int value = 0;
    
            int weight = 0;
    
            for (int j = 0; j < instance.getNumItems(); j++) {
    
                if (population[i][j] == 1) {
    
                    value += instance.getValues()[j];
    
                    weight += instance.getWeights()[j];
    
                }
    
            }
    
            if (weight > instance.getCapacity()) {
    
                value = 0;
    
            }
    
            fitness[i] = value;
    
        }
    
        
        
        bestFitness = 0;
        
        for (int i = 0; i < populationSize; i++) {
        
            if (fitness[i] > bestFitness) {
        
                bestFitness = fitness[i];
        
                bestSolution = population[i].clone();
        
            }
        
        }
    }
    
    
    
    
    private int[] tournamentSelection() {
    
        int[] selected = null;
    
        int bestFit = -1;
    
        for (int i = 0; i < tournamentSize; i++) {
    
            int idx = random.nextInt(populationSize);
    
            if (fitness[idx] > bestFit) {
    
                bestFit = fitness[idx];
    
                selected = population[idx];
    
            }
    
        }
    
        return selected.clone();
    
    }
    

    
    private int[] uniformCrossover(int[] parent1, int[] parent2) {
    
        int[] child = new int[instance.getNumItems()];
    
        for (int i = 0; i < instance.getNumItems(); i++) {
    
            child[i] = random.nextBoolean() ? parent1[i] : parent2[i];
    
        }
    
        return child;
    
    }
    

    
    private void mutate(int[] solution) {
    
        for (int i = 0; i < instance.getNumItems(); i++) {
    
    
            if (random.nextDouble() < mutationRate) {
    
                solution[i] = 1 - solution[i];
    
            }
    
        }
    
        repairSolution(solution);
    
    
    }
    

    
    private int[] getBestIndices(int count) {
    
        Integer[] indices = new Integer[populationSize];
    
        for (int i = 0; i < populationSize; i++) indices[i] = i;
    
        Arrays.sort(indices, (a, b) -> Integer.compare(fitness[b], fitness[a]));
    
        int[] result = new int[count];
    
        for (int i = 0; i < count; i++) result[i] = indices[i];
    
        return result;
    
    
    }
}

