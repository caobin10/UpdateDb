package com.demo.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
/**
 * Created by Administrator on 2018/6/4.
 */

public class MainActivity extends AppCompatActivity {
    private String DATABASES_DIR = "/storage/emulated/0/com.demo.test/database/";
    private String DATABASE_NAME = "test.db";
    private TextView textView;
    private File mypath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        copyDatabaseFile(this, true);
        //模糊查询数据
        queryData();
    }

    private void copyDatabaseFile(Context context, boolean isfored) {
        File dir = new File(DATABASES_DIR);
        if (!dir.exists() || isfored) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mypath = new File(dir, DATABASE_NAME);
        if (mypath.exists() && !isfored) {
            return;
        }
        try {
            if (mypath.exists()) {
                mypath.delete();
            }
            mypath.createNewFile();
            InputStream in = context.getAssets().open(DATABASE_NAME);
            int size = in.available();
            byte buf[] = new byte[size];
            in.read(buf);
            in.close();
            FileOutputStream out = new FileOutputStream(mypath);
            out.write(buf);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //模糊查询数据
    private void queryData() {
        SQLiteDatabase dBase = SQLiteDatabase.openOrCreateDatabase(mypath, null);
        try {
            Cursor cursor = dBase.rawQuery("Select * from testbiao where id= ?",
                    new String[]{"1"});
            String name = null;
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex("name"));
            }
            textView.setText("name:" + name);
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
