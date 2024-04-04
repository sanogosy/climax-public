package com.climax.fileprocess.utils;

import java.io.File;
import static com.climax.fileprocess.utils.CsvFormat.getExtensionByApacheCommonLib;

public class JsonFileFormat {

    public static boolean hasJSONFormatFromResource(File file) {
        String type = "json";
        if(!type.equals(getExtensionByApacheCommonLib(file.getName()))) return false;
        return true;
    }

    public static File uploadJsonFileFromResource(String fileName){
        File file = null;
        String clientfile = "../climax/" + fileName;

        file = new File(clientfile);

        if(hasJSONFormatFromResource(file)) {
            return file;
        }
        else{
            return null;
        }
    }

}
