package com.rsystems.noriel.jsoncsvconverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JsonCsvConverter {
    public static void JsonToCsv(String jsonString, File csvFile) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(jsonString);

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder
                .build()
                .withHeader();

        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(csvFile, jsonTree);

        File outputFile = new File(csvFile.toURI());
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        if (outputFile.length() != 0) {
            csvSchema = csvSchemaBuilder
                    .build()
                    .withoutHeader();
        }
        ObjectWriter writer = csvMapper.writer(csvSchema);
        OutputStream outstream = new FileOutputStream(outputFile , true);
        writer.writeValue(outstream,jsonTree);

//        CsvMapper csvMapper = new CsvMapper();
//        CsvSchema csvSchema = csvMapper
//                .schemaFor(UserActionTypeForCsv.class)
//                .withHeader();
//        csvMapper.addMixIn(UserActionType.class, UserActionTypeForCsv.class);
//
//        UserActionType[] orderLines = new ObjectMapper()
//                .readValue(jsonString, UserActionType[].class);
//        csvMapper.writerFor(UserActionType[].class)
//                .with(csvSchema)
//                .writeValue(csvFile, orderLines);

    }

}
