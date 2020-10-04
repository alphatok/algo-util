package com.alphatok.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeUtil {
    public static String openFile(String filename) throws IOException {
        String [] commands = {
                "cmd.exe","/c","start","\"DummyTitle\"","\"" + filename + "\""
        };

        return doExec(commands);
    }

    private static String doExec(String[] commands) throws IOException {
        Runtime rt = Runtime.getRuntime();

        StringBuilder commandLine = new StringBuilder();
        for (String command : commands) {
            commandLine.append(command).append(" ");
        }
        System.out.println("execing commands: " + commandLine);
        Process proc = rt.exec(commands);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = stdInput.readLine()) != null) {
            buffer.append(line).append("\n");
        }

        // Read any errors from the attempted command
        StringBuilder error = new StringBuilder();
        while ((line = stdError.readLine()) != null) {
            error.append(line).append("\n");
        }
        if (error.length() > 0) {
            throw new RuntimeException("failed msg: " + error.toString());
        }

        return buffer.toString();
    }

    public static String execCommand(String command) throws IOException {
        String [] commands = {
                "cmd.exe","/c", command
        };

        return doExec(commands);
    }
}
