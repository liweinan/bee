package net.bluedash.bee.model;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * 11 08 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
public class BeeNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String classToTableName(String className) {
        return "bee_" + super.classToTableName(className);
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return "col_" + super.propertyToColumnName(propertyName);
    }

    @Override
    public String joinKeyColumnName(String joinedColumn, String joinedTable) {
        return "join_" + super.joinKeyColumnName(joinedColumn, joinedTable).replace("col_", "");
    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return "ref_" + super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName).replace("col_", "");
    }

    @Override
    public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable, String propertyName) {
        return tableName(ownerEntityTable + "_" + propertyToColumnName(propertyName).replace("col_", ""));
    }
}
