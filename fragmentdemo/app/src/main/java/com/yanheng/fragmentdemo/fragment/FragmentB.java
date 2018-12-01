package com.yanheng.fragmentdemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanheng.fragmentdemo.L;
import com.yanheng.fragmentdemo.R;

public class FragmentB extends Fragment {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        L.d();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d();
        return inflater.inflate(R.layout.fragment_b,container,false);
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
