package com.project.doubleshop.domain.delivery.entity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;



public class FeePolicyTypeHandler extends BaseTypeHandler<FeePolicy> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, FeePolicy parameter, JdbcType jdbcType) throws
		SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public FeePolicy getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return FeePolicy.of(rs.getInt(columnName));
	}

	@Override
	public FeePolicy getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return FeePolicy.of(rs.getInt(columnIndex));
	}

	@Override
	public FeePolicy getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return FeePolicy.of(cs.getInt(columnIndex));
	}
}
