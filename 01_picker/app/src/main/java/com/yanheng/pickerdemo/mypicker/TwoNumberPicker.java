package com.yanheng.pickerdemo.mypicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.yanheng.pickerdemo.R;

public class TwoNumberPicker {
    private String title;
    private String pickerUnit1;
    private String [] pickerContent1;
    private int defaultValue1;
    private String pickerUnit2;
    private String [] pickerContent2;
    private int defaultValue2;
    private final Context context;
    private Callback callback;

    public TwoNumberPicker(Context context){
        this.context = context;
    }

    /**
     * 左側NumberPickerの設定
     * @param unit      単位
     * @param content　表示内容
     * @param value　　初期表示内容
     */
    public void setNumberPickerLeft(String unit , String [] content , int value){
        this.pickerContent1 = content;
        this.pickerUnit1 = unit;
        defaultValue1 = value;
    }
    /**
     * 右側NumberPickerの設定
     * @param unit      単位
     * @param content　表示内容
     * @param value　　初期表示内容
     */
    public void setNumberPickerRight(String unit , String [] content , int value){
        this.pickerContent2 = content;
        this.pickerUnit2 = unit;
        defaultValue2 = value;
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
        LinearLayout rootView = (LinearLayout)layoutInflater.inflate(R.layout.two_number_picker,null);
        //左側
        TextView textViewLeft = rootView.findViewById(R.id.textview1);
        final NumberPicker numberPickerLeft = rootView.findViewById(R.id.numberpicker1);
        textViewLeft.setText(pickerUnit1);
        numberPickerLeft.setDisplayedValues(pickerContent1);
        numberPickerLeft.setMinValue(0);
        numberPickerLeft.setMaxValue(pickerContent1.length-1);
        numberPickerLeft.setValue(defaultValue1);
        //右側
        TextView textViewRight = rootView.findViewById(R.id.textview2);
        final NumberPicker numberPickerRgiht = rootView.findViewById(R.id.numberpicker2);
        textViewRight.setText(pickerUnit2);
        numberPickerRgiht.setDisplayedValues(pickerContent2);
        numberPickerRgiht.setMinValue(0);
        numberPickerRgiht.setMaxValue(pickerContent2.length-1);
        numberPickerRgiht.setValue(defaultValue2);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onOK(pickerContent1[numberPickerLeft.getValue()],pickerContent2[numberPickerRgiht.getValue()]);
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
        void onOK(String selectValueLeft , String selectValueRight);
        void onCancel();
    }
}
