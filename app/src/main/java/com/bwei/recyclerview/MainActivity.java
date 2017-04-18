package com.bwei.recyclerview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Info{
    private RecyclerView recyclerView;
    private List<Bean.ResultBean.DataBean> data;
    private String uri="http://v.juhe.cn/toutiao/index?type=top&key=55657a83e68652aa6db8e5117482ee91";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter = new RecyclerViewAdapter(MainActivity.this, data);
            adapter.RecyclerViewAdapter(MainActivity.this);
            recyclerView.setAdapter(adapter);
        }
    };
    private RecyclerViewAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intdata();
        setDate();
    }
    private  void  intdata(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button xianx= (Button) findViewById(R.id.line);
        Button pubo= (Button) findViewById(R.id.pubu);
        Button gridview= (Button) findViewById(R.id.gridview);
        xianx.setOnClickListener(this);
        pubo.setOnClickListener(this);
        gridview.setOnClickListener(this);
    }
     private void setDate(){
         //调用封装的方法
        OkHttpUtils okHttpUtils=OkHttpUtils.getHttpUtils();
        okHttpUtils.loadDataFromNet(uri,Bean.class, new OkHttpUtils.CallBackListener<Bean>() {
            //成功返回的方法
            public void onSuccess(Bean result) {
                data = result.getResult().getData();
               handler.sendEmptyMessage(2);
            }
            public void onFail() {
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                //设置分隔线
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
               // adapter.notifyDataSetChanged();
                break;
            case R.id.pubu:
                StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager);
                //设置分割线
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                //adapter.notifyDataSetChanged();
                break;
            case R.id.gridview:
                GridLayoutManager manager1=new GridLayoutManager(this,3);
                recyclerView.setLayoutManager(manager1);
                //多条目加载
                manager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position%10==0){
                            return 3;
                        }else if (position%5==0){
                            return 2;
                        }
                        return 1;
                    }
                });
                //设置分隔线
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
        }
    }

    @Override
    public void num(int position) {
        Toast.makeText(MainActivity.this,"id:"+position,Toast.LENGTH_SHORT).show();
    }
}
