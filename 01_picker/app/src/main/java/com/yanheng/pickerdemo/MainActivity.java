package com.yanheng.pickerdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yanheng.pickerdemo.mypicker.OneNumberPicker;
import com.yanheng.pickerdemo.mypicker.TwoNumberPicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TooManyListenersException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * DatePickerDialog
     * @param v
     */
    public void pickerDemo1 (View v){
        // 日付情報の初期設定
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // 最大値設定
        Calendar maxDate = Calendar.getInstance();
        maxDate.set(year, monthOfYear, dayOfMonth);
        maxDate.add(Calendar.DATE, +20);
        // 最小値設定
        GregorianCalendar minDate = new GregorianCalendar();
        minDate.set(year, monthOfYear, dayOfMonth);
        // 日付設定ダイアログの作成
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                DateSetListener_expected_date, year, monthOfYear,
                dayOfMonth);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        if (datePicker != null) {
            datePicker.setMaxDate(maxDate.getTimeInMillis());
            datePicker.setMinDate(calendar.getTimeInMillis());
        }
        // 日付設定ダイアログの表示
        datePickerDialog.show();

    }
    private DatePickerDialog.OnDateSetListener DateSetListener_expected_date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            L.d("year="+year+"  month="+month+"  day="+dayOfMonth);
        }
    };

    /**
     * TimePickerDialog demo
     * @param v
     */
    public void pickerDemo2 (View v) {
        int hour = 9;
        int minute = 00;
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minuteOfDay) {
                        // 時間表示をHH:mmに変換
                        String time = String.format("%02d:%02d", hourOfDay,
                                minuteOfDay);
                        L.d("time="+time);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    /**
     * AlertDialog.Builder+DatePicker独自タイトル
     * @param v
     */
    public void pickerDemo3 (View v) {
        int hour=0;
        int minute=0;
        final DatePicker dp = new DatePicker(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("独自タイトル");
        builder.setView(dp);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }
    /**
     * AlertDialog.Builder+TimePicker 独自タイトル
     * @param v
     */
    public void pickerDemo4 (View v) {
        int hour=0;
        int minute=0;
        final TimePicker timePicker = new TimePicker(this);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("title");
        builder.setView(timePicker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                L.d(""+hour+":"+minute);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    private String[] mCities  = {"東京","大阪","名古屋","札幌","千葉","埼玉"};
    public void pickerDemo5(View v){
//        NumberPickerDemo();
//        oneNumberPickerDemo();
//        twoNumberPickerDemo();
    }

    public void twoNumberPickerDemo(View v) {
        String title = "身長";
        final String unit1 = ".";
        String [] contents1 = {"170","171","172","173","174","175","176","177"};
        int defaultValue1 = 3;
        final String unit2 = "cm";
        String [] contents2 = {"0","1","2","3","4","5","6","7","8","9"};
        int defaultValue2 = 0;
        TwoNumberPicker twoNumberPicker = new TwoNumberPicker(this);
        twoNumberPicker.setTitle(title);
        twoNumberPicker.setNumberPickerLeft(unit1,contents1,defaultValue1);
        twoNumberPicker.setNumberPickerRight(unit2,contents2,defaultValue2);
        twoNumberPicker.setCallback(new TwoNumberPicker.Callback() {
            @Override
            public void onOK(String selectValueLeft, String selectValueRight) {
                String showText = selectValueLeft + unit1 + selectValueRight + unit2;
                Toast.makeText(getApplicationContext(),showText,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();
            }
        });
        twoNumberPicker.show();
    }


    public void NumberPickerDemo(View v) {
        NumberPicker mNumberPicker = new NumberPicker(this);
        mNumberPicker.setDisplayedValues(mCities);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(mCities.length - 1);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("日本の都道府県");
        builder.setView(mNumberPicker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }

    public void oneNumberPickerDemo(View v){
        String[] contentValues  = {"1","2","3","4","5","6","7","8","9","10"};
        OneNumberPicker oneNumberPicker = new OneNumberPicker(this);
        oneNumberPicker.setTitle("注文数");
        oneNumberPicker.setNumberPicker("個",contentValues,1);
        oneNumberPicker.setCallback(new OneNumberPicker.Callback() {
            @Override
            public void onOK(String selectValue) {
                String s =selectValue+"個、注文した";
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                L.d();
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();
            }
        });
        oneNumberPicker.show();
    }


    public void pickerDemo6 (View v){
        DrawViewDemo drawViewDemo = findViewById(R.id.draw_view);
        drawViewDemo.setVisibility(View.VISIBLE);
    }
}
