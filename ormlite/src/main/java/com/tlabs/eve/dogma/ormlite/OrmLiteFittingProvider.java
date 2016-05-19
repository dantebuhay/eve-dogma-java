package com.tlabs.eve.dogma.ormlite;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ReferenceObjectCache;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.tlabs.eve.dogma.model.Item;
import com.tlabs.eve.dogma.FittingProvider;

public class OrmLiteFittingProvider implements FittingProvider {
    private static final Logger LOG = LoggerFactory.getLogger(OrmLiteFittingProvider.class);

    private final Dao<TypeEntity, Long> itemDAO;

    private final OrmLiteHelper helper;

    public OrmLiteFittingProvider() throws SQLException {
        this("jdbc:sqlite:" + OrmLiteFittingProvider.class.getResource("/sdelite.sqlite").getFile());
    }

    public OrmLiteFittingProvider(final String jdbcUrl) throws SQLException {
        this(new JdbcPooledConnectionSource(jdbcUrl));
    }

    public OrmLiteFittingProvider(final ConnectionSource source) throws SQLException {
        this.helper = new OrmLiteHelper(source);
        this.helper.init();

        this.itemDAO = DaoManager.createDao(source, TypeEntity.class);
        this.itemDAO.setObjectCache(ReferenceObjectCache.makeSoftCache());
    }

    @Override
    public Item findItem(long itemID) {
        try {
            final TypeEntity entity = this.itemDAO.queryForId(itemID);
            return (null == entity) ? null : helper.transform(entity);
        }
        catch (SQLException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

    @Override
    public Item findItem(String typeName) {
        try {
            final List<TypeEntity> entities = this.itemDAO.queryForEq("typeName", StringUtils.replace(typeName, "'", "''"));
            if ((null == entities) || entities.isEmpty()) {
                return null;
            }
            return helper.transform(entities.get(0));
        }
        catch (SQLException e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

}
