import java.util.Arrays;
import java.util.List;

/*
 * Design a function which consumes a string and produces a string.
 * The input string can have "?", which can be any alphabetic character.
 * If the input string is or can generate a palindrome, the palindrome is returned.
 * If not, the function returns the string "NO".
 */

public class HasPalindrome {

    public static void main(String args[]) {
        List<String> example1 = Arrays.asList("?a", "abba", "ab?ab", "?????", "abbba", "b???");

        example1.forEach(s -> System.out.println(s + ":" + solution(s)));
    }

    public static String solution(String S) {
        return helper(S, (S.length() % 2));
    }

    private static String helper(String S, int upperBoundSubtraction) {
        String[] sSplited = S.split("");
        String[] ans = S.split("");
        int half = S.length() / 2;
        int j = S.length() - 1;

        if (upperBoundSubtraction == 1 && sSplited[half].equals("?")) {
            ans[half] = "a";
        }

        for (int i = 0; i <= (half - upperBoundSubtraction); i++, j--) {
            if (sSplited[i].equals("?") && sSplited[j].equals("?")) {
                ans[i] = "a";
                ans[j] = "a";
                continue;
            }

            if (sSplited[i].equals("?")) {
                ans[i] = sSplited[j];
                continue;
            }

            if (sSplited[j].equals("?")) {
                ans[j] = sSplited[i];
                continue;
            }

            if (sSplited[i].equals(sSplited[j])) {
                continue;
            }
            return "NO";
        }

        return String.join("", ans);
    }

}