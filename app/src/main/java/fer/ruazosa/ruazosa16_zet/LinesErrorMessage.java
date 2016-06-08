package fer.ruazosa.ruazosa16_zet;

import android.content.Context;

public class LinesErrorMessage {

    public static String get(Throwable e, boolean pullToRefresh, Context c) {
        if (pullToRefresh) {
            return c.getString(R.string.error_lines);
        } else {
            return c.getString(R.string.error_lines_retry);
        }
    }

}
