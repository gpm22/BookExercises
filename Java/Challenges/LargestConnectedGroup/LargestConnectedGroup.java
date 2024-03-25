import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LargestConnectedGroup {

    public static void main(String[] args) {
        List<List<Integer>> empty = Arrays.asList(Arrays.asList(0, 0, 0, 0),
                Arrays.asList(0, 0, 0, 0),
                Arrays.asList(0, 0, 0, 0),
                Arrays.asList(0, 0, 0, 0));
        test("empty", empty, 0);
        List<List<Integer>> full = Arrays.asList(Arrays.asList(1, 1, 1, 1),
                Arrays.asList(1, 1, 1, 1),
                Arrays.asList(1, 1, 1, 1),
                Arrays.asList(1, 1, 1, 1));
        test("full", full, 16);
        List<List<Integer>> matrix1 = Arrays.asList(Arrays.asList(1, 1, 1, 0),
                Arrays.asList(1, 0, 0, 0),
                Arrays.asList(0, 0, 0, 1),
                Arrays.asList(0, 0, 0, 0));
        test("1", matrix1, 4);
        List<List<Integer>> matrix2 = Arrays.asList(Arrays.asList(1, 1, 1, 1),
                Arrays.asList(1, 0, 0, 1),
                Arrays.asList(1, 0, 0, 1),
                Arrays.asList(1, 1, 1, 1));
        test("2", matrix2, 12);
        List<List<Integer>> matrix3 = Arrays.asList(Arrays.asList(0, 0, 0, 0),
                Arrays.asList(0, 1, 1, 0),
                Arrays.asList(0, 1, 1, 0),
                Arrays.asList(0, 0, 0, 0));
        test("3", matrix3, 4);
    }

    public static void test(String name, List<List<Integer>> matrix, int expected) {
        int result = largestConnectedGroupSize(matrix);

        System.out.print("test " + name + " - Correct? ");
        if (result == expected) {
            System.out.println("yes");
        } else {
            System.out.println("NOOOOOOOOOO");
            System.err.println(result + " is not " + expected);
        }
    }

    public static int largestConnectedGroupSize(List<List<Integer>> matrix) {
        List<Set<Pair>> groups = generateConnectedGroups(matrix);
        int max = 0;
        for (Set<Pair> group : groups) {
            int newMax = group.size();
            if (newMax > max)
                max = newMax;
        }

        return max;
    }

    private static List<Set<Pair>> generateConnectedGroups(List<List<Integer>> matrix) {

        List<Set<Pair>> groups = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 0)
                    continue;

                Set<Pair> connectedNeighbors = getConnectedNeighbors(i, j, matrix);
                joinGroups(connectedNeighbors, groups);

            }
        }
        return groups;
    }

    private static Set<Pair> getConnectedNeighbors(int i, int j, List<List<Integer>> matrix) {
        Set<Pair> newGroup = new HashSet<>();
        newGroup.add(new Pair(i, j));

        int maxI = matrix.size() - 1;
        int maxJ = matrix.get(i).size() - 1;

        if (j > 0 && matrix.get(i).get(j - 1) == 1)
            newGroup.add(new Pair(i, j - 1));

        if (j < maxJ && matrix.get(i).get(j + 1) == 1)
            newGroup.add(new Pair(i, j + 1));

        if (i > 0) {
            if (matrix.get(i - 1).get(j) == 1)
                newGroup.add(new Pair(i - 1, j));

            if (j > 0 && matrix.get(i - 1).get(j - 1) == 1)
                newGroup.add(new Pair(i - 1, j - 1));

            if (j < maxJ && matrix.get(i - 1).get(j + 1) == 1)
                newGroup.add(new Pair(i - 1, j + 1));
        }

        if (i < maxI) {
            if (matrix.get(i + 1).get(j) == 1)
                newGroup.add(new Pair(i + 1, j));

            if (j > 0 && matrix.get(i + 1).get(j - 1) == 1)
                newGroup.add(new Pair(i + 1, j - 1));

            if (j < maxJ && matrix.get(i + 1).get(j + 1) == 1)
                newGroup.add(new Pair(i + 1, j + 1));
        }

        return newGroup;
    }

    private static void joinGroups(Set<Pair> connectedElements, List<Set<Pair>> groups) {
        Set<Pair> newGroup = new HashSet<>();

        for (Pair element : connectedElements) {
            Set<Pair> group = getGroup(element, groups);

            if (group.isEmpty()) {
                newGroup.add(element);
            } else {
                newGroup.addAll(group);
                groups.remove(group);
            }
        }

        groups.add(newGroup);
    }

    private static Set<Pair> getGroup(Pair pos, List<Set<Pair>> groups) {

        for (Set<Pair> group : groups)
            if (group.contains(pos))
                return group;

        return Collections.emptySet();
    }

    private static class Pair {
        private Integer i;
        private Integer j;

        public Pair(Integer i, Integer j) {
            this.i = i;
            this.j = j;
        }

        public int hashCode() {
            return i.hashCode() * j.hashCode();
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;

            if (!(o instanceof Pair))
                return false;

            Pair other = (Pair) o;

            return this.i.equals(other.getI()) && this.j.equals(other.getJ());
        }

        public Integer getI() {
            return this.i;
        }

        public Integer getJ() {
            return this.j;
        }

        public String toString() {
            return "Pair(i=" + this.i + ", j=" + this.j + ")";
        }
    }

}
