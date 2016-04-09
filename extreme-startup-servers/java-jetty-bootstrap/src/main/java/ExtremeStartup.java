import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtremeStartup extends HttpServlet {

    Splitter COMMA_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("q");

        System.out.println("A request has arrived:");
        System.out.println(parameter);

        String answer = answer(parameter);
        System.out.println("Sending response: ");
        System.out.println(answer);
        resp.getWriter().write(answer);
    }

    private static final Pattern q1 = Pattern.compile(".*what is (\\d+) plus (\\d+)");
    private String answerQ1(Matcher q1Matcher) {
        return String.valueOf(Integer.parseInt(q1Matcher.group(1))
                + Integer.parseInt(q1Matcher.group(2)));
    }

    Pattern q2Pattern = Pattern.compile(".*which of the following numbers is the largest: (.*)");
    String q2(Matcher q2Matcher) {
        String list = q2Matcher.group(1);
        Iterable<Integer> nums = splitOnComma(list);
        int max = Lib.max(nums);
        return Integer.toString(max);
    }

    private Iterable<Integer> splitOnComma(String list) {
        return Lib.toInt(COMMA_SPLITTER.split(list));
    }

    private static final Pattern q3 = Pattern.compile(".*what is (\\d+) multiplied by (\\d+)");
    private String answerQ3(Matcher q1Matcher) {
        return String.valueOf(Integer.parseInt(q1Matcher.group(1))
                * Integer.parseInt(q1Matcher.group(2)));
    }

    private static final Pattern q4 = Pattern.compile(".*who is the Prime Minister of Great Britain");
    private String answerQ4(Matcher q1Matcher) {
        return "David Cameron";
    }

    private static final Pattern q5 = Pattern.compile(".*which of the following numbers are primes: (.*)");
    private String answerQ5(Matcher q5Matcher) {
        Iterable<Integer> integers = splitOnComma(q5Matcher.group(1));
        Iterable<Integer> primes = Iterables.filter(integers, Primes::isPrime);
        return Joiner.on(',').join(primes);
    }

    private static final Pattern q6 = Pattern.compile(".*which of the following numbers is both a square and a cube: (.*)");
    private String answerQ6(Matcher matcher) {
        String list = matcher.group(1);
        Iterable<Integer> nums = Lib.toInt(COMMA_SPLITTER.split(list));
        Iterable<Integer> answers = Iterables.filter(nums, num -> Lib.isCube(num) && Lib.isSquare(num));
        return Lib.toString(answers);
    }

    private static final Pattern q8 = Pattern.compile(".*: what is the (\\d+).?.?.? number in the Fibonacci sequence(.*)");
    private String answerQ8(Matcher matcher) {
        String i = matcher.group(1);
        return Integer.toString(Lib.fib(Integer.parseInt(i)));
    }

    String answer(String parameter) {
        try {
            if (parameter == null)
                return "team name";

            Matcher q1Matcher = q1.matcher(parameter);
            if (q1Matcher.matches()) {
                return answerQ1(q1Matcher);
            }
            Matcher q2Matcher = q2Pattern.matcher(parameter);
            if (q2Matcher.matches()) {
                return q2(q2Matcher);
            }
            Matcher q3Matcher = q3.matcher(parameter);
            if (q3Matcher.matches()) {
                return answerQ3(q3Matcher);
            }
            Matcher q4Matcher = q4.matcher(parameter);
            if (q4Matcher.matches()) {
                return answerQ4(q4Matcher);
            }
            Matcher q5Matcher = q5.matcher(parameter);
            if (q5Matcher.matches()) {
                return answerQ5(q5Matcher);
            }
            Matcher q6Matcher = q6.matcher(parameter);
            if (q6Matcher.matches()) {
                return answerQ6(q6Matcher);
            }
            Matcher q8Matcher = q8.matcher(parameter);
            if (q8Matcher.matches()) {
                return answerQ8(q8Matcher);
            }



        } catch (Exception e) {
            e.printStackTrace();
            return "team name";
        }

        return "team name";
    }

}
