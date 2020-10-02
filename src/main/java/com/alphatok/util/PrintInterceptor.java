package com.alphatok.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrintInterceptor {
    public static String print(Printer printer){
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);
        PrintStream cacheStream = new PrintStream(baoStream);
        PrintStream oldStream = System.out;
        System.setOut(cacheStream);
        printer.doPrint();
        System.setOut(oldStream);
        return baoStream.toString();
    }
}
