public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando teste...");

        // String stringVals = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14";
        String stringVals = "0 1 2 null 4 5 6 7 8 9 10 null 12 13 14";
        System.out.println("Valores: \n" + stringVals);

        String[] stringArrayVals = stringVals.split(" ");

        Integer[] testVals = new Integer[stringArrayVals.length];

        for (int i = 0; i < stringArrayVals.length; i++) {
            try {
                testVals[i] = Integer.parseInt(stringArrayVals[i]);
            } catch (Exception e) {
                testVals[i] = null;
            }

        }

        BinaryTree binaryTree = BinaryTree.createBinaryTree(testVals);

        System.out.println("Filhos únicos: \n" + binaryTree.onlyChildren());

        binaryTree.traverse(binaryTree.getRoot());

    }
}
