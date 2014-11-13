package org.dcache.tests.datanucleus;

import org.junit.Before;
import org.junit.Test;

import org.dcache.tests.datanucleus.hsqldb.HsqldbEntry;
import org.dcache.tests.datanucleus.hsqldb.HsqldbStore;
import org.dcache.tests.datanucleus.xml.XmlEntry;
import org.dcache.tests.datanucleus.xml.XmlStore;

import static org.junit.Assert.fail;

public class EnhancerHelperTest2 {

    private  XmlStore xmlStore;
    private  HsqldbStore hsqldbStore;

    @Before
    public void setUp() throws Exception {
        xmlStore = new XmlStore();
        hsqldbStore = new HsqldbStore();
    }

    @Test
    public void test0DatastoreFirst() {
        try {
            hsqldbStore.storeHsqldb(new HsqldbEntry("testDatastoreFirst"));
            xmlStore.storeXml(new XmlEntry("testDatastoreFirst"));
        } catch (Throwable t) {
            Throwable cause = t.getCause();
            fail("Running xml database in same JVM with "
                            + "another database using datastore "
                            + "identity failed: " + t + "; cause " + cause);
        }
    }
}
