package com.alphatok.graph;

import org.junit.Test;

public class GraphVizTest {
    @Test
    public void test(){
        GraphViz gViz=new GraphViz("F:\\github\\algo-util\\target", "C:\\Program Files\\Graphviz 2.44.1\\bin\\dot.exe");
        gViz.start_graph();
        gViz.addln("A->B;");
        gViz.addln("A->C;");
        gViz.addln("C->B;");
        gViz.addln("B->D;");
        gViz.addln("C->E;");
        gViz.addln("E->F;");
        gViz.addln("F->G;");
        gViz.end_graph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}