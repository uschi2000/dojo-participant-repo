import java.util.regex.Pattern;

/**
 * Created by jellis on 4/8/16.
 */
public class BasicMatcher {

    private final Pattern pattern;
    private final String response;

    public BasicMatcher(String pattern, String response) {
        this.pattern = Pattern.compile(".*" + pattern);
        this.response = response;
    }

    public boolean matches(String parameter) {
        return pattern.matcher(parameter).matches();
    }

    public String getResponse() {
        return response;
    }

}
