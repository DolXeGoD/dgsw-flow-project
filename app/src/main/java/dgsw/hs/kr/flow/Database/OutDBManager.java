package dgsw.hs.kr.flow.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 조현재 on 2018-05-08.
 */

public class OutDBManager extends SQLiteOpenHelper{

    public int count;

    public OutDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //새로운 테이블 생성
        db.execSQL("CREATE TABLE t_out (" +
                "idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "accept INTEGER DEFAULT 0, " +
                "start_time TEXT, " +
                "end_time TEXT, " +
                "reason TEXT, " +
                "class_idx INTEGER, " +
                "student_email TEXT " +
                ")");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(int accept, String start_time, String end_time, String reason, int class_idx, String student_email){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO t_out(accept, start_time, end_time, reason, class_idx, student_email) " +
                "VALUES('" + accept +"','" + start_time + "','" + end_time + "','" + reason + "',"+ class_idx + ",'"+student_email+"') ");
    }

    //디버깅용 delete 함수. insert 테스트를 위해 idx 2부터 모든 데이터를 없앰.
    public void delete(int idx){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM t_out WHERE idx = "+idx);
    }

    //조회할 idx를 파라미터로 받아 해당 cursor를 리턴시켜줌. 나머지 작업은 리스트뷰 어댑터쪽에서 진행.
    public Cursor select(int idx){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM t_out WHERE idx = "+idx;
        Cursor cursor = null;
        cursor = db.rawQuery(query, null);

        /*cursor.moveToNext();*/
        count = cursor.getCount();
        /*Log.i("FUCK YOU SSIBAL ",  cursor.getInt(1) + " fuck " + cursor.getString(3));*/
        /*for(int i = 0;i < count;i++){
            cursor.moveToNext();
            Log.i("VALUE TEST", "and accept : " + cursor.getInt(1) + "and start_time : " + cursor.getString(2)
                    + "and end_time : " + cursor.getString(3) + " end reason : " + cursor.getString(4)
                    + "and class_idx" + cursor.getInt(5) );
        }*/

        /*int rLength=cursor.getCount(); // 현재 row 번호(=전체 row갯수)
        int cLength=cursor.getColumnCount(); // 컬럼 갯수

        String[][] array=new String[rLength][cLength]; // row,column 2차원 배열 생성

        for(int i=0;i<rLength;i++){ // 행 길이 만큼 반복
            for(int j=0;j<cLength;j++){ // 열 길이 만큼 반복
                array[i][j]=cursor.getString(j); // 반복문 j로 한 행의 모든 컬럼을 읽음
                Log.i("THIS IS JUST TEST AND ", "DATA : "+ array[i][j]);
            }
            cursor.moveToNext(); // 다음 행으로 이동
        }*/

        Log.i("THIS IS JUST TEST AND ", "THIS IS DB COUNT RESULT : "+ count);

        return cursor;
    }
}
