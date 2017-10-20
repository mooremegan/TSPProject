import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class TravellingSalesmanProblem {
   
   public static ArrayList<int[]> permutations = new ArrayList<int[]>();
   //we want a hashtable of city to city + cost
   //string will be city#tocity# (for cost between 1 and 2, string is "1to2"
   //Integer is the cost
   public static HashMap<String, Integer> costBtwn = new HashMap<String, Integer>();
   
   /**
    * @param args
    */
   public static void main(String[] args) throws IOException {
      //start timer
      long startTime = System.nanoTime();
      
      Scanner tMatrix = new Scanner(new File("swiss42.tsp"));
      
      //ensure first line of input file contains the number of nodes in the file as the first integer.  
      int num = tMatrix.nextInt();
      
      int[] cities = new int[num + 1];
      
//      int[] cities = {0,1,2,3,0};
//      costBtwn.put("0to1", new Integer(1));
//      costBtwn.put("0to2", new Integer(8));
//      costBtwn.put("0to3", new Integer(9));
//      costBtwn.put("1to2", new Integer(3));
//      costBtwn.put("1to3", new Integer(7));
//      costBtwn.put("2to3", new Integer(6));
      
      //read in file
      //Assumes every file is complete & uncorrupted, can add error handling in the future
      //handles known # of cities.
      
      
      for(int j = 0; j < num + 1; j++){
         cities[j] = j;
      }
      cities[num] = 0;
      
      //parses 8 lines of explanatory information, change according to file header. 
      for(int z = 0; z<8;z++){
       tMatrix.nextLine();  
      }
      
      
      for(int k = 0; k < num; k++){
         for(int i = k + 1; i < num; i++){
          costBtwn.put(k +"to" + i, new Integer(Integer.parseInt(tMatrix.next())));  
         }
      }
           
//      for(int i : cities){
//         System.out.print(i + " ");
//      }
//       System.out.println("\n" + costBtwn.toString());  
//         
   
      
      
     
      
      //we want an array of ints (each line number) that represent each city (we're doing ints instead of A,B,C,etc)
      /* int numCities; // number of lines of input
       int[] cities = new int[numCities];
       cities[0] = 0;
       cities[numCities-1] = 0;*/
      
     permute(cities, 1, cities.length-1);
     System.out.println("The smallest cost is: " + getCost(permutations));
     long endTime = System.nanoTime();
     
     
     System.out.println("Running time is: " + (endTime-startTime)/1000000000.0 + " seconds");
      
      
      
      
      
   }
   
   public static int[] swap(int[] cities, int l, int i) {
      int temp = cities[l];
      cities[l] = cities[i];
      cities[i] = temp;
      return cities;
   }
   
   //remember that l and r are the middles (ie not front and back)
   public static void permute(int[] cities, int l, int r) {
      if (l==r) {
         
         permutations.add(cities);
      }
      else {
         for (int i = l; i <= r; i++) {
            cities = swap(cities, l, i);
            permute(cities, l+1, r);
            cities = swap(cities,l,i);
         }
      }
   }
   
   public static int getCost(ArrayList<int[]> permutations) {
      int tempCost = 0;
      int minCost = Integer.MAX_VALUE;
      for (int[] path : permutations) {
         for (int i = 0; i < path.length-1; i++) {
            int j = i+1;
            String key;
            if (path[i] <= path[j])
               key = path[i]+ "to" + path[j];
            else {
               key = path[j] + "to" + path[i];
            }
            tempCost += costBtwn.get(key).intValue();
         }
         if (tempCost < minCost)
            minCost = tempCost;
         tempCost = 0;
      }
      return minCost;
   }
   
}
