package vnlozan.eatmanagerproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by V on 23.06.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    String DB_PATH=null;
    private static String DB_NAME="menu";
    private SQLiteDatabase myDataBase;
    private Context context;
    public DataBaseHelper(Context context) {
        super(context,DB_NAME,null,10);
        this.context=context;
        DB_PATH="data/data/"+context.getPackageName()+"/"+ "databases" +"/";
        Log.e("PATH 1",DB_PATH);
    }
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist)
        {
            System.out.println("EXISTS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //WHEN ITS GONNA BE FULL
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
            //END
        }
        else
        {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }
    }
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try
        {
            String myPath = DB_PATH + DB_NAME;
            Log.e("PATH CERTAIN",myPath);
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch(SQLiteException e)
        {
            //database does't exist yet.
        }
        if(checkDB != null)
        {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
    private void copyDataBase() throws IOException{
        System.out.println("COPY STARTEDDDDDDDDDDDDDDDDD@!!!!");
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[4096];
        int length;
        while ((length = myInput.read(buffer))>0)
            myOutput.write(buffer, 0, length);
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        System.out.println("COPY ENDED!!!!!!!!!!!!!!!!!");
    }
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion)
            try
            {
                copyDataBase();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }
    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        System.out.println(table+"!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try
        {
            return myDataBase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
