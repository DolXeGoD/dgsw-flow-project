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
    public int count;
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

    public int insert(String token){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM t_token";
        int checkCtn = 0;

        Cursor cursor = null;
        if(cursor != null){
            cursor.moveToFirst();
        }

        cursor = db.rawQuery(query, null);
        count = cursor.getCount();
        loop: for(int i=0;i<count;i++){
            cursor.moveToNext();
            String reduCheck = cursor.getString(1);
            //같은 토큰 값이 존재하면.
            if(token.equals(reduCheck) == true){
                Log.i("err", "같은 토큰 값이 이미 DB에 존재합니다.");
                break loop; // for문 탈출 후 함수 종료
            }
            checkCtn++; //중복된 값이 없으묜 checkCtn를 계속 증가.
        }
        //중복된 토큰 값 없이 모두 통과하면 insert 실행.
        if(checkCtn == count){
            db.execSQL("INSERT INTO t_token(token) VALUES('" + token +"') ");
        }
        return 0;
    }

    public void delete(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM t_token WHERE idx > 1");
    }

    public String select(){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM t_token";
        Cursor cursor = null;


        if(cursor != null){
            cursor.moveToFirst();
        }

        cursor = db.rawQuery(query, null);
        count = cursor.getCount();

        for(int i = 0; i<count; i++){
            cursor.moveToNext();
            testIdx = cursor.getInt(0);
            testToken = cursor.getString(1);
            Log.i("this is token log", testIdx + " | " + testToken);
        }

        Log.i("THIS IS JUST TEST AND ", "DB COUNT RESULT : "+ count);

        return testToken;
    }
}
