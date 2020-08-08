package com.example.layoutinflaterinflateparamstudy.usecase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layoutinflaterinflateparamstudy.R;

import java.util.List;

/**
 * @author wangzhichao
 * @date 7/20/20
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private List<String> list;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context, List<String> list) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: parent=" + parent);
        // 打印信息: onCreateViewHolder: parent=androidx.recyclerview.widget.RecyclerView{2e97f7b VFED..... .F....ID 0,0-1440,2048 #7f070081 app:id/recycler_view}
        View view = layoutInflater.inflate(R.layout.recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }

        public void bindItem(String data) {
            tv.setText(data);
        }
    }
}
