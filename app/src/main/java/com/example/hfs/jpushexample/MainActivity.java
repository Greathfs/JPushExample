package com.example.hfs.jpushexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hfs.jpushexample.adapter.MyAdapter;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private BadgeView mBadge;
    private LinearLayout mLinearLayout;
    private ListView mListView;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

//        JPushInterface.setAlias(this, "test123456", new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                Log.d("alias", "set alias result is" + i);
//            }
//        });
//
//        Set<String> sets = new HashSet<>();
////        sets.add("sport");
//        sets.add("game");
//        sets.add("music");
//
//        JPushInterface.setTags(this, sets, new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                Log.d("alias", "set tag result is" + i);
//            }
//        });

        mImageView= (ImageView) this.findViewById(R.id.image_message);
        mListView= (ListView) this.findViewById(R.id.list);
        mAdapter=new MyAdapter(this,Data.data);
        mListView.setAdapter(mAdapter);
        mLinearLayout= (LinearLayout) this.findViewById(R.id.layout_message);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListView.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Data.data:"+Data.data.size(),Toast.LENGTH_SHORT).show();
            }
        });
        mBadge = new BadgeView(MainActivity.this, mImageView);
        if (Data.data.size()>0){
            mBadge.setText(String.valueOf(Data.data.size()));
            mBadge.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
