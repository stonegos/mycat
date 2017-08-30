package org.opencloudb.parser.druid.impl;

import com.alibaba.druid.sql.ast.SQLStatement;
import org.opencloudb.config.model.SchemaConfig;
import org.opencloudb.route.RouteResultset;

import java.sql.SQLNonTransientException;

public class DruidDeleteParser extends DefaultDruidParser {
	@Override
	public void statementParse(SchemaConfig schema, RouteResultset rrs, SQLStatement stmt) throws SQLNonTransientException {
//		MySqlDeleteStatement delete = (MySqlDeleteStatement)stmt;
//		String tableName = StringUtil.removeBackquote(delete.getTableName().getSimpleName().toUpperCase());
//		ctx.addTable(tableName);
	}
}

