package com.yanheng.recyclerview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yanheng.recyclerview.L;
import com.yanheng.recyclerview.R;

import java.util.ArrayList;
import java.util.List;


public class ActivityDemo extends AppCompatActivity {
    private RecyclerView mRv;
    private QuickAdapter<Integer> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d();

        setContentView(R.layout.activity_6);

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new QuickAdapter<Integer>(initData()) {
            @Override
            public int getLayoutId(int viewType) {
                L.d();
                return R.layout.item_6;
            }

            @Override
            public void convert(VH holder, Integer data, int position) {
                L.d();
                ImageView imageView = holder.getView(R.id.image);
//                imageView.setImageResource(data);
                Picasso.with(ActivityDemo.this).load(data).into(imageView);
                //holder.itemView.setOnClickListener();
                L.d();
            }

            @Override
            public int getItemViewType(int position) {
                L.d();
                return super.getItemViewType(position);
            }
        };
        L.d();
        mAdapter.setHasStableIds(true);
        L.d();
        ((SimpleItemAnimator)mRv.getItemAnimator()).setSupportsChangeAnimations(false);
        L.d();
        mRv.setAdapter(mAdapter);

        L.d();
    }

    public List<Integer> initData(){
        L.d();
        Integer[] images = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                        R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10
                };
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<2;i++){
            for(Integer image:images){
                list.add(image);
            }
        }
        L.d();
        return list;
    }





}
