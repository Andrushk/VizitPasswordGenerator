package com.example.puchkovav.vizitpasswordgenerator;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.puchkovav.vizitpasswordgenerator.PasswordGenerator.FourDigitGenerator;
import com.example.puchkovav.vizitpasswordgenerator.PasswordGenerator.Generator;
import com.example.puchkovav.vizitpasswordgenerator.PasswordGenerator.OneDigitGenerator;

public class MainActivity extends AppCompatActivity {

    private TextView currentCodeView;
    private TextView counterValueView;
    private ListView logListView;

    private Generator generator = new FourDigitGenerator();
    private StringLog stringLog = new StringLog();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentCodeView = (TextView) findViewById(R.id.currentCodeView);
        counterValueView = (TextView) findViewById(R.id.counterValueView);
        logListView = (ListView) findViewById(R.id.logListView);

        restoreGenerator();
        restoreLog();
    }

    public void onClickNextCodeButton(View view) {
        if (generator.eof()) return;
        //старый код кладем в лог
        arrayAdapter.insert(generator.current(), 0);
        generator.next();
        showCode();
    }

    public void onClickResetButton(View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(com.example.puchkovav.vizitpasswordgenerator.R.string.confirmText)
                .setMessage(R.string.resetButtonConfirmationText)
                .setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetGenerator();
                        stringLog.clear();
                    }

                })
                .setNegativeButton(R.string.textNo, null)
                .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        generator.saveState(getPreferences());
        stringLog.saveState(getPreferences());
    }

    private SharedPreferences getPreferences(){
        return getPreferences(MODE_PRIVATE);
    }

    private void restoreGenerator(){
        generator.restoreState(getPreferences());
        showCode();
    }

    private void resetGenerator(){
        generator.reset();
        showCode();
    }

    private void restoreLog(){
        stringLog.restoreState(getPreferences());
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringLog.getItems());
        logListView.setAdapter(arrayAdapter);
    }

    private void showCode(){
        currentCodeView.setText(generator.current());
        counterValueView.setText(String.valueOf(generator.count()));
    }
}
