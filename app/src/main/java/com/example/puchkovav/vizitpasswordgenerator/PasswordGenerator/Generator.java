package com.example.puchkovav.vizitpasswordgenerator.PasswordGenerator;

import android.content.SharedPreferences;

/**
 * Created by PuchkovAV on 13.03.2017.
 *
 * Генератор кодов
 */

public interface Generator {

    // очередной код
    String next();

    // текущий код
    String current();

    // true, если все коды перебраны
    boolean eof();

    // перезапустить генератор
    void reset();

    // кол-во уже сгенерированных кодов
    int count();

    // восстановить настройки, прочитав их из SharedPreferences
    void restoreState(SharedPreferences preferences);

    // сохранить настройки в SharedPreferences
    void saveState(SharedPreferences preferences);
}
