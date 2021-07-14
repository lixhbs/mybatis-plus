package com.baomidou.mybatisplus.extension.plugins.pagination.dialects;

import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;

/**
 * GBase8s 数据库分页方言
 */
public class InformixDialect implements IDialect {
    @Override
    public DialectModel buildPaginationSql(String originalSql, long offset, long limit) {
        int slot = originalSql.toLowerCase().indexOf("select");
        StringBuilder sql = new StringBuilder();
        if (slot == 0) {
            sql.append("SELECT");
            if (offset != 0L) {
                sql.append(" SKIP ").append(SECOND_MARK);
                sql.append(" FIRST ").append(FIRST_MARK);
                sql.append(originalSql.substring(6));
                return new DialectModel(sql.toString(), offset, limit).setConsumerChain();
            } else {
                sql.append(" FIRST ").append(FIRST_MARK);
                sql.append(originalSql.substring(6));
                return new DialectModel(sql.toString(), limit).setConsumer(true);
            }
        }
        return null;
    }
}
