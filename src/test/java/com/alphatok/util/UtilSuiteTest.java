package com.alphatok.util;


import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CollectionUtilTest.class,
        StringUtilTest.class,
        ListNodeUtilTest.class,
        // 接着写其他被测单元测试类
})

public class UtilSuiteTest extends TestCase {

}