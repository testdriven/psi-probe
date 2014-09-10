/*
 * Licensed under the GPL License.  You may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package net.testdriven.psiprobe.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Mark Lewis
 */
public class InstrumentsTests {

    private String sunArchDataModelProperty;

    /**
     * Forces the tests to run in 32-bit mode.
     */
    @Before
    public void setUp() {
        sunArchDataModelProperty = System.getProperty("sun.arch.data.model");
        System.setProperty("sun.arch.data.model", "32");
    }

    /**
     * Undoes the changes made in {@link #setUp()}.
     */
    @After
    public void tearDown() {
        System.setProperty("sun.arch.data.model", sunArchDataModelProperty);
    }

    @Test
    public void testObject() {
        long oSize = Instruments.sizeOf(new Object());
        Assert.assertEquals(Instruments.SIZE_OBJECT, oSize);
    }


    @Test
    public void testBoolean() {
        long booleanSize = Instruments.sizeOf(Boolean.FALSE) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_BOOLEAN, booleanSize);
    }

    @Test
    public void testByte() {
        byte b = 0x00;
        long byteSize = Instruments.sizeOf(b) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_BYTE, byteSize);
    }

    @Test
    public void testChar() {
        char c = '\0';
        long charSize = Instruments.sizeOf(c) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_CHAR, charSize);
    }

    @Test
    public void testShort() {
        short s = 0;
        long shortSize = Instruments.sizeOf(s) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_SHORT, shortSize);
    }

    @Test
    public void testInt() {
        int i = 0;
        long intSize = Instruments.sizeOf(i) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_INT, intSize);
    }

    @Test
    public void testLong() {
        long l = 0;
        long longSize = Instruments.sizeOf(l) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_LONG, longSize);
    }

    @Test
    public void testFloat() {
        float f = 0.0f;
        long floatSize = Instruments.sizeOf(f) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_FLOAT, floatSize);
    }

    @Test
    public void testDouble() {
        double d = 0.0;
        long doubleSize = Instruments.sizeOf(d) - Instruments.SIZE_OBJECT;
        Assert.assertEquals(Instruments.SIZE_DOUBLE, doubleSize);
    }

    @Ignore("jdk changes probably broken this test")
    @Test
    public void testString() {
        String s = "test";
        long stringSize = Instruments.sizeOf(s);
        Assert.assertEquals((s.length() * Instruments.SIZE_CHAR) + (3 * Instruments.SIZE_INT) + Instruments.SIZE_OBJECT, stringSize);
    }

    @Ignore("jdk changes probably broken this test")
    @Test
    public void testList() {
        List<String> bikes = new ArrayList<>();
        bikes.add("specialized");
        bikes.add("kona");
        bikes.add("GT");
        long size = Instruments.sizeOf(bikes);
        Assert.assertEquals(110, size);
    }

    @Ignore("jdk changes probably broken this test")
    @Test
    public void testMap() {
        Map<String, Object> session = new HashMap<>();
        session.put("test1", "test message");
        List<String> bikes = new ArrayList<>();
        bikes.add("specialized");
        bikes.add("kona");
        bikes.add("GT");
        session.put("bikes", bikes);

        Map<String, Object> bikeParts = new TreeMap<>();
        bikeParts.put("bikes", bikes);
        session.put("parts", bikeParts);

        long size = Instruments.sizeOf(session);
        Assert.assertEquals(425, size);
    }

    @Ignore("jdk changes probably broken this test")
    @Test
    public void testCircularReference() {
        Map<String, Object> session = new HashMap<>();
        session.put("test1", "test message");
        session.put("self", session);
        long size = Instruments.sizeOf(session);
        Assert.assertEquals(186, size);
    }

}
