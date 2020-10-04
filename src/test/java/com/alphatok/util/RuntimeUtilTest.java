package com.alphatok.util;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class RuntimeUtilTest {

    private String getImagePath() throws IOException {
        String projecRoot = getProjectRoot();
        return projecRoot + File.separator +  "src\\test\\resources\\read.jpg";
    }

    private String getProjectRoot() throws IOException {
        File directory = new File("");
        return directory.getCanonicalPath();
    }

    @Test
    @Ignore
    public void openFile() throws IOException {
        System.out.println("RuntimeUtil.openFile(getImagePath()) = " + RuntimeUtil.openFile(getImagePath()));
    }

    @Test
    public void execCommand() throws IOException {
        String projecRoot = getProjectRoot();
        RuntimeUtil.execCommand("cd " + projecRoot);
        System.out.println("RuntimeUtil.execCommand(\"dir \") = " + RuntimeUtil.execCommand("dir "));
    }
}