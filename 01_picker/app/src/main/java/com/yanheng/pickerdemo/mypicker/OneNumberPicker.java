package com.yanheng.pickerdemo.mypicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yanheng.pickerdemo.R;

public class OneNumberPicker {
    private String title;
    private String pickerUnit;
    private String [] pickerContent;
    private int defaultValue;
    private final Context context;
    private Callback callback;

    public  OneNumberPicker (Context context){
        this.context = context;
    }

    /**
     * @param unit      単位
     * @param content　表示内容
     * @param value　　初期表示内容
     */
    public void setNumberPicker(String unit , String [] content , int value){
        this.pickerContent = content;
        this.pickerUnit = unit;
        defaultValue = value;
    }

    /**
     * タイトル設定
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * ボタンタッチのコールバック
     * @param callback
     */
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void show(){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout rootView = (LinearLayout)layoutInflater.inflate(R.layout.one_number_picker,null);
        TextView textView = rootView.findViewById(R.id.textview1);
        final NumberPicker numberPicker = rootView.findViewById(R.id.numberpicker1);
        textView.setText(pickerUnit);
        numberPicker.setDisplayedValues(pickerContent);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(pickerContent.length-1);
        numberPicker.setValue(defaultValue);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onOK(pickerContent[numberPicker.getValue()]);
            }
        })      ;
        builder.setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onCancel();
            }
        });
        builder.setView(rootView);
        builder.create().show();
    }
    public interface Callback {
        void onOK(String selectValue);
        void onCancel();
    }
}
