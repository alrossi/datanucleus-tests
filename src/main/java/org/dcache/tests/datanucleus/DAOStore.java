package org.dcache.tests.datanucleus;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;

import java.util.Properties;

public abstract class DAOStore {
    protected JDOPersistenceManagerFactory pmf;

    protected DAOStore(String connectionURL) {
        Properties properties = new Properties();
        properties.setProperty("javax.jdo.option.ConnectionURL",
                        connectionURL);
        configure(properties);
        pmf = new JDOPersistenceManagerFactory(properties);
    }

    protected abstract void configure(Properties properties);
}
