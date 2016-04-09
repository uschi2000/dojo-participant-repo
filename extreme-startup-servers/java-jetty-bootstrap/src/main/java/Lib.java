import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.Collections;

public class Lib {
    public static int min(Iterable<Integer> ints) {
        return Collections.min(ImmutableList.copyOf(ints));
    }

    public static int max(Iterable<Integer> ints) {
        return Collections.max(ImmutableList.copyOf(ints));
    }

    public static Iterable<Integer> toInt(Iterable<String> ints) {
        return Iterables.transform(ints, Integer::parseInt);
    }

    public static String toString(Iterable<Integer> ints) {
        Joiner joiner = Joiner.on(", ");
        return joiner.join(ints);
    }


    public static int multiply(Iterable<Integer> ints) {
        int m = 1;
        for (int i : ints) {
            m *= i;
        }
        return m;
    }

    public static boolean isSquare(int n) {
        int root = (int) Math.sqrt(n);
        return root*root == n;
    }

    public static boolean isCube(int n) {
        int a = (int) Math.round(Math.pow(n, 1.0/3.0));
        return a * a * a == n;
    }
}
