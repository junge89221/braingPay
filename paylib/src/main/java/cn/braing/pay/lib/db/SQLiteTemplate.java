package cn.braing.pay.lib.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite数据库模板工具类 单例
 * <p>
 * 该类提供了数据库操作常用的增删改查,以及各种复杂条件匹配,分页,排序等操作
 *
 * @see SQLiteDatabase
 */
public class SQLiteTemplate {
    /**
     * Default Primary key
     */
    protected String mPrimaryKey = "_id";

    private static SQLiteTemplate sqliteTemplate;

    /**
     * DBManager
     */
    private DBManager dBManager;

    private SQLiteTemplate() {
    }

    private SQLiteTemplate(DBManager dBManager) {
        this.dBManager = dBManager;
    }

    public static SQLiteTemplate getInstance(DBManager dBManager) {
        if (sqliteTemplate == null) {
            sqliteTemplate = new SQLiteTemplate(dBManager);
        }
        return sqliteTemplate;
    }


    /**
     * 保存 user
     *
     * @param user
     */
    public synchronized <T> long saveObject(T user, String tab, String col) {
        SQLiteDatabase dataBase = null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            dataBase = dBManager.openWritableDatabase();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            byte data[] = arrayOutputStream.toByteArray();
            objectOutputStream.close();
            arrayOutputStream.close();
            ContentValues values = new ContentValues();
            values.put(col, data);
            return dataBase.insert(tab, null, values);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }


    /**
     * 执行一条sql语句，加了同步代码，避免多线程读写导致数据库关闭异常
     */
    public synchronized void execSQL(String sql) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            dataBase.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
    }

    /**
     * 执行一条sql语句，加了同步代码，避免多线程读写导致数据库关闭异常
     */
    public synchronized void execSQL(String sql, Object[] bindArgs) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            dataBase.execSQL(sql, bindArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
    }

    /**
     * 向数据库表中插入一条数据
     *
     * @param table   表名
     * @param content 字段值
     */
    public synchronized long insert(String table, ContentValues content) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            // insert方法第一参数：数据库表名，第二个参数如果CONTENT为空时则向表中插入一个NULL,第三个参数为插入的内容
            return dataBase.insert(table, null, content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }

    /**
     * 向数据库表中插入一批数据
     *
     * @param table    表名
     * @param contents 字段值
     */
    public synchronized void insertAll(String table, List<ContentValues> contents) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            // insert方法第一参数：数据库表名，第二个参数如果CONTENT为空时则向表中插入一个NULL,第三个参数为插入的内容
            for (ContentValues content : contents) {
                dataBase.insert(table, null, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
    }


    /**
     * 批量删除指定主键数据
     */
    public synchronized void deleteByIds(String table, Object... primaryKeys) {
        SQLiteDatabase dataBase = null;
        try {
            if (primaryKeys.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (@SuppressWarnings("unused")
                        Object id : primaryKeys) {
                    sb.append("?").append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                dataBase = dBManager.openWritableDatabase();
                dataBase.execSQL("delete from " + table + " where " + mPrimaryKey + " in(" + sb + ")", primaryKeys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
    }


    public synchronized int clearTable(String table) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            return dataBase.delete(table, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }

    /**
     * 根据某一个字段和值删除一行数据, 如 name="jack"
     *
     * @param table
     * @param field
     * @param value
     * @return 返回值大于0表示删除成功
     */
    public synchronized int deleteByField(String table, String field, String value) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            return dataBase.delete(table, field + "=?", new String[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }

    /**
     * 根据条件删除数据
     *
     * @param table       表名
     * @param whereClause 查询语句 参数采用?
     * @param whereArgs   参数值
     * @return 返回值大于0表示删除成功
     */
    public synchronized int deleteByCondition(String table, String whereClause, String[] whereArgs) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            return dataBase.delete(table, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }

        return 0;
    }

    /**
     * 根据主键删除一行数据
     *
     * @param table
     * @param id
     * @return 返回值大于0表示删除成功
     */
    public synchronized int deleteById(String table, String id) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            return deleteByField(table, mPrimaryKey, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }

    /**
     * 根据主键更新一行数据
     *
     * @param table  要操作的表
     * @param id     要修改的数据ID
     * @param values 列对应的数据
     * @return 返回值大于0表示更新成功
     */
    public synchronized int updateById(String table, String id, ContentValues values) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            return dataBase.update(table, values, mPrimaryKey + "=?", new String[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }

    /**
     * 更新数据
     *
     * @param table       要操作的表
     * @param values      列对应的数据
     * @param whereClause 可选的Where语句
     * @param whereArgs   whereClause语句中表达式的？占位参数列表，参数只能为String类型
     * @return 返回值大于0表示更新成功
     */
    public synchronized int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openWritableDatabase();
            return dataBase.update(table, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return 0;
    }

    /**
     * 根据主键查看某条数据是否存在
     *
     * @param table
     * @param id
     * @return
     */
    public synchronized Boolean isExistsById(String table, String id) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            return isExistsByField(table, mPrimaryKey, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return null;
    }

    /**
     * 根据某字段/值查看某条数据是否存在
     *
     * @return
     */
    public synchronized Boolean isExistsByField(String table, String field, String value) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ").append(field).append(" =?");
        SQLiteDatabase dataBase = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            return isExistsBySQL(sql.toString(), new String[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, null);
        }
        return null;
    }

    /**
     * 使用SQL语句查看某条数据是否存在
     *
     * @param sql
     * @param selectionArgs
     * @return
     */
    public synchronized Boolean isExistsBySQL(String sql, String[] selectionArgs) {
        SQLiteDatabase dataBase = null;
        Cursor cursor = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.rawQuery(sql, selectionArgs);
            if (cursor.moveToFirst()) {
                return (cursor.getInt(0) > 0);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return null;
    }

    /**
     * 查询一条数据
     *
     * @param rowMapper
     * @param sql
     * @param args
     * @return
     */
    public synchronized <T> T queryForObject(RowMapper<T> rowMapper, String sql, String[] args) {
        SQLiteDatabase dataBase = null;
        Cursor cursor = null;
        T object = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.rawQuery(sql, args);
            if (cursor.moveToFirst()) {
                object = rowMapper.mapRow(cursor, cursor.getCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return object;

    }

    /**
     * 查询
     *
     * @param rowMapper
     * @param sql       开始索引 注:第一条记录索引为0
     *                  步长
     * @return
     */
    public synchronized <T> List<T> queryForList(RowMapper<T> rowMapper, String sql, String[] selectionArgs) {
        SQLiteDatabase dataBase = null;
        Cursor cursor = null;
        List<T> list = null;
        try {
            list = new ArrayList<T>();
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.rawQuery(sql, selectionArgs);
            while (cursor.moveToNext()) {
                list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return list;
    }

    /**
     * 分页查询
     *
     * @param rowMapper
     * @param sql
     * @param startResult 开始索引 注:第一条记录索引为0
     * @param maxResult   步长
     * @return
     */
    public synchronized <T> List<T> queryForList(RowMapper<T> rowMapper, String sql, int startResult, int maxResult) {
        SQLiteDatabase dataBase = null;
        Cursor cursor = null;
        List<T> list = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.rawQuery(sql + " limit ?,?", new String[]{String.valueOf(startResult), String.valueOf(maxResult)});
            list = new ArrayList<T>();
            while (cursor.moveToNext()) {
                list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return list;
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public synchronized Integer getCount(String table) {
        SQLiteDatabase dataBase = null;
        Cursor cursor = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.rawQuery("select count(*) from " + table, null);
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return 0;
    }


    /**
     * 根据订单状态和订单类型获取记录数
     *
     * @return
     */
    public synchronized Integer getCount(String table, int OrderType, int OrderState) {
        SQLiteDatabase dataBase = null;
        Cursor cursor = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.rawQuery("select count(*) from " + table + "where OrderType = " + OrderType + "and OrderState = " + OrderState, null);
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return 0;
    }


    /**
     * 分页查询
     *
     * @param rowMapper
     * @param table         检索的表
     * @param columns       由需要返回列的列名所组成的字符串数组，传入null会返回所有的列。
     * @param selection     查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符"?"
     * @param selectionArgs 对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常
     * @param groupBy       对结果集进行分组的group by语句（不包括GROUP BY关键字）。传入null将不对结果集进行分组
     * @param having        对查询后的结果集进行过滤,传入null则不过滤
     * @param orderBy       对结果集进行排序的order by语句（不包括ORDER BY关键字）。传入null将对结果集使用默认的排序
     * @param limit         指定偏移量和获取的记录数，相当于select语句limit关键字后面的部分,如果为null则返回所有行
     * @return
     */
    public synchronized <T> List<T> queryForList(RowMapper<T> rowMapper, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        List<T> list = null;
        SQLiteDatabase dataBase = null;

        Cursor cursor = null;
        try {
            dataBase = dBManager.openReadableDatabase();
            cursor = dataBase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            list = new ArrayList<T>();
            while (cursor.moveToNext()) {
                list.add(rowMapper.mapRow(cursor, cursor.getPosition()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(dataBase, cursor);
        }
        return list;
    }

    /**
     * Get Primary Key
     *
     * @return
     */
    public String getPrimaryKey() {
        return mPrimaryKey;
    }

    /**
     * Set Primary Key
     *
     * @param primaryKey
     */
    public void setPrimaryKey(String primaryKey) {
        this.mPrimaryKey = primaryKey;
    }

    /**
     * @param <T>
     * @author 张俊
     */
    public interface RowMapper<T> {
        /**
         * @param cursor 游标
         * @param index  下标索引
         * @return
         */
        T mapRow(Cursor cursor, int index);
    }

    /**
     * 关闭数据库
     */
    public void closeDatabase(SQLiteDatabase dataBase, Cursor cursor) {
        if (null != dataBase) {
            dataBase.close();
        }
        if (null != cursor) {
            cursor.close();
            cursor = null;
        }
    }
}
