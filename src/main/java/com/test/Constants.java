package com.test;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String TYPE_DEPARTMENT = "Department";
    public static final String TYPE_CATEGORY = "Category";
    public static final String TYPE_SUBCATEGORY = "Sub Category";
    public static final String TYPE_TYPE = "Type";
    public static final String TAB = "    ";
    public static final String ROOT = "ROOT";

    public static final Map<String, Integer> DEPARTMENT_SURCHARGE;

    static {
        DEPARTMENT_SURCHARGE = new HashMap<>();
        DEPARTMENT_SURCHARGE.put("Marketing", 10);
        DEPARTMENT_SURCHARGE.put("Sales", 15);
        DEPARTMENT_SURCHARGE.put("Development", 20);
        DEPARTMENT_SURCHARGE.put("Operations", -15);
        DEPARTMENT_SURCHARGE.put("Support", -5);
    }
}
