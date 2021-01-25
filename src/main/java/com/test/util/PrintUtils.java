package com.test.util;

import com.test.Constants;
import com.test.hierarchy.TreeNode;

public class PrintUtils {

    public static void printTreeNode(String tabString, TreeNode<?> treeNode){
        treeNode.getChildren().forEach( child -> {
            println(tabString +  child.getName() + " (" + child.getType() + ") / " + child.getParentNode().getName());
            printTreeNode(tabString + Constants.TAB, child);
        });
    }

    public static void println(Object msg){
        System.out.println(msg);
    }

    public static void print(Object msg){
        System.out.print(msg);
    }
}
