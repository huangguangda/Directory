package cn.edu.gdmec.android.directory;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyHelper myHelper;
    private EditText mEtName;
    private EditText mEtPhone;
    private TextView mTvShow;
    private Button mBtnAdd;
    private Button mBtnDelete;
    private Button mBtnUpdate;
    private Button mBtnQuery;
    private String name;
    private String phone;

    private SQLiteDatabase db;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        myHelper=new MyHelper ( this );

        init();//初始化控件
    }

    private void init() {
        mEtName = (EditText) findViewById ( R.id.et_name );
        mEtPhone = (EditText) findViewById ( R.id.et_phone );
        mTvShow = (TextView) findViewById ( R.id.tv_show );

        mBtnAdd = (Button) findViewById ( R.id.btn_add );
        mBtnDelete = (Button) findViewById ( R.id.btn_delete );
        mBtnUpdate = (Button) findViewById ( R.id.btn_update );
        mBtnQuery = (Button) findViewById ( R.id.btn_query );

        mBtnAdd.setOnClickListener ( this );
        mBtnDelete.setOnClickListener ( this );
        mBtnUpdate.setOnClickListener ( this );
        mBtnQuery.setOnClickListener ( this );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()){

            case R.id.btn_add://添加数据
                name = mEtName.getText ().toString ();
                phone=mEtPhone.getText ().toString ();
                db = myHelper.getReadableDatabase ();//获取可读数据库对象

                values = new ContentValues ();
                values.put ( "name",name );
                values.put ( "phone",phone );
                db.insert ( "information",null,values );
                db.close ();

                break;
            case R.id.btn_query://查询数据
                db=myHelper.getReadableDatabase ();
                Cursor cursor = db.query ( "information",null,null,null,null,null,null );
                if (cursor.getCount() == 0){
                    mTvShow.setText ( "" );
                    Toast.makeText ( this,"没有数据",Toast.LENGTH_LONG ).show ();

                }else {
                    cursor.moveToFirst ();//移动到第一行
                    mTvShow.setText ( "Name:"+cursor.getString(1)+"Tel:"+cursor.getString(2) );

                }
                while (cursor.moveToNext()){
                    mTvShow.append ( "\n"+"Name:"+cursor.getString(1)+"Tel:"+cursor.getString(2) );
                }
                cursor.close();
                db.close ();

                break;
            case R.id.btn_update://更新数据
                db= myHelper.getReadableDatabase ();

                values = new ContentValues (  );

                values.put ( "phone",phone=mEtPhone.getText ().toString () );

                db.update ( "information",values,"name=?",new String[]{mEtName.getText ().toString ()} );
                db.close ();
                break;
            case R.id.btn_delete://删除数据

                db=myHelper.getWritableDatabase ();
                db.delete ( "information",null,null );
                Toast.makeText ( this,"信息已经删除", Toast.LENGTH_LONG ).show ();
                mTvShow.setText ( "" );
                db.close ();
                break;

        }
    }
}
