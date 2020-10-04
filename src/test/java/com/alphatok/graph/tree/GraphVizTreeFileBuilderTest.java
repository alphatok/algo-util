package com.alphatok.graph.tree;

import com.alphatok.domain.TreeNode;
import com.alphatok.domain.TreeNodeLevelBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphVizTreeFileBuilderTest {

    @Test
    public void buildOneNode() {
        GraphVizTreeFileBuilder builder = GraphVizTreeFileBuilder.create(new TreeNode(1));
        String fileContent = builder.build().toString();
        System.out.println("fileContent = " + fileContent);
        String expected = "digraph G {\n" +
                "    No2 [color=grey,fontcolor=grey,label=nil,shape=none];\n" +
                "    No1 [label=1];\n" +
                "    No3 [color=grey,fontcolor=grey,label=nil,shape=none];\n" +
                "    No1->No2 [color=grey,fontcolor=grey];\n" +
                "    No1->No3 [color=grey,fontcolor=grey];\n" +
                "}\n";
        assertEquals(expected, fileContent);
    }

    @Test
    public void buildThreeNode() {
        TreeNode node = TreeNodeLevelBuilder.create("1,2,3").build();
        GraphVizTreeFileBuilder builder = GraphVizTreeFileBuilder.create(node);
        String fileContent = builder.build().toString();
        System.out.println("fileContent = " + fileContent);
        String expected = "digraph G {\n" +
                "    No2 [label=2];\n" +
                "    No1 [label=1];\n" +
                "    No4 [color=grey,fontcolor=grey,label=nil,shape=none];\n" +
                "    No3 [label=3];\n" +
                "    No6 [color=grey,fontcolor=grey,label=nil,shape=none];\n" +
                "    No5 [color=grey,fontcolor=grey,label=nil,shape=none];\n" +
                "    No7 [color=grey,fontcolor=grey,label=nil,shape=none];\n" +
                "    No2->No5 [color=grey,fontcolor=grey];\n" +
                "    No3->No6 [color=grey,fontcolor=grey];\n" +
                "    No3->No7 [color=grey,fontcolor=grey];\n" +
                "    No1->No2 [];\n" +
                "    No1->No3 [];\n" +
                "    No2->No4 [color=grey,fontcolor=grey];\n" +
                "}\n";
        assertEquals(expected, fileContent);
    }
}