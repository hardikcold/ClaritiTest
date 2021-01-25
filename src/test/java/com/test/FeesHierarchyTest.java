package com.test;

import com.test.csv.CSVReader;
import com.test.hierarchy.FeesHierarchyService;
import com.test.hierarchy.TreeNode;
import com.test.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class FeesHierarchyTest {

    List<Item> items;
    FeesHierarchyService hierarchyService;

    @BeforeEach
    void setUp() throws IOException {
        items = new CSVReader().parseCSV();
        hierarchyService = new FeesHierarchyService();
    }

    @RepeatedTest(1)
    public void buildFeeHierarchy() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        Assertions.assertNotNull(feesHierarchyTree);
        Assertions.assertEquals(feesHierarchyTree.getName(), "ROOT");
        String[] expectedDepartments = {"Support", "Development","Sales","Operations","Marketing"};
        String[] actualDepartments = feesHierarchyTree.getChildren().stream()
                .map( e-> e.getName()).collect(Collectors.toList()).toArray(new String[]{});
        Assertions.assertArrayEquals(expectedDepartments, actualDepartments);
    }

    @RepeatedTest(1)
    public void searchTreeNode_Support() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Support");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Support");
        String[] expectedCategories = {"Tier 1", "Tier 2","Tier 3"};
        String[] actualCategories = departmentTreeNode.getChildren().stream()
                .map( e-> e.getName()).sorted().collect(Collectors.toList()).toArray(new String[]{});
        Assertions.assertArrayEquals(expectedCategories, actualCategories);
    }

    @RepeatedTest(1)
    public void searchTreeNode_Marketing() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Marketing");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Marketing");
        String[] expectedCategories = {"ABM"};
        String[] actualCategories = departmentTreeNode.getChildren().stream()
                .map( e-> e.getName()).sorted().collect(Collectors.toList()).toArray(new String[]{});
        Assertions.assertArrayEquals(expectedCategories, actualCategories);
    }

    @RepeatedTest(1)
    public void searchTreeNode_Sales() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Sales");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Sales");
        String[] expectedCategories = {"Pre Sales", "Sales Engineering"};
        String[] actualCategories = departmentTreeNode.getChildren().stream()
                .map( e-> e.getName()).sorted().collect(Collectors.toList()).toArray(new String[]{});
        Assertions.assertArrayEquals(expectedCategories, actualCategories);
    }

    @RepeatedTest(1)
    public void searchTreeNode_Development() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Development");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Development");
        String[] expectedCategories = {"Coding", "Quality Assurance"};
        String[] actualCategories = departmentTreeNode.getChildren().stream()
                .map( e-> e.getName()).sorted().collect(Collectors.toList()).toArray(new String[]{});
        Assertions.assertArrayEquals(expectedCategories, actualCategories);
    }

    @RepeatedTest(1)
    public void searchTreeNode_Operations() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Operations");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Operations");
        String[] expectedCategories = {"Human Resources", "Performance Management"};
        String[] actualCategories = departmentTreeNode.getChildren().stream()
                .map( e-> e.getName()).sorted().collect(Collectors.toList()).toArray(new String[]{});
        Assertions.assertArrayEquals(expectedCategories, actualCategories);

    }

    @RepeatedTest(1)
    public void getTotalFees_Marketing() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Marketing");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Marketing");
        BigDecimal totalFees = hierarchyService.getTotalFees(departmentTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("619524.202"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Sales() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Sales");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Sales");
        BigDecimal totalFees = hierarchyService.getTotalFees(departmentTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("643947.652"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Development() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Development");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Development");
        BigDecimal totalFees = hierarchyService.getTotalFees(departmentTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("666826.26"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Operations() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Operations");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Operations");
        BigDecimal totalFees = hierarchyService.getTotalFees(departmentTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("467370.7745"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Support() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Support");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "Support");
        BigDecimal totalFees = hierarchyService.getTotalFees(departmentTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("516008.9445"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Marketing_ABM() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> departmentTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"ABM");
        Assertions.assertNotNull(departmentTreeNode);
        Assertions.assertEquals(departmentTreeNode.getName(), "ABM");
        BigDecimal totalFees = hierarchyService.getTotalFees(departmentTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("619524.202"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Sales_PreSales() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Pre Sales");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Pre Sales");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("329646.672"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Sales_SalesEngineering() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Sales Engineering");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Sales Engineering");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("314300.98"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Development_Coding() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Coding");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Coding");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("327203.748"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Development_QualityAssurance() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Quality Assurance");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Quality Assurance");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("339622.512"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Operations_HumanResources() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Human Resources");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Human Resources");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("229041.0255"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Operations_PerformanceManagement() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Performance Management");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Performance Management");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("238329.749"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Support_Tier1() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Tier 1");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Tier 1");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("179710.17"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Support_Tier2() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Tier 2");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Tier 2");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("167030.463"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Support_Tier3() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"Tier 3");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "Tier 3");
        BigDecimal totalFees = hierarchyService.getTotalFees(categoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("169268.3115"));
    }

    @RepeatedTest(1)
    public void getTotalFees_Marketing_ABM_Cat1() {
        TreeNode<String> feesHierarchyTree = hierarchyService.buildFeeHierarchy(items);
        TreeNode<String> categoryTreeNode = hierarchyService.searchTreeNode(feesHierarchyTree,"ABM");
        Assertions.assertNotNull(categoryTreeNode);
        Assertions.assertEquals(categoryTreeNode.getName(), "ABM");
        TreeNode<String> subCategoryTreeNode = hierarchyService.searchTreeNode(categoryTreeNode,"Cat1");
        Assertions.assertNotNull(subCategoryTreeNode);
        Assertions.assertEquals(subCategoryTreeNode.getName(), "Cat1");
        BigDecimal totalFees = hierarchyService.getTotalFees(subCategoryTreeNode, items);
        Assertions.assertEquals(totalFees, new BigDecimal("218216.262"));
    }
}
