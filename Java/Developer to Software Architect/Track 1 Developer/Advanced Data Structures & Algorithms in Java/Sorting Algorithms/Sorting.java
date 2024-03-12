import java.util.*;

public class Sorting{

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        System.out.println(arr);
        selectSort(arr);
        System.out.println(arr);
    }

    private static <T extends Comparable<T>> void selectSort(List<T> list){

        for(int i = 0; i<list.size(); i++){
            for(int j = i + 1; j< list.size(); j++){
                T firstValue = list.get(i);
                T secondValue = list.get(j);
                if(secondValue.compareTo(firstValue) < 0){
                    list.set(i, secondValue);
                    list.set(j, firstValue);
                }
            }
        }
    }

}