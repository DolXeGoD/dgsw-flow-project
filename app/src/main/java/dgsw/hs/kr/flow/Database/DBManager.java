package dgsw.hs.kr.flow.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 조현재 on 2018-05-08.
 */

public class DBManager extends SQLiteOpenHelper{

    public int testIdx;
    public String testToken;

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //새로운 테이블 생성
        db.execSQL("CREATE TABLE t_token (idx INTEGER PRIMARY KEY AUTOINCREMENT, token TEXT)");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String token){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO t_token(token) VALUES('" + token +"')");
    }

    public String select(){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM t_token";
        Cursor cursor = null;


        if(cursor != null){
            cursor.moveToFirst();
        }

        cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()){
            testIdx = cursor.getInt(0);
            testToken = cursor.getString(1);
            Log.i("this is token log", testIdx + " | " + testToken);
        }

        return testToken;
    }
}
