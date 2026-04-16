import java.util.*;

public class Main {
    private static long SEED;
    private static Random random;
    private static List<KnapsackInstance> instances = new ArrayList<>();
    
   
   
   
    public static void main(String[] args) {
   
        Scanner scanner = new Scanner(System.in);
   
        System.out.print("Enter seed value: ");
   
        SEED = scanner.nextLong();
   
        random = new Random(SEED);
   
        System.out.println("Using seed: " + SEED);
   
        
        
        loadAllInstances();
        


        
        List<Result> gaResults = new ArrayList<>();
        
        List<Result> ilsResults = new ArrayList<>();
        
        System.out.println("\n+----------------------+----------+------------+----------------+-----------------+");
        System.out.println("| Instance             | Algorithm| Best Value | Known Optimum   | Runtime (s)     |");
        System.out.println("+----------------------+----------+------------+----------------+-----------------+");
        

        
        for (KnapsackInstance inst : instances) {
        
            System.out.println("Solving: " + inst.getFilename());
        
            
            
            GeneticAlgorithm ga = new GeneticAlgorithm(inst, SEED);
            
            
            Result gaResult = ga.run(500);
            
            gaResults.add(gaResult);
            

            
            IteratedLocalSearch ils = new IteratedLocalSearch(inst, SEED);
            
            Result ilsResult = ils.run(1000);
            
            ilsResults.add(ilsResult);
            
            
            System.out.printf("| %-20s | GA       | %-10d | %-14d | %-15.3f |\n",
                inst.getFilename(), gaResult.getBestValue(), inst.getKnownOptimum(), gaResult.getRuntime());
            System.out.printf("| %-20s | ILS      | %-10d | %-14d | %-15.3f |\n",
                inst.getFilename(), ilsResult.getBestValue(), inst.getKnownOptimum(), ilsResult.getRuntime());
        }
        System.out.println("+----------------------+----------+------------+----------------+-----------------+");
        
        
        
        WilcoxonTest.test(gaResults, ilsResults);

        
    }
    
    
    
    private static void loadAllInstances() {
    
        // Instance 1
    
        instances.add(new KnapsackInstance("f1_l-d_kp_10_269", 269,
    
        new int[]{55,10,47,5,4,50,8,61,85,87},
    
        new int[]{95,4,60,32,23,72,80,62,65,46}, 295));
    
        
        
        // Instance 2
        
        instances.add(new KnapsackInstance("f2_l-d_kp_20_878", 878,
        
        new int[]{44,46,90,72,91,40,75,35,8,54,78,40,77,15,61,17,75,29,75,63},
        
        new int[]{92,4,43,83,84,68,92,82,6,44,32,18,56,83,25,96,70,48,14,58}, 1024));
        

        
        // Instance 3
        
        instances.add(new KnapsackInstance("f3_l-d_kp_4_20", 20,
        
        new int[]{9,11,13,15},
        
        new int[]{6,5,9,7}, 35));
        

        
        // Instance 4
        
        instances.add(new KnapsackInstance("f4_l-d_kp_4_11", 11,
        
        new int[]{6,10,12,13},
        
        new int[]{2,4,6,7}, 23));
        

        
        // Instance 5
        
        instances.add(new KnapsackInstance("f5_l-d_kp_15_375", 375,
        
        new int[]{0,19,59,35,82,17,71,30,9,15,99,12,1,53,60},
        
        new int[]{56,81,48,90,75,86,51,1,36,17,45,1,38,57,61}, 481));
        

        
        
        // Instance 6
        
        instances.add(new KnapsackInstance("f6_l-d_kp_10_60", 60,
        
        new int[]{20,18,17,15,15,10,5,3,1,1},
        
        new int[]{30,25,20,18,17,11,5,2,1,1}, 52));
        

        
        // Instance 7
        
        instances.add(new KnapsackInstance("f7_l-d_kp_7_50", 50,
        
        new int[]{70,20,39,37,7,5,10},
        
        new int[]{31,10,20,19,4,3,6}, 107));
        

        
        // Instance 8
        
        instances.add(new KnapsackInstance("f8_l-d_kp_23_10000", 10000,
        
        
        new int[]{981,980,979,978,977,976,487,974,970,485,485,970,970,484,484,976,974,482,962,961,959,958,857},
        
        new int[]{983,982,981,980,979,978,488,976,972,486,486,972,972,485,485,969,966,483,964,963,961,958,959}, 9767));
        

        
        // Instance 9
        
        instances.add(new KnapsackInstance("f9_l-d_kp_5_80", 80,
        
        new int[]{33,24,36,37,12},
        
        new int[]{15,20,17,8,31}, 130));
        

        
        // Instance 10
        
        instances.add(new KnapsackInstance("f10_l-d_kp_20_879", 879,
        
        new int[]{91,72,90,46,55,8,35,75,61,15,77,40,63,75,29,75,17,78,40,44},
            new int[]{84,83,43,4,44,6,82,92,25,83,56,18,58,14,48,70,96,32,68,92}, 1025));
        
        
        
        instances.add(new KnapsackInstance("knapPI_1_100_1000_1", 995,
        
        new int[]{94,506,416,992,649,237,457,815,446,422,791,359,667,598,7,544,334,766,994,893,633,131,428,700,617,874,720,419,794,196,997,116,908,539,707,569,537,931,726,487,772,513,81,943,58,303,764,536,724,789,479,142,339,641,196,494,66,824,208,711,800,314,289,401,466,689,833,225,244,849,113,379,361,65,486,686,286,889,24,491,891,90,181,214,17,472,418,419,356,682,306,201,385,952,500,194,737,324,992,224},
        
        new int[]{485,326,248,421,322,795,43,845,955,252,9,901,122,94,738,574,715,882,367,984,299,433,682,72,874,138,856,145,995,529,199,277,97,719,242,107,122,70,98,600,645,267,972,895,213,748,487,923,29,674,540,554,467,46,710,553,191,724,730,988,90,340,549,196,865,678,570,936,722,651,123,431,508,585,853,642,992,725,286,812,859,663,88,179,187,619,261,846,192,261,514,886,530,849,294,799,391,330,298,790}, 9147));
    }

}


