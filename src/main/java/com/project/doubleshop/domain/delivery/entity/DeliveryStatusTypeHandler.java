package com.project.doubleshop.domain.delivery.entity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class DeliveryStatusTypeHandler extends BaseTypeHandler<DeliveryStatus> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DeliveryStatus parameter, JdbcType jdbcType) throws
		SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public DeliveryStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return DeliveryStatus.of(rs.getInt(columnName));
	}

	@Override
	public DeliveryStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return DeliveryStatus.of(rs.getInt(columnIndex));
	}

	@Override
	public DeliveryStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return DeliveryStatus.of(cs.getInt(columnIndex));
	}
}
