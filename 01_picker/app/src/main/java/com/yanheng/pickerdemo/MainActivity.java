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
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
//                int hour = tp.getCurrentHour();
//                int minute = tp.getCurrentMinute();
//                L.d(""+hour+":"+minute);
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

}
