import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashmapVsSwitch {

    public static void main(String[] args) {
        System.out.println("Starting test");

        long inicioSwitch = System.currentTimeMillis();

        for (int i = 2000; i < 9999; i++) {
            HashmapVsSwitch.rotationDaySwitch(String.valueOf(i));
        }

        long timeSwitch = System.currentTimeMillis() - inicioSwitch;
        System.out.println("switch : " + timeSwitch * 1000);

        long inicioArray = System.currentTimeMillis();

        for (int i = 2000; i < 3000; i++) {
            HashmapVsSwitch.rotationDayArray(String.valueOf(i));
        }

        long timeArray = System.currentTimeMillis() - inicioArray;
        System.out.println("Array : " + timeArray * 1000);

        long inicioHashMap = System.currentTimeMillis();

        for (int i = 2000; i < 3000; i++) {
            HashmapVsSwitch.rotationDayHashMap(String.valueOf(i));
        }

        long timeHashMap = System.currentTimeMillis() - inicioHashMap;
        System.out.println("HashMap : " + timeHashMap * 1000);

        System.out.println("Stoping test");
    }

    final private static Map<String, Integer> rotationDay = Stream.of(new Object[][] {
            { "0", Calendar.MONDAY },
            { "1", Calendar.MONDAY },
            { "2", Calendar.TUESDAY },
            { "3", Calendar.TUESDAY },
            { "4", Calendar.WEDNESDAY },
            { "5", Calendar.WEDNESDAY },
            { "6", Calendar.THURSDAY },
            { "7", Calendar.THURSDAY },
            { "8", Calendar.FRIDAY },
            { "9", Calendar.FRIDAY },
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    final private static int[] rotationDayArray = {
            Calendar.MONDAY,
            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY,
            Calendar.FRIDAY };

    public static int rotationDayHashMap(String year) {
        String lastDigit = year.substring(3, 4);
        return rotationDay.get(lastDigit);
    }

    public static int rotationDayArray(String year) {
        int lastDigit = Integer.parseInt(year.substring(3, 4));
        return rotationDayArray[lastDigit];
    }

    public static int rotationDaySwitch(String year) {
        int lastDigit = Integer.parseInt(year.substring(3, 4));

        return switch (lastDigit) {
            case 0, 1 -> Calendar.MONDAY;
            case 2, 3 -> Calendar.TUESDAY;
            case 4, 5 -> Calendar.WEDNESDAY;
            case 6, 7 -> Calendar.THURSDAY;
            case 8, 9 -> Calendar.FRIDAY;
            default -> -1;
        };
    }

}