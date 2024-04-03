package com.climax.fileprocess.utils;

import com.climax.fileprocess.domain.dto.ClientDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CsvFormat {

    public boolean hasCSVFormat(MultipartFile file) {
        String type = "text/csv";
        if(!type.equals(file.getContentType())) return false;
        return true;
    }

    public static boolean hasCSVFormatFromResource(File file) {
        String type = "csv";
        if(!type.equals(getExtensionByApacheCommonLib(file.getName()))) return false;
        return true;
    }

    public static String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public static File uploadCsvFileFromResource(String fileName){
        File file = null;
        String clientfile = "../climax/" + fileName;

        file = new File(clientfile);

        if(hasCSVFormatFromResource(file)) {
            return file;
        }
        else{
            return null;
        }
    }
}
