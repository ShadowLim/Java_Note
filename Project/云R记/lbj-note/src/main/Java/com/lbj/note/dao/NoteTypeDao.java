package com.lbj.note.dao;


import com.lbj.note.po.NoteType;
import com.lbj.note.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class NoteTypeDao {

    /**
     *
     通过用户ID查询类型集合
     1. 定义SQL语句
        String sql = "select typeId,typeName,userId from tb_note_type where userId = ? ";
     2. 设置参数列表
     3. 调用BaseDao的查询方法，返回集合
     4. 返回集合
     * @param userId
     * @return
     */
    public List<NoteType> findTypeListByUserId(Integer userId) {
        // 1. 定义SQL语句
        String sql = "select typeId,typeName,userId from tb_note_type where userId = ? ";
        // 2. 设置参数列表
        List<Object> params = new ArrayList<>();
        params.add(userId);
        // 3. 调用BaseDao的查询方法，返回集合
        List<NoteType> list = BaseDao.queryRows(sql, params, NoteType.class);
        // 4. 返回集合
        return list;
    }

    /**
     *  通过类型ID查询云记记录的数量，返回云记数量
     * @param typeId
     * @return
     */
    public long findNoteCountByTypeId(String typeId) {
        // 定义 sql语句
        String sql = "select count(1) from tb_note where typeId = ?";
        // 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(typeId);
        // 调用BaseDao
        long count = (long) BaseDao.findSingleValue(sql, params);
        return count;
    }

    /**
     * 通过类型ID删除指定的类型记录，返回受影响的行数
     * @param typeId
     * @return
     */
    public int deleteTypeById(String typeId) {
        // 定义 sql语句
        String sql = "delete from tb_note_type where typeId = ?";
        // 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(typeId);
        // 调用BaseDao
        int row = BaseDao.executeUpdate(sql, params);
        return row;
    }

    /**
     * 查询当前登录用户下，类型名称是否唯一
     *  返回 1 成功  0 失败
     * @param typeName
     * @param userId
     * @param typeId
     * @return
     */
    public Integer checkTypeName(String typeName, Integer userId, String typeId) {
        // 定义 sql 语句
        String sql = "select * from tb_note_type where userId = ? and typeName = ?";
        // 设置参数
        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(typeName);
        // 执行查询操作
        NoteType noteType = (NoteType) BaseDao.queryRow(sql, params, NoteType.class);
        // 若对象为空 可用
        if (noteType == null) {
            return 1;
        } else {
            // 若为修改操作 则需要判断是否为当前记录本身
            if (typeId.equals(noteType.getTypeId().toString())) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 添加方法，返回主键
     * @param typeName
     * @param userId
     * @return
     */
    public Integer addType(String typeName, Integer userId) {
        Integer key = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 得到数据库连接
            connection = DBUtil.getConnection();
            // 定义 sql 语句
            String sql = "insert into tb_note_type (typeName,userId) values (?,?)";
            // 先预编译
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 设置参数
            preparedStatement.setString(1, typeName);
            preparedStatement.setInt(2, userId);
            // 执行更新 返回受影响的行数
            int row = preparedStatement.executeUpdate();
            // 判段受影响的行数
            if (row > 0) {
                // 获取主键 返回主键结果集
                resultSet = preparedStatement.getGeneratedKeys();
                // 得到主键的值
                if (resultSet.next()) {
                    key = resultSet.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        return  key;
    }

    /**
     * 修改方法，返回受影响的行数
     * @param typeName
     * @param typeId
     * @return
     */
    public Integer updateType(String typeName, String typeId) {
        // 定义SQL语句
        String sql = "update tb_note_type set typeName = ? where typeId = ?";
        // 设置参数
        List<Object> params = new ArrayList<>();
        params.add(typeName);
        params.add(typeId);
        // 调用BaseDao的更新方法
        int row = BaseDao.executeUpdate(sql, params);
        return row;
    }
}
