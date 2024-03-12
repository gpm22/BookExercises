import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class GoodSubSequences {

    public static void main(String[] args) {

        // TODO
        // The solution is not complete yet, the subsequences are not being generated
        // correctly, as there are duplicated subsequences.
        List<Integer> test1 = Arrays.asList(1, 2, 3, 4);

        System.out.println(numberOfGoodSubSequences(test1));

    }

    private static int numberOfGoodSubSequences(List<Integer> arr) {

        int goodSubSequences = 0;

        List<List<Integer>> subs = subSequences(arr);
        System.out.println(subs);

        for (List<Integer> sub : subs) {
            if (isGood(sub))
                goodSubSequences++;
        }
        return goodSubSequences;
    }

    private static List<List<Integer>> subSequences(List<Integer> arr) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(arr);

        List<List<Integer>> helper = subSequencesHelper(arr);
        while (!helper.isEmpty()) {
            result.addAll(helper);

            List<List<Integer>> helper2 = new ArrayList<>();
            for (List<Integer> list : helper) {
                List<List<Integer>> helper3 = subSequencesHelper(list);
                if (helper3.get(0).isEmpty())
                    return result;

                helper2.addAll(helper3);
            }
            helper = new ArrayList<>(helper2);
        }
        return result;
    }

    private static List<List<Integer>> subSequencesHelper(List<Integer> arr) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            List<Integer> copy = new ArrayList<>(arr);
            copy.remove(i);
            result.add(copy);
        }

        return result;
    }

    private static boolean isGood(List<Integer> arr) {
        if (arr.size() == 1)
            return true;

        HashSet<Integer> set = new HashSet<>();

        for (Integer each : arr) {
            if (!set.add(each))
                return false;
        }

        Integer min = Collections.min(arr);
        Integer max = Collections.max(arr);

        for (int i = min; i < max; i++) {
            if (!set.contains(i))
                return false;
        }

        return true;

    }
}