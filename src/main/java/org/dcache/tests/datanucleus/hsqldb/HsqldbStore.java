package org.dcache.tests.datanucleus.hsqldb;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import java.io.IOException;
import java.util.Properties;

import org.dcache.tests.datanucleus.DAOStore;

public class HsqldbStore extends DAOStore {
    public HsqldbStore() throws IOException {
        super("jdbc:hsqldb:mem:nucleus1");
    }

    protected void configure(Properties properties) {
        properties.setProperty("javax.jdo.option.ConnectionDriverName",
                               "org.hsqldb.jdbcDriver");
        properties.setProperty("javax.jdo.option.ConnectionUserName", "sa");
        properties.setProperty("javax.jdo.option.DetachAllOnCommit", "true");
        properties.setProperty("javax.jdo.option.Optimistic", "true");
        properties.setProperty("javax.jdo.option.NontransactionalRead", "true");
        properties.setProperty("javax.jdo.option.RetainValues", "true");
        properties.setProperty("javax.jdo.option.Multithreaded", "true");
        properties.setProperty("datanucleus.autoCreateSchema", "true");
        properties.setProperty("datanucleus.validateTables", "false");
        properties.setProperty("datanucleus.validateConstraints", "false");
        properties.setProperty("datanucleus.autoCreateColumns", "true");
        properties.setProperty("datanucleus.connectionPoolingType", "None");
    }

    public void storeHsqldb(HsqldbEntry entry) throws Exception {
        PersistenceManager insertManager = pmf.getPersistenceManager();
        Transaction tx = insertManager.currentTransaction();
        try {
            tx.begin();
            insertManager.makePersistent(entry);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }
}
