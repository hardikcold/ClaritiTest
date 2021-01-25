package com.test.hierarchy;

import com.test.Constants;
import com.test.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.test.util.PrintUtils.printTreeNode;
import static com.test.util.PrintUtils.println;

public class FeesHierarchyService {

    public TreeNode<String> buildFeeHierarchy(List<Item> items){
        println("======= Building Hierarchy : START =======");
        TreeNode<String> rootNode = new TreeNode<>(Constants.ROOT, null);
        items.forEach( item -> {
            TreeNode<String> departmentNode = rootNode.addChild(item.getDepartment().getName(), Constants.TYPE_DEPARTMENT);
            TreeNode<String> categoryNode = departmentNode.addChild(item.getCategory().getName(), Constants.TYPE_CATEGORY);
            TreeNode<String> subCategoryNode = categoryNode.addChild(item.getSubCategory().getName(), Constants.TYPE_SUBCATEGORY);
            subCategoryNode.addChild(item.getType().getName(), Constants.TYPE_TYPE);
        });
        println("======= Building Hierarchy : END =======\n\n\n");
        return rootNode;
    }

    public TreeNode<String> searchTreeNode(TreeNode<String> rootNode, String name){
        TreeNode<String> foundTreeNode = search(rootNode, name, null);
        println("foundTreeNode >>> " + foundTreeNode.getName());
        return foundTreeNode;
    }

    private TreeNode<String> search(TreeNode<String> treeNode, String name, TreeNode<String> foundTreeNode){
        if(!Objects.isNull(foundTreeNode))
            return foundTreeNode;

        if(treeNode.getName().equals(name)){
            foundTreeNode = treeNode;
            return foundTreeNode;
        }

        for (TreeNode<String> treeNode1 : treeNode.getChildren()){
            println(">>> " + treeNode1.getName() + " | " + treeNode1.getType());
            foundTreeNode = search(treeNode1, name, foundTreeNode);
        }

        return foundTreeNode;
    }

    public BigDecimal getTotalFees(TreeNode<String> treeNode, List<Item> items){
        List<String> feesHierarchyList = getFeesHierarchyList(treeNode, "", new ArrayList<>());
        final BigDecimal[] totalFees = {BigDecimal.ZERO};
        final String feesHierarchyPrefix = treeNode.getType().equals(Constants.TYPE_SUBCATEGORY) ?
            treeNode.getParentNode().getParentNode().getName() + "/" + treeNode.getParentNode().getName() + "/" : "";

        feesHierarchyList.forEach( feesHierarchy -> {
            println(">>>>>>> " + (feesHierarchyPrefix +feesHierarchy));
            totalFees[0] = totalFees[0].add(
                items.stream().filter(e->
                    e.getFeesHierarchy().endsWith(feesHierarchyPrefix + feesHierarchy)
                ).map(Item::getTotalFee).reduce(BigDecimal.ZERO, BigDecimal::add)
            );
        });
        println("Total Fees ::::: " + totalFees[0]);
        Integer surCharge = 0;
        String departmentName = "";
        if(treeNode.getType().equals(Constants.TYPE_DEPARTMENT)){
            departmentName = treeNode.getName();
        } else if(treeNode.getType().equals(Constants.TYPE_CATEGORY)){
            departmentName = treeNode.getParentNode().getName();
        } else if(treeNode.getType().equals(Constants.TYPE_SUBCATEGORY)){
            departmentName = treeNode.getParentNode().getParentNode().getName();
        }
        surCharge = Constants.DEPARTMENT_SURCHARGE.get(departmentName);
        println(departmentName + " surCharge % ::::: " + surCharge);
        BigDecimal surchargeOnTotalFees = totalFees[0].multiply(BigDecimal.valueOf(surCharge))
            .divide(BigDecimal.valueOf(100));
        println(departmentName + " surchargeOnTotalFees ::::: " + surchargeOnTotalFees);
        BigDecimal finalFee = totalFees[0].add(surchargeOnTotalFees);
        println(departmentName + " finalFee ::::: " + finalFee);

        return finalFee;
    }

    private List<String> getFeesHierarchyList(TreeNode<String> treeNode, String navigation, List<String> feesHierarchyList){
        if(navigation.isEmpty()) {
            navigation = treeNode.getName();
        }
        for(TreeNode treeNode1 : treeNode.getChildren()){
            getFeesHierarchyList(treeNode1, navigation + "/" + treeNode1.getName(), feesHierarchyList);
        }
        if(treeNode.isLeaf())
            feesHierarchyList.add(navigation);
        return feesHierarchyList;
    }

    public void printHierarchy(TreeNode<?> treeNode){
        printTreeNode(Constants.TAB, treeNode);
    }

}
