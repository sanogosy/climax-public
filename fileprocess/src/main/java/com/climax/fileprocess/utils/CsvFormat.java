package com.climax.fileprocess.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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

    public static boolean hasTXTFormatFromResource(File file) {
        String type = "txt";
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

    public static File uploadTxtFileFromResource(String fileName){
        File file = null;
        String clientfile = "../climax/" + fileName;

        file = new File(clientfile);

        if(hasTXTFormatFromResource(file)) {
            return file;
        }
        else{
            return null;
        }
    }
}
