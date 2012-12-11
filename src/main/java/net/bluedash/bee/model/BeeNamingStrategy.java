package net.bluedash.bee.model;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * 11 08 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
public class BeeNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String tableName(String tableName) {
        return "bee_" + super.tableName(tableName);
    }

//    public String columnName(String columnName) {
//        return "col_" + super.columnName(columnName);
//    }
}
