public class Result {
    private int bestValue;
    private double runtime;
    private int[] bestSolution;
    
  
    public Result(int bestValue, double runtime) {
  
        this.bestValue = bestValue;
  
        this.runtime = runtime;
  
    }
  
    
    
    public Result(int bestValue, int[] bestSolution, double runtime) {
    
        this.bestValue = bestValue;
    
        this.bestSolution = bestSolution;
    
        this.runtime = runtime;
    
    }
    

    
    public int getBestValue() { return bestValue; }
    
    public double getRuntime() { return runtime; }
    
    public int[] getBestSolution() { return bestSolution; }
}

