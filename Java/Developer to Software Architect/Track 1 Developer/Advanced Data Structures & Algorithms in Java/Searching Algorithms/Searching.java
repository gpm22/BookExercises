import java.util.*;
import java.util.function.*;

public class Searching {

    public static void main(String[] args) {
        
        List<BiFunction<List<Integer>, Integer, Integer>> SEARCHS = Arrays.asList(
            (list, element) -> linearSearch(list, element),
            (list, element) -> binarySearch(list, element),
            (list, element) -> jumpSearch(list, element),
            (list, element) -> interpolationSearch(list, element)
        );


        for(BiFunction<List<Integer>, Integer, Integer> fun : SEARCHS)
            test(fun);
    }

    static private int testNumber = 0;
    private static void test(BiFunction<List<Integer>, Integer, Integer> fun){

        testNumber++;
        System.out.println("\n Test number: " + testNumber + "\n");
        List<Integer> list = Arrays.asList(1, 4, 6, 7, 12, 14, 16);

        Integer result;
        System.out.println("Test last element");
        result = fun.apply(list, list.get(list.size()-1));
        System.out.println("correct? " + (result == list.size()-1));

        System.out.println("Test first element");
        result = fun.apply(list, list.get(0));
        System.out.println("correct? " + (result == 0));

        System.out.println("Test middle element");
        result = fun.apply(list, list.get(list.size()/2));
        System.out.println("correct? " + (result ==list.size()/2));

        System.out.println("Test non existent element bellow");
        result = fun.apply(list, -12);
        System.out.println("correct? " + (result == -1));

        System.out.println("Test non existent element above");
        result = fun.apply(list, 40);
        System.out.println("correct? " + (result == -1));
    }
    
    private static <T> int linearSearch(List<T> list, T element){
        int numberOfSteps = 0;
        for(int i = 0; i < list.size(); i++){
            numberOfSteps++;
            if (list.get(i) == element)
                return i;
        } 
        System.out.println("linear - number of steps: " + numberOfSteps);
        return-1 ;
    }
    
    private static <T extends Comparable<T>> int binarySearch(List<T> list, T element){

        int low = 0;
        int high = list.size()-1;
        int max = list.size();
        int count = 0;
        while(low <= high){
            count++;
            int mid = (high-low)/2+low;
            T testElement = list.get(mid);
            if(testElement.equals(element)){
                System.out.println("binary - number of steps: " + count);
                return mid;
            }
            
            if(testElement.compareTo(element) > 0){
                high = mid-1;
            } else {
                low = mid + 1;
            }

            if(count > max)
                throw new RuntimeException("list is not ascended sorted");
        }

        System.out.println("binary - number of steps: " + count);
        return -1;
    }

    private static <T extends Comparable<T>> int jumpSearch(List<T> list, T element){
        int jumptLength = (int) Math.floor(Math.sqrt(list.size()));
        return jumpSearch(list, element, jumptLength);
    }

    private static <T extends Comparable<T>> int jumpSearch(List<T> list, T element, int jumpLength){

    int i;
    int numberOfSteps = 0;
    for(i=0; i < list.size() && list.get(i).compareTo(element) <= 0; i+=jumpLength){
        numberOfSteps++;
    }

    int startIndex = i - jumpLength;
    if(startIndex < 0)
        startIndex = 0;

    int endIndex = Math.min(i, list.size());
    
    for (int j = startIndex; j <endIndex; j++){
        numberOfSteps++;
        if(list.get(j).equals(element)){
            System.out.println("jump - number of steps: " + numberOfSteps);
            return j;
        }
    }

    System.out.println("jump - number of steps: " + numberOfSteps);
    return -1;
}
    private static int interpolationSearch(List<Integer> list, Integer element){
        int low = 0;
        int high = list.size() -1;
        int numberOfSteps = 0;
        while(low <= high){
            numberOfSteps++;
            int mid = low + ((element - list.get(low))*(high-low))/(list.get(high) - list.get(low));

            if(mid < 0 | mid > list.size() - 1)
                break;
        
            if(list.get(mid) == element){
                System.out.println("interpolation - number of steps: " + numberOfSteps);
                return mid;
            }

            if(list.get(mid) < element){
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        System.out.println("interpolation - number of steps: " + numberOfSteps);
        return -1;
    }
}
