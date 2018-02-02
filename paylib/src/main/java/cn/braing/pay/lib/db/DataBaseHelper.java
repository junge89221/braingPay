package cn.braing.pay.lib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite数据库的帮助类
 * <p>
 * 该类属于扩展类,主要承担数据库初始化和版本升级使用,其他核心全由核心父类完成
 *
 * @author 张俊
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";

    /**
     * 用户表
     **/
    public static final String TABLE_USER_CONFIG = "userconfig";


    public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE IF NOT EXISTS [" + TABLE_USER_CONFIG + "]([user_data] text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            /*db.execSQL("DELETE FROM " + TABLE_USER_ORDER);
            // // 更新
			boolean b = checkColumnExist(db, TABLE_USER_ORDER, "payStatus");
			if (!b) {
				db.execSQL("ALTER TABLE " + TABLE_USER_ORDER + " ADD COLUMN payStatus INT;");
			}*/

            db.execSQL("CREATE TABLE IF NOT EXISTS [" + TABLE_USER_CONFIG + "]([id] INT AUTO_INCREMENT PRIMARY KEY  ,[user_data] text)");
            onCreate(db);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * 检查某表列是否存在
     *
     * @param db
     * @param tableName  表名
     * @param columnName 列名
     * @return
     */
    @SuppressWarnings("unused")
    private boolean checkColumnExist(SQLiteDatabase db, String tableName, String columnName) {
        boolean result = false;
        Cursor cursor = null;
        try {
            // 查询一行
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);
            result = cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e) {
            // LogUtil.e(TAG, "checkColumnExists1..." + e.getMessage());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return result;
    }
}
