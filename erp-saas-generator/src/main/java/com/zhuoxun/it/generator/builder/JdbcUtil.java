package com.zhuoxun.it.generator.builder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manipulate the data source utility class
 * 
 * @author liwen
 */
public class JdbcUtil {

    private final static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);

    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String REMARKS = "REMARKS";
    private static final String DATA_TYPE = "DATA_TYPE";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String COLUMN_SIZE = "COLUMN_SIZE";

    /**
     * 注册数据库驱动
     */
    static {
        try {
            Class.forName(Constant.driverClassName);
        } catch (ClassNotFoundException e) {
            logger.error("Load drive failure:", e);
        }
    }

    /**
     * get datasource connection
     * 
     * @throws SQLException
     */
    public static Connection getConnection(String url, String user, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.error("Load drive failure:", e);
        }
        return conn;
    }

    /**
     * 关闭连接资源
     * 
     * @throws SQLException
     */
    public static void close(Connection conn) {
        try {
            if (null != conn) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("close db conn error:", e);
        }
    }

    /**
     * 获取表单所有信息
     * 
     * @return List<TableInfo> 返回表单数据集合
     */
    public static List<TableInfo> getTables(Connection connection) {
        List<TableInfo> tableInfos = new ArrayList<TableInfo>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            System.err.println(metaData.getSchemas());
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", new String[] {"TABLE"});
            while (tables.next()) {
                TableInfo tableInfo = new TableInfo();
                String table_name = tables.getString(TABLE_NAME);
                String remarkes = tables.getString(REMARKS);
                tableInfo.setTableName(table_name);
                tableInfo.setEntityName(StringUtils.toUpperCaseFirst(StringUtils.underlineToCamel(table_name)));
                tableInfo.setPackageName("");
                tableInfo.setComments(remarkes);
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, table_name, "%");
                List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
                boolean isPk = false;
                while (columns.next()) {
                    String colName = columns.getString(COLUMN_NAME);
                    String comments = columns.getString(REMARKS);
                    int dataType = columns.getInt(DATA_TYPE);
                    int columnSize = columns.getInt(COLUMN_SIZE);
                    EntityMapping data = EntityMapping.forKey(dataType);
                    if (null == tableInfo.getImportPackages()) {
                        tableInfo.setImportPackages(new HashSet<String>());
                    }
                    String attrName = StringUtils.underlineToCamel(colName);
                    if (!"createdDate".equals(attrName) && !"lastUpdateDate".equals(attrName)) {
                        tableInfo.getImportPackages().add(data.getJavaType());
                    }
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setColumnSize(columnSize);
                    fieldInfo.setFieldName(colName);
                    fieldInfo.setAttrName(attrName);
                    fieldInfo.setEm(data);
                    fieldInfo.setComments(comments);
                    fieldInfos.add(fieldInfo);
                    if (!isPk) {
                        isPk = true;
                        tableInfo.setPk(fieldInfo);
                    }
                }
                tableInfo.setFields(fieldInfos);
                tableInfos.add(tableInfo);
            }
        } catch (SQLException e) {
            System.err.println(e);
            logger.error("db error:", e);
        } finally {
            close(connection);
        }
        return tableInfos;
    }

}
