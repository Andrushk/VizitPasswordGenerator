package com.example.puchkovav.vizitpasswordgenerator.PasswordGenerator;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PuchkovAV on 13.03.2017.
 *
 * Генератор кода строго из четырех знаков, причем все знаки - цифры и пока все варианты не будут
 * перебраны коды не должны повторяться.
 */

public class FourDigitGenerator extends FixedDigitGenerator {
    final String TAG = "FourDigitGenerator";
    final String KEY_IDX = "FourDigitGeneratorIdx";
    final String KEY_DATA = "FourDigitGeneratorData";
    final int DEFAULT_IDX = -1;

    @Override
    public void restoreState(SharedPreferences preferences) {
        try {
            idx = preferences.getInt(KEY_IDX, DEFAULT_IDX);
            String joined = preferences.getString(KEY_DATA, null);
            if (idx == DEFAULT_IDX || joined == null || joined.isEmpty()) {
                reset();
            } else {
                codes = new ArrayList<>(Arrays.asList(TextUtils.split(joined, ",")));
            }
        } catch (Exception e) {
            reset();
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void saveState(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_IDX, idx);
        editor.putString(KEY_DATA, TextUtils.join(",", codes));
        editor.commit();
    }

    @Override
    public int digits() {
        return 4;
    }
}
