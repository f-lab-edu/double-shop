package com.project.doubleshop.delivery.entity.enumuration;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class FeeMethodTypeHandler extends BaseTypeHandler<FeeMethod> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, FeeMethod parameter, JdbcType jdbcType) throws
		SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public FeeMethod getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return FeeMethod.of(rs.getInt(columnName));
	}

	@Override
	public FeeMethod getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return FeeMethod.of(rs.getInt(columnIndex));
	}

	@Override
	public FeeMethod getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return FeeMethod.of(cs.getInt(columnIndex));
	}
}
