package com.android.systemui.utils;

import java.util.Calendar;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.content.res.Resources;
import android.content.Context;
import com.android.systemui.R;

public class ContextualHeaderProvider {

    // Daily calendar periods
    private static final int TIME_SUNRISE = 6;
    private static final int DRAWABLE_SUNRISE = R.drawable.notifhead_sunrise;
    private static final int TIME_MORNING = 9;
    private static final int DRAWABLE_MORNING = R.drawable.notifhead_morning;
    private static final int TIME_NOON = 11;
    private static final int DRAWABLE_NOON = R.drawable.notifhead_noon;
    private static final int TIME_AFTERNOON = 13;
    private static final int DRAWABLE_AFTERNOON = R.drawable.notifhead_afternoon;
    private static final int TIME_SUNSET = 19;
    private static final int DRAWABLE_SUNSET = R.drawable.notifhead_sunset;
    private static final int TIME_NIGHT = 22;
    private static final int DRAWABLE_NIGHT = R.drawable.notifhead_night;

    private static final int DRAWABLE_DEFAULT = R.drawable.notification_header_bg;

    private static SparseArray<Drawable> mCache = new SparseArray<Drawable>();
    private static Context mContext;

    public static Drawable getHeader(Context ctx) {
        mContext = ctx;
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (hour < TIME_SUNRISE || hour >= TIME_NIGHT) {
            return loadOrFetch(DRAWABLE_NIGHT);
        } else if (hour >= TIME_SUNRISE && hour < TIME_MORNING) {
            return loadOrFetch(DRAWABLE_SUNRISE);
        } else if (hour >= TIME_MORNING && hour < TIME_NOON) {
            return loadOrFetch(DRAWABLE_MORNING);
        } else if (hour >= TIME_NOON && hour < TIME_AFTERNOON) {
            return loadOrFetch(DRAWABLE_NOON);
        } else if (hour >= TIME_AFTERNOON && hour < TIME_SUNSET) {
            return loadOrFetch(DRAWABLE_AFTERNOON);
        } else if (hour >= TIME_SUNSET && hour < TIME_NIGHT) {
            return loadOrFetch(DRAWABLE_SUNSET);
        }
	return loadOrFetch(DRAWABLE_DEFAULT);
    }

    private static Drawable loadOrFetch(int resId) {
        Drawable res = mCache.get(resId);

        if (res == null) {
            // We don't have this drawable cached, do it!
            final Resources r = mContext.getResources();
            res = r.getDrawable(resId);
            mCache.put(resId, res);
        }

        return res;
    }
}
