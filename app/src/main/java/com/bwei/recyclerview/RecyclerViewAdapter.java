package com.bwei.recyclerview;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/*
* 作者：梁伟航 on 2017/3/13 20:02
* 类的用途：
*/
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHOlder>{
    private Info info;
    private LayoutInflater inflater;
    private List<Bean.ResultBean.DataBean> data;
    private Context en;
    public void RecyclerViewAdapter(Info info) {
        this.info = info;
    }
    public RecyclerViewAdapter(Context context, List<Bean.ResultBean.DataBean> data) {
        en=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }
    public MyHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item,parent,false);
        MyHOlder myHOlder = new MyHOlder(view);
        myHOlder.itemView=view;
        return myHOlder;
    }
    public void onBindViewHolder(MyHOlder holder, final int position) {
        holder.name.setText(data.get(position).getAuthor_name());
        Glide.with(en).load(data.get(position).getThumbnail_pic_s()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                info.num(position);
            }
        });
    }
    public int getItemCount() {
        return data.size();
    }



    class MyHOlder extends RecyclerView.ViewHolder{
        private  ImageView image;
        private  TextView name;
        private View itemView;
        public MyHOlder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
