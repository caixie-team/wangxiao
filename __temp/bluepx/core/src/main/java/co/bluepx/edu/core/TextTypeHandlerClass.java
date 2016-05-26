package co.bluepx.edu.core;

import org.apache.ibatis.type.StringTypeHandler;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 解析mysql text 类型字段，防止乱码
 */
public class TextTypeHandlerClass extends StringTypeHandler {

    @Override
    public String getResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        byte[] bytes = cs.getBytes(columnIndex);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName)
            throws SQLException {
        byte[] bytes = rs.getBytes(columnName);
        try {
            if (bytes != null)
                return new String(bytes, "UTF-8");
            else
                return rs.getString(columnName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
