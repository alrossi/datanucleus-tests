package org.dcache.tests.datanucleus;

import org.junit.Before;
import org.junit.Test;

import org.dcache.tests.datanucleus.hsqldb.HsqldbEntry;
import org.dcache.tests.datanucleus.hsqldb.HsqldbStore;
import org.dcache.tests.datanucleus.xml.XmlEntry;
import org.dcache.tests.datanucleus.xml.XmlStore;

import static org.junit.Assert.fail;

/**
 * Run the two Junit tests separately.
 *
 * This test fails with the message:
 * <p>
 *java.lang.AssertionError: Running xml database in same JVM with another
 *  database using datastore identity failed: java.lang.ExceptionInInitializerError;
 *      cause org.datanucleus.metadata.InvalidClassMetaDataException:
 *      Class "org.dcache.tests.datanucleus.hsqldb.HsqldbEntry" :
 *      Datastore ID not supported for XML
 * </p>
 *
 * Why does the enhancer helper think that the Hsqldb datatype must be
 * stored to the XML database?
 *
 * @author arossi
 */
public class EnhancerHelperTest1 {

    private  XmlStore xmlStore;
    private  HsqldbStore hsqldbStore;

    @Before
    public void setUp() throws Exception {
        xmlStore = new XmlStore();
        hsqldbStore = new HsqldbStore();
    }

    @Test
    public void test1XmlFirst() {
        try {
            xmlStore.storeXml(new XmlEntry("testXmlFirst"));
            hsqldbStore.storeHsqldb(new HsqldbEntry("testXmlFirst"));
        } catch (Throwable t) {
            Throwable cause = t.getCause();
            fail("Running xml database in same JVM with "
                            + "another database using datastore "
                            + "identity failed: " + t + "; cause " + cause);
        }
    }
}
