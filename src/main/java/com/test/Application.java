package com.test;

import com.test.csv.CSVReader;
import com.test.hierarchy.FeesHierarchyService;
import com.test.hierarchy.TreeNode;
import com.test.model.Item;

import java.math.BigDecimal;
import java.util.List;

import static com.test.util.PrintUtils.println;

public class Application {

    public static void main(String[] args) {
        println("----- Start -----");
        try {
            List<Item> items = new CSVReader().parseCSV();
            FeesHierarchyService hierarchyService = new FeesHierarchyService();
            TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
            hierarchyService.printHierarchy(feesHierarchyTree);
            TreeNode<String> treeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Human Resources");
            println("Found Node : " + treeNode.getName());
            hierarchyService.printHierarchy(treeNode);
            BigDecimal totalFees = hierarchyService.getTotalFees(treeNode, items);
            println("=====================================");
            println("TOTAL FEES = " + totalFees);
            println("=====================================");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
