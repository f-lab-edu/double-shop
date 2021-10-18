package com.project.doubleshop.domain.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StatusTypeHandler extends BaseTypeHandler<Status> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws
		SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return Status.of(rs.getInt(columnName));
	}

	@Override
	public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return Status.of(rs.getInt(columnIndex));
	}

	@Override
	public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return Status.of(cs.getInt(columnIndex));
	}
}
