import java.util.*;

public class Sorting{

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr2 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr3 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr4 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr5 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr6 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        List<Integer> arr7 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1, 12));
        List<Integer> arr8 = new ArrayList<>(Arrays.asList(10, 9, 8, 4, 3, 1));
        System.out.println(arr);
        selectSort(arr);
        System.out.println(arr);
        System.out.println(arr2);
        bubbleSort(arr2);
        System.out.println(arr2);
        System.out.println(arr3);
        insertSort(arr3);
        System.out.println(arr3);
        System.out.println(arr4);
        shellSort(arr4);
        System.out.println(arr4);
        System.out.println(arr5);
        bubbleShellSort(arr5);
        System.out.println(arr5);
        System.out.println(arr6);
        mergeSort(arr6);
        System.out.println(arr6);
        System.out.println(arr7);
        mergeSort(arr7);
        System.out.println(arr7);
        System.out.println(arr8);
        quickSort(arr8);
        System.out.println(arr8);

        System.out.println("\nTest with ordered list\n");
        bubbleSort(arr2);
        selectSort(arr2);
        insertSort(arr2);
        shellSort(arr2);
        bubbleShellSort(arr2);
        mergeSort(arr2);
        System.out.println("\nTest with reversed list\n");
        Collections.reverse(arr2);
        bubbleSort(arr2);
        Collections.reverse(arr2);
        selectSort(arr2);
        Collections.reverse(arr2);
        insertSort(arr2);
        Collections.reverse(arr2);
        shellSort(arr2);
        Collections.reverse(arr2);
        bubbleShellSort(arr2);
        Collections.reverse(arr2);
        mergeSort(arr2);

        System.out.println("\nBubble sort x Insert sort x Shell Sort\n");
        List<Integer> arrr1 = new ArrayList<>(Arrays.asList(1, 5, 4, 7, 6, 9, 10, 11));
        List<Integer> arrr2 = new ArrayList<>(Arrays.asList(1, 5, 4, 7, 6, 9, 10, 11));
        List<Integer> arrr3 = new ArrayList<>(Arrays.asList(1, 5, 4, 7, 6, 9, 10, 11));
        List<Integer> arrr4 = new ArrayList<>(Arrays.asList(1, 5, 4, 7, 6, 9, 10, 11));
        System.out.println(arrr1);
        bubbleSort(arrr1);
        System.out.println(arrr1);
        System.out.println(arrr2);
        insertSort(arrr2);
        System.out.println(arrr2);
        System.out.println(arrr3);
        shellSort(arrr3);
        System.out.println(arrr3);
        System.out.println(arrr4);
        bubbleShellSort(arrr4);
        System.out.println(arrr4);
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
            if(!swap)
                break;
        }
        System.out.println("bubbleSort steps:" + numberOfSteps + " for n:" + list.size());
    }
    private static <T extends Comparable<T>> int insertSort(List<T> list){
        return insertSort(list, 1, true);
    }

    private static <T extends Comparable<T>> int insertSort(List<T> list, int increment, boolean log){
        int numberOfSteps = 0;

        for(int j = 1; j + increment < list.size(); j++){
            for(int i = j + increment; i - increment >= 0 ; i -= increment){
                numberOfSteps++;
                T valueToCompare = list.get(i);
                swap(list, i-increment, i);
                if(valueToCompare.equals(list.get(i)))
                    break;
            }
        }

        if(log)
            System.out.println("insertionSort steps:" + numberOfSteps + " for n:" + list.size());

        return numberOfSteps;
    }

    private static <T extends Comparable<T>> void  bubbleShellSort(List<T> list){
        int numberOfSteps = 0;
        int step = list.size()/2;

        while(true){
            for(int i = 0; i < list.size(); i++){
                if(i+step > list.size()-1)
                    break;
                numberOfSteps++;
                swap(list, i, i+step);
            }

            if(step == 1)
                break;

            step = (int) Math.ceil(step/2.0);
        }
        System.out.println("bubbleShell steps:" + numberOfSteps + " for n:" + list.size());
    }


    private static <T extends Comparable<T>> void  shellSort(List<T> list){
        int numberOfSteps = 0;
        int increment = list.size()/2;
        while(increment >= 1){
            numberOfSteps += insertSort(list, increment, false);
            increment /= 2;
        }
        System.out.println("shellSort steps:" + numberOfSteps + " for n:" + list.size());
    }

    private static <T extends Comparable<T>> void mergeSort(List<T> list){

        int numberOfSteps = 0;
        for(int step = 1; step < list.size(); step*=2){
            for(int i = 0; i < list.size(); i+=2*step){
              numberOfSteps +=  merge(list, i, i+step-1, i+step, i+2*step-1);;
            }
        }

        System.out.println("mergeSort steps:" + numberOfSteps + " for n:" + list.size());
    }

    private static <T extends Comparable<T>> int merge(List<T> list,
    int startFirstList, int endFirstList, int startSecondList, int endSecondList){
        if(startSecondList >= list.size())
            return 1;
        
        int indexFirstList = startFirstList;
        int indexSecondList = startSecondList;
        int numberOfSteps = 0;
        while(indexFirstList <= endFirstList && (indexSecondList <= endSecondList && indexSecondList < list.size() )){
            numberOfSteps++;
            T firstValue = list.get(indexFirstList);
            T secondValue = list.get(indexSecondList);
            if(firstValue.compareTo(secondValue) < 0 ){
                indexFirstList++;
                continue;
            } 

            list.set(indexFirstList, secondValue);
            
            if(indexSecondList < endSecondList && indexSecondList < list.size()-1){
                T nextSecondValue = list.get(indexSecondList+1);
                if(nextSecondValue.compareTo(firstValue) < 0){
                    list.set(indexSecondList, nextSecondValue);
                    list.set(indexSecondList+1, firstValue);
                } else {
                    list.set(indexSecondList, firstValue);
                }
            } else {
                list.set(indexSecondList, firstValue);
            }


            indexFirstList++;
            if(indexFirstList == endFirstList){
                endFirstList++;
                indexSecondList++;
            }
        }
        return numberOfSteps;
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list){
        int numberOfSteps = quickSort(list, 0, list.size()-1);
        System.out.println("quickSort steps:" + numberOfSteps + " for n:" + list.size());
    }


    private static <T extends Comparable<T>> int quickSort(List<T> list, int firstIndex, int secondIndex){
        if(firstIndex == secondIndex || firstIndex > secondIndex || secondIndex >= list.size())
            return 1;

        int pivotIndex = firstIndex;
        int rightIndex = rightSearch(list, pivotIndex, secondIndex);    
        int leftIndex = -1;
        int numberOfSteps = 0;
        while(leftIndex != -1 || rightIndex != -1){

            if(rightIndex !=-1){
                quickSwap(list, pivotIndex, rightIndex);
                pivotIndex = rightIndex;
                rightIndex = -1;
                leftIndex = leftSearch(list, pivotIndex, firstIndex);

                if(leftIndex > -1){
                    numberOfSteps += pivotIndex - leftIndex+1;
                } else {
                    numberOfSteps += pivotIndex - firstIndex+1;
                }
            }

            if(leftIndex !=-1){
                quickSwap(list, pivotIndex, leftIndex);
                pivotIndex = leftIndex;
                leftIndex = -1;
                rightIndex = rightSearch(list, pivotIndex, secondIndex);

                if(rightIndex > -1){
                    numberOfSteps += rightIndex- pivotIndex+1;
                } else {
                    numberOfSteps += secondIndex - pivotIndex+1; 
                }
            }
        }
        numberOfSteps += quickSort(list, firstIndex, pivotIndex-1);
        numberOfSteps += quickSort(list, pivotIndex+1, secondIndex);
        return numberOfSteps;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static <T extends Comparable<T>> int rightSearch(List<T> list, int pivotIndex, int rightIndex){
        for(int i = rightIndex; i > pivotIndex; i--){
            if(list.get(rightIndex).compareTo(list.get(pivotIndex)) < 0)
                return i;
        }
        return -1;
    }

    private static <T extends Comparable<T>> int leftSearch(List<T> list, int pivotIndex, int leftIndex){
        for(int i = leftIndex; i < pivotIndex; i++){
            if(list.get(leftIndex).compareTo(list.get(pivotIndex)) > 0)
                return i;
        }
        return -1;
    }

    private static <T extends Comparable<T>> void quickSwap(List<T> list, int pivotIndex, int newIndex){
        T newValue = list.get(newIndex);
        list.set(newIndex, list.get(pivotIndex));
        list.set(pivotIndex, newValue);
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