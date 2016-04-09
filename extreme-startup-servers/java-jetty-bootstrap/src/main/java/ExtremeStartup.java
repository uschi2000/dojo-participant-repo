import com.google.common.base.Splitter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    String q2(String query) {
        Matcher m = q2Pattern.matcher(query);
        String list = m.group(1);
        Iterable<Integer> nums = Lib.toInt(COMMA_SPLITTER.split(list));
        int max = Lib.max(nums);
        return Integer.toString(max);
    }

    String answer(String parameter) {
        if (parameter == null)
            return "team name";

        Matcher q1Matcher = q1.matcher(parameter);
        if (q1Matcher.matches()) {
            return answerQ1(q1Matcher);
        }
        Matcher q2Matcher = q2Pattern.matcher(parameter);
        if (q2Matcher.matches()) {
            return q2(parameter);
        }

        return "team name";
    }

}
