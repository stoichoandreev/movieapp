package smovie.movieapp.utils;

import android.support.annotation.Nullable;

/**
 * Created by sniper on 12/07/16.
 */
public class RegularUtils {

    @Nullable
    public static <T> T parseAdditionalParams(Object[] arr, int pos, Class<T> clazz) {
        try {
            return (arr != null && arr.length > 0) ? clazz.cast(arr[pos]) : null;
        } catch(ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
