package com.example.hfs.jpushexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by HFS on 2016/7/30.
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String message=bundle.getString(JPushInterface.EXTRA_MESSAGE);
            System.out.println("收到了自定义消息。消息内容是：" + message);
            String extra=bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println("收到了自定义消息。extra是：" + extra);


            /**
             * 通过输出日志我们知道extra是JSON格式的字符串
             * 解析Json
             */
            Map<String, Object> map = new HashMap<String, Object>();
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(extra);
                String key = jsonObject.getString("key");
                map.put("key", key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("message", message);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(calendar.getTime());
            map.put("date", date);
            Data.data.add(map);

            Toast.makeText(context,"Data.data:"+Data.data.size(),Toast.LENGTH_SHORT).show();

            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
