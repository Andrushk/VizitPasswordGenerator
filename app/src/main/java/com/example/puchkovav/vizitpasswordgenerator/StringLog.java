package com.example.puchkovav.vizitpasswordgenerator;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PuchkovAV on 20.03.2017.
 */

public class StringLog {
    final String TAG = "StringLog";
    final String KEY_LOG_LINES = "StringLogLines";

    private ArrayList<String> lines;

    public void restoreState(SharedPreferences preferences) {
        try {
            String joined = preferences.getString(KEY_LOG_LINES, null);
            if (joined != null) {
                lines = new ArrayList<>(Arrays.asList(TextUtils.split(joined, ",")));
            }
        } catch (Exception e) {
            lines = null;
            Log.e(TAG, e.getMessage());
        }
    }

    public void saveState(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LOG_LINES, TextUtils.join(",", getLines()));
        editor.commit();
    }

    public void clear() {
        if (lines != null) {
            lines.clear();
        }
    }

    public ArrayList<String> getItems() {
        return getLines();
    }

    private ArrayList<String> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }
}
