import com.google.common.collect.Iterables;

public class Lib {
    public static int max(Iterable<Integer> ints) {
        int max = Integer.MIN_VALUE;
        for (Integer i : ints) {
            if (i >= max) {
                max = i;
            }
        }
        return max;
    }

    public static Iterable<Integer> toInt(Iterable<String> ints) {
        return Iterables.transform(ints, Integer::parseInt);
    }

}
