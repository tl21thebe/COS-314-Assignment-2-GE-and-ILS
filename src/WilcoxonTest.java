import java.util.*;

public class WilcoxonTest {
    
   
   
   
    public static void test(List<Result> gaResults, List<Result> ilsResults) {
   
        int n = gaResults.size();
   
        double[] differences = new double[n];
   
        
        
        for (int i = 0; i < n; i++) {
        
            differences[i] = gaResults.get(i).getBestValue() - ilsResults.get(i).getBestValue();
        
        }
        

        
       
        
        Integer[] indices = new Integer[n];
        
        for (int i = 0; i < n; i++) indices[i] = i;
        
        Arrays.sort(indices, (a, b) -> Double.compare(Math.abs(differences[a]), Math.abs(differences[b])));
        

        
        double Wplus = 0, Wminus = 0;
        
        for (int rank = 0; rank < n; rank++) {
        
            int idx = indices[rank];
        
            if (differences[idx] > 0) Wplus += (rank + 1);
        
            else if (differences[idx] < 0) Wminus += (rank + 1);
        
        }
        

        
        double W = Math.min(Wplus, Wminus);
        
        System.out.println("\n==================================================");
        System.out.println("WILCOXON SIGNED-RANK TEST (α=0.05)");
        System.out.println("==================================================");
        System.out.println("H0: GA mean = ILS mean");
        System.out.println("H1: GA mean ≠ ILS mean");
        System.out.println("W statistic: " + W);
        
        
        double gaMean = 0, ilsMean = 0;
        
        for (int i = 0; i < n; i++) {
        
            gaMean += gaResults.get(i).getBestValue();
        
            ilsMean += ilsResults.get(i).getBestValue();
        
        }
        
        gaMean /= n;
        
        ilsMean /= n;
        

        System.out.println("GA Mean: " + gaMean);
        System.out.println("ILS Mean: " + ilsMean);
        
        System.out.println("Difference: " + Math.abs(gaMean - ilsMean));
        

        
        
        
        
        if (W <= 11) {
        
            System.out.println("Result: REJECT H0 - Significant difference found");
        
        } else {
        
            System.out.println("Result: FAIL TO REJECT H0 - No significant difference");
        
        
        }
    }

}
