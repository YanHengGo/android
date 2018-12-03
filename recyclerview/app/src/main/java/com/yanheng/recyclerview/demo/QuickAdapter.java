package com.yanheng.recyclerview.demo;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanheng.recyclerview.L;

import java.util.List;

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH>{

    private List<T> mDatas;

    public QuickAdapter(List<T> datas){
        L.d();
        this.mDatas = datas;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        L.d();
        VH vh = VH.get(parent,getLayoutId(viewType));
        L.d();
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        L.d();
        convert(holder, mDatas.get(position), position);
        L.d();
    }

    @Override
    public int getItemCount() {
        L.d();
        return mDatas.size();
    }

    public abstract void convert(VH holder, T data, int position);

    static class VH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v){
            super(v);
            L.d();
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId){
            L.d();
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            L.d();
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            L.d();
            return (T)v;
        }

        public void setText(int id, String value){
            L.d();
            TextView view = getView(id);
            view.setText(value);
            L.d();
        }
    }
}
