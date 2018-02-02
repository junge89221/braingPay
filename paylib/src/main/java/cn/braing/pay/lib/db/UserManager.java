package cn.braing.pay.lib.db;

import android.content.Context;
import android.database.Cursor;


import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import cn.braing.pay.lib.bean.User;

import static cn.braing.pay.lib.db.DataBaseHelper.TABLE_USER_CONFIG;


/**
 * Created by zhangli on 2017/10/24
 */

public class UserManager {
    private static UserManager noticeManager = null;
    private static DBManager manager = null;
    private Context context;

    private UserManager(Context context) {
        manager = DBManager.create(context);
        this.context = context;
    }

    public static UserManager getInstance(Context context) {

        if (noticeManager == null) {
            noticeManager = new UserManager(context);
        }

        return noticeManager;
    }





    public void saveUser(User user) {
        deleteUser();
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager);
        st.saveObject(user, TABLE_USER_CONFIG, "user_data");
    }

    public long deleteUser() {
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager);
        return st.clearTable(TABLE_USER_CONFIG);
    }

    public User getUser() {
        SQLiteTemplate st = SQLiteTemplate.getInstance(manager);
        return st.queryForObject(new SQLiteTemplate.RowMapper<User>() {

            @Override
            public User mapRow(Cursor cursor, int index) {
                User user = null;
                byte[] user_data = cursor.getBlob(cursor.getColumnIndex("user_data"));
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(user_data);
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                    user = (User) inputStream.readObject();
                    inputStream.close();
                    arrayInputStream.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return user;
            }
        }, "select * from " + TABLE_USER_CONFIG, null);
    }
}
