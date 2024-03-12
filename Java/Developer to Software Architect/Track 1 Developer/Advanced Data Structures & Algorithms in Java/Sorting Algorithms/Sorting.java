import java.util.*;

public class Sorting{

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr2 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        System.out.println(arr);
        selectSort(arr);
        System.out.println(arr);
        System.out.println(arr2);
        bubbleSort(arr2);
        System.out.println(arr2);
        bubbleSort(arr2);
        selectSort(arr2);
    }

    private static <T extends Comparable<T>> void selectSort(List<T> list){

        int numberOfSteps = 0;
        for(int i = 0; i<list.size(); i++){
            for(int j = i + 1; j< list.size(); j++){
                numberOfSteps++;
                swap(list, i, j);
            }
        }
        System.out.println("selectSort steps:" + numberOfSteps+ " for n:" + list.size());
    }

    private static <T extends Comparable<T>> void bubbleSort(List<T> list){

        boolean swap;
        int numberOfSteps = 0;
        for(int i = 0; i <list.size(); i++){
            swap = false;
            for(int j = 0; j < list.size()-1;j++){
                numberOfSteps++;
                T valueToCompare = list.get(j);
                swap(list, j, j+1);

                if(!valueToCompare.equals(list.get(j)))
                    swap = true;
            }
            if(!swap){
                System.out.println("bubbleSort steps:" + numberOfSteps + " for n:" + list.size());
                return;
            }
        }
        System.out.println("bubbleSort steps:" + numberOfSteps + " for n:" + list.size());
    }

    private static <T extends Comparable<T>> void swap(List<T> list, int firstIndex, int secondIndex){
        T firstValue = list.get(firstIndex);
        T secondValue = list.get(secondIndex);
        if(secondValue.compareTo(firstValue) < 0){
            list.set(firstIndex, secondValue);
            list.set(secondIndex, firstValue);
        }
    }
}