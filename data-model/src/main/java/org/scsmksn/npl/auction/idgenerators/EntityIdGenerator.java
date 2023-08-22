package org.scsmksn.npl.auction.idgenerators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class EntityIdGenerator implements IdentifierGenerator {

    public static final String ID_COUNT = "id_count";
    public static final String SELECT_COUNT_SQL_FORMAT = "select count(id) as " + ID_COUNT + " from %s";
    public static final String TABLE_NAME = "table_name";
    private String tableName;

    @Override
    public void configure(final Type type, final Properties params, final ServiceRegistry serviceRegistry) {
        tableName = params.getProperty(TABLE_NAME);
    }

    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object object) {
        return session.execute(connection -> {
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(String.format(SELECT_COUNT_SQL_FORMAT, tableName));
            if (rs.next()) {
                return rs.getLong(ID_COUNT) + 1L;
            }
            return null;
        });
    }
}
