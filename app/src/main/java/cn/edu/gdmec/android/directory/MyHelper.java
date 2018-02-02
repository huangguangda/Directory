package cn.edu.gdmec.android.directory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jack on 2018/2/2.
 */

public class MyHelper extends SQLiteOpenHelper{

    public MyHelper(Context context) {
        super(context,"itcast.db",null,1);
    }

    //当数据库第一次创建的时候执行
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), phone VARCHAR(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oleVersion, int newVersion) {

    }
}
