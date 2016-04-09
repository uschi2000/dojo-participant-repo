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

    public static int multiply(Iterable<Integer> ints) {
        int m = 1;
        for (int i : ints) {
            m *= 1;
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
