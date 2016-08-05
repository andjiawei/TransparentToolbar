package com.netsite.titlefading;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.netsite.titlefading.utils.Utils;
import com.netsite.titlefading.widget.MyListView;
import com.netsite.titlefading.widget.TransparentToolBar;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements TransparentToolBar.OnScrollStateListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TransparentToolBar mTransparentToolBar;
    private MyListView mMyListView;
    private int resId=R.drawable.london;//listView顶部图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();
        initData();
    }

    private void initListener() {
        mTransparentToolBar.setOnScrollStateListener(this);
        mTransparentToolBar.setOffset(Utils.getHeaderHeight(this,resId)-mTransparentToolBar.getHeight());//必须设置ToolBar最大偏移量，这会影响到0~1透明度变化的范围
    }

    private List<String> itemList;
    private void initData() {
        itemList=new ArrayList<>();
        for (int i=0;i<50;i++){
            itemList.add("itemList"+i);
        }
        mMyListView.setAdapter(new MyAdapter());
        //必须设置ToolBar颜色值，也就是0~1透明度变化的颜色值
        mTransparentToolBar.setBgColor(getResources().getColor(R.color.colorAccent));

        mMyListView.setTitleBar(mTransparentToolBar);
    }

    private void initView() {
        mTransparentToolBar = (TransparentToolBar) findViewById(R.id.main_bar);
        mMyListView = (MyListView) findViewById(R.id.main_sv_root);
        mMyListView.setHeader(resId);
        mTransparentToolBar.post(new Runnable() {

            @Override
            public void run() {
                initListener();
            }
        });
    }

    @Override
    public void updateFraction(float fraction) {
        //ToolBar滚动回调的百分比0~1
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                convertView=View.inflate(ListViewActivity.this,R.layout.item,null);
                viewHolder.textView= (TextView) convertView.findViewById(R.id.textView);
                viewHolder.textView.setPadding(0,30,0,30);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(itemList.get(position));
            return convertView;
        }
        class ViewHolder{
            public TextView textView;
        }
    }
}
