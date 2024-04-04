public class MatrixSpiral {

    static int testNumber = 0;

    public static void main(String[] args) {
        String[] arr1 = new String[] { "1,2,3", "4,5,6", "7,8,9" };
        test(arr1, "1,2,3,6,9,8,7,4,5");

        String[] arr2 = new String[] { "1,2", "4,5"};
        test(arr2, "1,2,5,4");

        String[] arr3 = new String[] { "1, 2, 3, 4", "5, 6, 7, 8"};
        test(arr3, "1,2,3,4,8,7,6,5");

        String[] arr4 = new String[] { "1, 2", "3, 4", "5, 6", "7, 8"};
        test(arr4, "1,2,4,6,8,7,5,3");

        String[] arr5 = new String[] { "1,2,3, 4", "5,6, 7, 8", "9, 10, 11, 12", "13, 14, 15, 16" };
        test(arr5, "1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10");
    }

    private static void test(String[] input, String result){
        String current = createSpiral(input);
        testNumber++;
        if(!current.equals(result))
            throw new RuntimeException("test " + testNumber +  " failed! " +  current + " is different from " + result);

        System.out.println("test " + testNumber + " passed!");
        System.out.println(current);
    }

    public static String createSpiral(String[] arrStr) {

        String[][] matrix = getMatrix(arrStr);
        printMatrix(matrix);
        System.out.println("\n----");
        String result = "";
        // print first row, last column, last row inversed, firs column inversed

        int firstRow = 0;
        int lastRow = matrix.length - 1;
        int firstColumn = 0;
        int lastColumn = matrix[0].length - 1;

        while (firstRow <= lastRow && firstColumn <= lastColumn) {
            // printFirstRow
            for (int i = firstColumn; i <= lastColumn; i++)
                result += "," + matrix[firstRow][i];

            // printLastColumn
            for (int i = firstRow + 1; i < lastRow; i++)
                result += "," + matrix[i][lastColumn];

            // printLastRow
            if(firstRow != lastRow)
                for (int i = lastColumn; i >= firstColumn; i--)
                    result += "," + matrix[lastRow][i];

            // printFirstColumn
            if(lastColumn != firstColumn)
                for (int i = lastRow - 1; i > firstRow; i--)
                    result += "," + matrix[i][firstColumn];

            firstRow++;
            lastRow--;
            firstColumn++;
            lastColumn--;
        }
        return result.substring(1);
    }

    private static String[][] getMatrix(String[] arrStr) {

        String[][] result = new String[arrStr.length][];

        for (int i = 0; i < arrStr.length; i++) {
            String[] row = arrStr[i].replaceAll("\\s", "").split(",");
            result[i] = new String[row.length];
            for (int j = 0; j < row.length; j++)
                result[i][j] = row[j];
        }

        return result;
    }

    private static void printMatrix(String[][] matrix) {

        for (String[] arr : matrix){
            System.err.println();
            for (String str : arr)
                System.out.print(" " + str);
        }

    }
}