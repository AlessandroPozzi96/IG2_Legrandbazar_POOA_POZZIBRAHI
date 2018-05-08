package businessPackage;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

public class ConversionStringTest {
    private OrdreManager manager;
    private Integer dixI;

    @Before
    public void setUp() throws Exception {
        manager = new OrdreManager();
    }

    @Test
    public void conversionStringVersInteger() {
        Assert.assertEquals((Integer) 10, manager.conversionStringVersInteger("10"));
        dixI = -1;
        Assert.assertEquals(dixI, manager.conversionStringVersInteger("-1"));
        Assert.assertEquals(dixI, manager.conversionStringVersInteger(""));
        Assert.assertEquals(dixI, manager.conversionStringVersInteger("ABC"));
        Assert.assertEquals(dixI, manager.conversionStringVersInteger("10.0"));
    }
}