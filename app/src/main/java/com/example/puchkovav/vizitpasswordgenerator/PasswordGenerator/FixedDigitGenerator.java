package com.example.puchkovav.vizitpasswordgenerator.PasswordGenerator;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by PuchkovAV on 18.04.2017.
 *
 * Генератор кода строго из заданного заранее кол-ва знаков,
 * причем все знаки - цифры и пока все варианты не будут перебраны коды не должны повторяться.
 */

public abstract class FixedDigitGenerator implements Generator {
    final String EOF = "eof";
    final int DEFAULT_IDX = -1;

    protected ArrayList<String> codes;
    protected int idx = DEFAULT_IDX;

    private final int max;
    private final String mask;

    public FixedDigitGenerator() {
        max = (int) Math.pow(10, digits());
        mask = "%0" + Integer.toString(digits()) + "d";
    }

    @Override
    public String next() {
        idx++;
        return current();
    }

    @Override
    public String current() {
        checkCodes();
        if (idx >= codes.size()) return EOF;
        return codes.get(idx);
    }

    @Override
    public boolean eof() {
        return current() == EOF;
    }

    @Override
    public void reset() {
        codes = new ArrayList<>();
        idx = 0;
        for (int i = 0; i < max; i++) {
            codes.add(String.format(mask, i));
        }
        Collections.shuffle(codes);
    }

    @Override
    public int count() {
        return idx;
    }

    @Override
    public abstract void restoreState(SharedPreferences preferences);

    @Override
    public abstract void saveState(SharedPreferences preferences);

    public abstract int digits();

    private void checkCodes() {
        if (idx <= DEFAULT_IDX || codes == null) {
            reset();
        }
    }
}
