import java.util.*;

public class MorseCode {

    public static List<String> possibilities(String signals) {

        LinkedHashMap<String, String> possibleLettersMap = possibleLettersMap();
        List<String> possibleLetters = new ArrayList<String>();

        String[] signalsSplited = signals.split("");

        if (signalsSplited.length == 3) {

            if (signals.equals("???")) {
                possibleLettersMap.keySet().forEach((i) -> {
                    if (i.length() == 3) {
                        possibleLetters.add(possibleLettersMap.get(i));
                    }

                });
                return possibleLetters;
            }

            if (signalsSplited[0].equals("?") && !signalsSplited[1].equals("?") && !signalsSplited[2].equals("?")) {
                possibleLetters.add(possibleLettersMap.get("." + signalsSplited[1] + signalsSplited[2]));
                possibleLetters.add(possibleLettersMap.get("-" + signalsSplited[1] + signalsSplited[2]));
                return possibleLetters;
            }

            if (!signalsSplited[0].equals("?") && signalsSplited[1].equals("?") && !signalsSplited[2].equals("?")) {
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0] + "." + signalsSplited[2]));
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0] + "-" + signalsSplited[2]));
                return possibleLetters;
            }

            if (!signalsSplited[0].equals("?") && !signalsSplited[1].equals("?") && signalsSplited[2].equals("?")) {
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0] + signalsSplited[1] + "."));
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0] + signalsSplited[1] + "-"));
                return possibleLetters;
            }

            if (signalsSplited[0].equals("?") && signalsSplited[1].equals("?") && !signalsSplited[2].equals("?")) {
                possibleLetters.add(possibleLettersMap.get(".." + signalsSplited[2]));
                possibleLetters.add(possibleLettersMap.get(".-" + signalsSplited[2]));
                possibleLetters.add(possibleLettersMap.get("-." + signalsSplited[2]));
                possibleLetters.add(possibleLettersMap.get("--" + signalsSplited[2]));

                return possibleLetters;
            }

            if (signalsSplited[0].equals("?") && !signalsSplited[1].equals("?") && signalsSplited[2].equals("?")) {
                possibleLetters.add(possibleLettersMap.get("." + signalsSplited[1] + "."));
                possibleLetters.add(possibleLettersMap.get("." + signalsSplited[1] + "-"));
                possibleLetters.add(possibleLettersMap.get("-" + signalsSplited[1] + "."));
                possibleLetters.add(possibleLettersMap.get("-" + signalsSplited[1] + "-"));
                return possibleLetters;
            }

            if (!signalsSplited[0].equals("?") && signalsSplited[1].equals("?") && signalsSplited[2].equals("?")) {
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0]) + "..");
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0]) + ".-");
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0]) + "-.");
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0]) + "--");
                return possibleLetters;
            }

        }

        if (signalsSplited.length == 2) {
            if (signalsSplited[0].equals("?") && !signalsSplited[1].equals("?")) {
                possibleLetters.add(possibleLettersMap.get("." + signalsSplited[1]));
                possibleLetters.add(possibleLettersMap.get("-" + signalsSplited[1]));
                return possibleLetters;
            }

            if (!signalsSplited[0].equals("?") && signalsSplited[1].equals("?")) {
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0] + "."));
                possibleLetters.add(possibleLettersMap.get(signalsSplited[0] + "-"));
                return possibleLetters;
            }

            if (signalsSplited[0].equals("?") && signalsSplited[1].equals("?")) {
                possibleLettersMap.keySet().forEach((i) -> {
                    if (i.length() == 2) {
                        possibleLetters.add(possibleLettersMap.get(i));
                    }

                });
                return possibleLetters;
            }
        }

        if (signals.equals("?")) {
            possibleLettersMap.keySet().forEach((i) -> {
                if (i.length() == 1) {
                    possibleLetters.add(possibleLettersMap.get(i));
                }

            });
            return possibleLetters;
        }

        possibleLetters.add(possibleLettersMap.get(signals));
        return possibleLetters;
    }

    private static LinkedHashMap<String, String> possibleLettersMap() {
        LinkedHashMap<String, String> possibleLetters = new LinkedHashMap<String, String>();
        possibleLetters.put(".", "E");
        possibleLetters.put("-", "T");
        possibleLetters.put("..", "I");
        possibleLetters.put(".-", "A");
        possibleLetters.put("-.", "N");
        possibleLetters.put("--", "M");
        possibleLetters.put("...", "S");
        possibleLetters.put("..-", "U");
        possibleLetters.put(".-.", "R");
        possibleLetters.put(".--", "W");
        possibleLetters.put("-..", "D");
        possibleLetters.put("-.-", "K");
        possibleLetters.put("--.", "G");
        possibleLetters.put("---", "O");
        return possibleLetters;
    }
}