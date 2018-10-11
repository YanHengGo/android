package com.yanheng.myapplication;

import java.util.regex.Pattern;
import android.util.Log;
public class L {
	
	/**
     * デバック用ログを出力するフラグ
     */
	private static Boolean DEBUG_FLAG = true;

    public static void d() {
        if(DEBUG_FLAG == true){
            Log.d(getTag(), "gentest ::");
        }
    }
    /**
     * デバック用ログを出力する。 本番リリース時は出力されない。
     *
     * @param msg 出力するメッセージ
     */
    public static void d(String msg) {
    	if(DEBUG_FLAG == true){
    		Log.d(getTag(), "gentest ::"+msg);
    	}
    }
    public static void dstart(String msg) {
    	if(DEBUG_FLAG == true){
    		Log.d(getTag(), "gentest ::"+msg+" :start");
    	}
    }
    public static void dend(String msg) {
    	if(DEBUG_FLAG == true){
    		Log.d(getTag(), "gentest ::"+msg+" :end");
    	}
    }
    public static void e(String msg) {
    	if(DEBUG_FLAG == true){
    		Log.e(getTag(), msg);
    	}
    }
    /**
     * 同上
     *
     * @param msg
     * @param t　Throwable
     */
    public static void e(String msg, Throwable t) {
    	if(DEBUG_FLAG == true){
    		Log.e(getTag(), msg, t);
    	}
    }
    /**
     * タグを生成する
     *
     * @return className#methodName:line
     */
    private static String getTag() {
        final StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
        final String cla = trace.getClassName();
        Pattern pattern = Pattern.compile("[\\.]+");
        final String[] splitedStr = pattern.split(cla);
        final String simpleClass = splitedStr[splitedStr.length - 1];
        final String mthd = trace.getMethodName();
        final int line = trace.getLineNumber();
        final String tag = simpleClass + "#" + mthd + ":" + line;
        return tag;
    }
}