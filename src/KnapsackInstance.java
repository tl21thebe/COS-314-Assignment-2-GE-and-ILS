public class KnapsackInstance {
    private String filename;
    private int capacity;
    private int[] weights;
    private int[] values;
    private int knownOptimum;
    private int numItems;
    
   
   
    public KnapsackInstance(String filename, int capacity, int[] weights, int[] values, int knownOptimum) {
   
        this.filename = filename;
   
        this.capacity = capacity;
   
        this.weights = weights;
   
        this.values = values;
   
        this.knownOptimum = knownOptimum;
   
        this.numItems = weights.length;
   
    }
   
    
    
    public String getFilename() { return filename; }
    
    public int getCapacity() { return capacity; }
    
    public int[] getWeights() { return weights; }
    
    public int[] getValues() { return values; }
    
    public int getKnownOptimum() { return knownOptimum; }
    
    public int getNumItems() { return numItems; }
}

