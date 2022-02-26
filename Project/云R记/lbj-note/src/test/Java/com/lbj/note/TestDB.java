package com.lbj.note;

import com.lbj.note.util.DBUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yoonalim
 * @create 2022-01-03-17:19
 */

public class TestDB {
    // 使用日志工厂类，记录日志
    private Logger logger = LoggerFactory.getLogger(TestDB.class);
    /*
     * 单元测试方法
     * 1. 方法返回值：建议用 void, 一般没有返回值
     * 2. 方法参数：空参，一般没有参数
     * 3. 每个方法需要就设置 @Test 注解，这样每个方法才能独立运行
     */
    @Test
    public void testDB() {
        System.out.println(DBUtil.getConnection());
        // 使用日志 : 两种方式 第二种的大括号放在哪里，参数 DBUtil.getConnection() 的内容就打印在哪里
        logger.info("获取数据库连接：" + DBUtil.getConnection());
        logger.info("获取数据库连接：{}" , DBUtil.getConnection());
    }
}
