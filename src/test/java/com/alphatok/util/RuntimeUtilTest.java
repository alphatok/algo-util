package com.alphatok.util;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class RuntimeUtilTest {

    private String getImagePath() throws IOException {
        File directory = new File("");
        String courseFile = directory.getCanonicalPath();
        return courseFile + File.separator +  "src\\test\\resources\\read.jpg";
    }

    @Test
    @Ignore
    public void openFile() throws IOException {
        RuntimeUtil.openFile(getImagePath());
    }
}