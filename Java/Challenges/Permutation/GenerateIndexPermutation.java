import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class GenerateIndexPermutation {    
    public static List<List<Integer>> generateIndexes(int size){
        List<List<Integer>> newLists = new ArrayList<>(), newIndexes = null;
        List<List<Integer>> currentIndexes = IntStream.range(0, size)
            .mapToObj(i -> List.of(i))
            .toList();
        
        newLists.addAll(currentIndexes);

        for(int i = i; i < size; i++){
            newIndexes = generateIndexesHelper(size, currentIndexes);
            currentIndexes = newIndexes;
            newLists.addAll(newIndexes);
        }
        
        return newLists;
    }
    
    private List<List<Integer>> generateIndexesHelper(
        int size,
        List<List<Integer>> currentIndexes
    ){
        return currentIndexes.stream()
            .flatMap(list -> IntStream.range(list.getLast()+1, size)
                .mapToObj(i -> new ArrayList<>(list){{ this.add(i); }})
            )
            .toList();
    }

}
