public class MatrixSpiral {

    public static void main(String[] args) {
        String[] arr1 = new String[] { "1,2,3", "4,5,6", "7,8,9" };
        System.out.println(createSpiral(arr1));
    }

    public static String createSpiral(String[] arrStr) {

        String[][] matrix = getMatrix(arrStr);
        printMatrix(matrix);
        System.out.println("----");
        String result = "";
        // print first row, last column, last row inversed, firs column inversed

        int firstRow = 0;
        int lastRow = matrix.length - 1;
        int firstColumn = 0;
        int lastColumn = matrix[0].length - 1;

        while (firstRow <= lastRow && firstColumn <= lastColumn) {
            System.out.println("1");
            System.out.println(result);
            // printFirstRow
            for (int i = firstColumn; i <= lastColumn; i++)
                result += result + "," + matrix[firstRow][i];

            System.out.println("2");
            System.out.println(result);
            // printLastColumn
            for (int i = firstRow + 1; i < lastRow; i++)
                result += result + "," + matrix[i][lastColumn];

            System.out.println("3");
            System.out.println(result);
            // printLastRow
            for (int i = lastColumn; i >= firstColumn; i--)
                result += result + "," + matrix[lastRow][i];

            System.out.println("4");
            System.out.println(result);
            // printFirstColumn
            for (int i = lastRow - 1; i > firstRow; i--)
                result += result + "," + matrix[i][firstColumn];

            System.out.println("5");
            System.out.println(result);
            System.out.println("---------------");
            firstRow++;
            lastRow--;
            firstColumn++;
            lastColumn--;
        }
        return result;
    }

    private static String[][] getMatrix(String[] arrStr) {

        String[][] result = new String[arrStr.length][];

        for (int i = 0; i < arrStr.length; i++) {
            String[] row = arrStr[i].split(",");
            result[i] = new String[row.length];
            for (int j = 0; j < row.length; j++)
                result[i][j] = row[j];
        }

        return result;
    }

    private static void printMatrix(String[][] matrix) {

        for (String[] arr : matrix)
            for (String str : arr)
                System.out.print(str);
    }
}