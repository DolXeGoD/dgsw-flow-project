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
        db.execSQL("CREATE TABLE IF NOT EXISTS t_token (idx INTEGER PRIMARY KEY AUTOINCREMENT, token TEXT)");

        //새로운 테이블 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS t_out (" +
                "idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "accept INTEGER, " +
                "start_time TEXT, " +
                "end_time TEXT, " +
                "reason TEXT, " +
                "class_idx INTEGER, " +
                "student_email TEXT, " +
                "server_idx INTEGER " +
                ")");

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void droptable(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE t_out ") ;
        onCreate( db ) ;
    }

    public void out_insert(int accept, String start_time, String end_time, String reason, int class_idx, String student_email, int server_idx){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO t_out(accept, start_time, end_time, reason, class_idx, student_email,server_idx) " +
                "VALUES('" + accept +"','" + start_time + "','" + end_time + "','" + reason + "',"+ class_idx + ",'"+student_email+"',"+server_idx+") ");
        Cursor cursor = null;

        if(cursor != null){
            cursor.moveToFirst();
        }
        String query = "SELECT * FROM t_out";

        cursor = db.rawQuery(query, null);
        int cnt = cursor.getCount();
        for(int i=0;i<cnt;i++){
            cursor.moveToNext();
            Log.i("SELECT AFTER INSERT", cursor.getInt(0) + ", "+cursor.getInt(1) + ", "+cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4)
            + ", " + cursor.getInt(5) + ", " + cursor.getString(6) + ", " + cursor.getInt(7));
            Log.i("COUNT COUNT COUNT", "COUNT GET SU : " + cnt + " AND SERVER COUNT GET SU" + cursor.getInt(7));
        }
    }

    public int token_insert(String token){
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

    //디버깅용 delete 함수. insert 테스트를 위해 idx 2부터 모든 데이터를 없앰.
    public void token_delete(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM t_token WHERE idx > 1");
        token_resetAi();
    }

    public void token_resetAi(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 't_token'");
    }

    public void out_delete(int idx){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM t_out WHERE idx = "+idx);
        out_resetAi();
    }

    public void out_resetAi(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 't_out'");
    }

    public String token_select(){
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

    public Cursor out_select(int idx){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM t_out WHERE idx = "+idx;
        Cursor cursor = null;
        cursor = db.rawQuery(query, null);

        return cursor;
    }

    public void update_accept(int server_idx){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE t_out SET accept = 1 WHERE idx = "+server_idx);
    }

    public int out_count(){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM t_out";
        Cursor cursor = null;
        cursor = db.rawQuery(query, null);

        count = cursor.getCount();

        for(int i = 0;i<count;i++){
            cursor.moveToNext();
            int index = cursor.getInt(0);
            int accept = cursor.getInt(1);
            String start_time = cursor.getString(2);
            String student_email = cursor.getString(6);
            int server_idx = cursor.getInt(7);
            Log.i("test",index + " | " + accept + " | " + start_time + " | " + student_email + " | " + server_idx);
            /*Log.i("SELECT AFTER INSERT", cursor.getInt(0) + ", "+cursor.getInt(1) + ", "+cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4)
                    + ", " + cursor.getInt(5) + ", " + cursor.getString(6) + ", " + cursor.getInt(7));*/
        }
        return count;
    }
}
