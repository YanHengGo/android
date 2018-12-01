package com.yanheng.fragmentdemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanheng.fragmentdemo.L;
import com.yanheng.fragmentdemo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FragmentA extends Fragment {
    private TextView textView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d();
        String s = "1";
        int a = Integer.getInteger(s);
        String b = String.valueOf(a);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d();
        return inflater.inflate(R.layout.fragment_a,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.textView = (TextView)view.findViewById(R.id.fragment_textview);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.d();
    }

    @Override
    public void onStart() {
        super.onStart();
        L.d();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.d();
    }

    @Override
    public void onPause() {
        super.onPause();
        L.d();
    }

    @Override
    public void onStop() {
        super.onStop();
        L.d();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d();
    }
}
