package com.yanheng.recyclerview.demo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Quick2Adapter<T> extends RecyclerView.Adapter<Quick2Adapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = ViewHolder.get(viewGroup,getLayoutId(i));
        return viewHolder;
    }

    public abstract int getLayoutId (int viewType);

    static class ViewHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mConvertView = itemView;
            mViews = new SparseArray<>();
        }
        public static ViewHolder get(ViewGroup parent , int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
            return new ViewHolder(convertView);
        }
        public <T extends View> T getView (int id ){
            View v = mViews.get(id);
            if(v==null){
                v = mConvertView.findViewById(id);
                mViews.put(id,v);
            }
            return (T)v;
        }
    }

}
