package com.alphatok.graph;

import java.util.HashMap;
import java.util.Map;

public abstract class GraphVizFileBuilder {

    protected final static String START_LINE = "digraph G {\n";
    protected final static String END_LINE = "}\n";

    public final static Map<String, String> WHITE_ALL = new HashMap<>(2);
    static {
        WHITE_ALL.put("color", "white");
        WHITE_ALL.put("fontcolor", "white");
    }

    public abstract StringBuilder build();
}
