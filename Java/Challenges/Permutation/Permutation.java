import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static int steadyGene(String gene) {
        int steadyN = gene.length()/4;
        
        Map<Character, Integer> countMap = getCountMap(gene);
        Map<Character, Integer> neededMap = getNeededMap(countMap, steadyN);
        
        System.out.println("countMap - " + countMap);
        if (neededMap.get('A') == neededMap.get('C') && 
        neededMap.get('A') == neededMap.get('G') && 
        neededMap.get('G') == neededMap.get('T'))
            return 0;
            
        if (countMap.get('A') == gene.length() || 
        countMap.get('C') == gene.length() || 
        countMap.get('T') == gene.length() ||
        countMap.get('G') == gene.length())
            return steadyN*3;

        return minSubStringLen(gene, neededMap);
    }
    
    private static Map<Character, Integer> getCountMap(String gene){
        Map<Character, Integer> countMap = getInitializedMap();
        
        for(Character ch: gene.toCharArray()){
            countMap.put(ch, countMap.get(ch)+1);
        }
        
        return countMap;
    }
    
    private static Map<Character, Integer> getNeededMap(Map<Character, Integer> countMap, int steadyN){
        
        Map<Character, Integer> neededMap = new HashMap<>();
        
        for(Character ch: countMap.keySet())
            neededMap.put(ch, calculateNeed(ch, countMap, steadyN));
        
        return neededMap;
    }
    
    private static int calculateNeed(char ch, Map<Character, Integer> countMap, int steadyN){
        int result = countMap.get(ch) - steadyN;
        
        return result > 0 ? result : 0;
    }
    
    private static Map<Character, Integer> getInitializedMap(){
        Map<Character, Integer> countMap = new HashMap<>();
        countMap.put('C', 0);
        countMap.put('G', 0);
        countMap.put('A', 0);
        countMap.put('T', 0);
        return countMap;
    }
    
    private static Map<Character, LinkedList<Integer>> getInitializedMapList(){
        Map<Character, LinkedList<Integer>> indexMap = new HashMap<>();
        
        indexMap.put('C', new LinkedList<>());
        indexMap.put('G', new LinkedList<>());
        indexMap.put('A', new LinkedList<>());
        indexMap.put('T', new LinkedList<>());
        
        return indexMap;
    }
    
    private static int minSubStringLen(String gene, Map<Character, Integer> needMap){
        Map<Character, Integer> countMap = getInitializedMap();
        Map<Character, LinkedList<Integer>> indexMap = getInitializedMapList();
        
        int min = Integer.MAX_VALUE;
        int minSizePossible = needMap.get('C') + needMap.get('G') + needMap.get('A') + needMap.get('T');
        boolean isCountAchieved = false;
        
        for(int i = 0; i < gene.length(); i++){
            char currch = gene.charAt(i);
            int needed = needMap.get(currch);
            
            if(needed < 1)
                continue;
            
            if (countMap.get(currch) < needed){
                countMap.put(currch, countMap.get(currch) + 1);
            } else {
                indexMap.get(currch).remove();
            }
            
            indexMap.get(currch).addLast(i);
            
            if(!isCountAchieved){
                int solSize = countMap.get('A') + countMap.get('C') + countMap.get('T') + countMap.get('G');
                
                if (solSize != minSizePossible)
                    continue;
                
                isCountAchieved = true;
            }
            
            int newMin = calculateNewMin(indexMap);
            if (newMin < min){
                min = newMin;
                
                boolean isLowestPossible = min == minSizePossible;
                if(isLowestPossible)
                    return min;
            }
        }
        
        return min;
    }
    
    private static int calculateNewMin(Map<Character, LinkedList<Integer>> indexMap){
        int minIndex = Integer.MAX_VALUE;
        int maxIndex = Integer.MIN_VALUE;
        
        for(LinkedList<Integer> list: indexMap.values()){
            if(list.isEmpty())
                continue;
            
            int firstElement = list.getFirst();
            if(firstElement < minIndex)
                minIndex = firstElement;
                
            int lisSize = list.size();
            if(lisSize == 1)
                continue;
            
            int lastElement = list.getLast();
            if(lastElement > maxIndex)
                maxIndex = lastElement;
        }

        return maxIndex - minIndex + 1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String gene = bufferedReader.readLine();

        int result = Result.steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

