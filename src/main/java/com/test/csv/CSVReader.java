package com.test.csv;

import com.test.model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    private final String SAMPLE_CSV_FILE_PATH = "/raw_fees.csv";

    public List<Item> parseCSV() throws IOException {

        String path = getClass().getResource(SAMPLE_CSV_FILE_PATH).getPath();
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVParser csvParser = new CSVParser(
            reader,
            CSVFormat.DEFAULT
                .withHeader(
                    "Id", "Name", "Description__c", "Department__c",
                    "Category__c", "Sub_Category__c", "Type__c",
                    "Quantity__c","Unit_Price__c"
                )
                .withIgnoreHeaderCase()
                .withTrim()
        );

        // Reading all records at once into memory
        List<CSVRecord> csvRecords = csvParser.getRecords();
        System.out.println("Size ::: " + csvRecords.size());
        List<Item> items = csvRecords.stream().skip(1).map(record -> buildItem(record)).collect(Collectors.toList());

        //items.forEach( e-> System.out.println(e.getName()));

        return items;
    }

    private Item buildItem(CSVRecord record){
        Item item = new Item();
        item.setId(record.get("Id"));
        item.setName(record.get("Name"));
        item.setDescription(record.get("Description__c"));
        item.setDepartment(new Department(record.get("Department__c")));
        item.setCategory(new Category(record.get("Category__c")));
        item.setSubCategory(new SubCategory(record.get("Sub_Category__c")));
        item.setType(new Type(record.get("Type__c")));
        item.setQuantity(Integer.parseInt(record.get("Quantity__c")));
        item.setUnitPrice(new BigDecimal(record.get("Unit_Price__c")));
        return item;
    }
}
