package co.bluepx.edu.auth;

/**
 * Created by bison on 1/10/16.
 *
 */
import com.google.common.base.Preconditions;

public final class StringConditions {
    private StringConditions() { }

    public static String checkNotBlank(String string) {
        Preconditions.checkArgument(string != null && string.trim().length() > 0);
        return string;
    }
}
