package analysislog;

import exception.ParseLogExeption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.log.LogAukDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileInput, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class AnalysisLogAUKDB {
    
    public static String statiscFile = "AnalysisLogAUKDB-statisc.txt";

    private static long numsSetProfile = 0;
    private static long numsNotmatch = 0;

    String baseDir;
    String output;

    List<String> listFileNames = new LinkedList<>();

    public AnalysisLogAUKDB(String baseDir, String output) throws FileNotFoundException {
        this.baseDir = baseDir;
        this.output = output;

        File folder = new File(baseDir);

        if (!folder.exists() || !folder.isDirectory()) {
            throw new java.io.FileNotFoundException("Cannot find folder:" + baseDir);
        }

        File[] files = folder.listFiles((d, s) -> {
            return s.toLowerCase().startsWith("gt-ppt-auk-db-2019");
        });

        if (files.length <= 0) {
            throw new java.io.FileNotFoundException("Cannot find any file in folder:" + baseDir);
        } else {
            for (File file : files) {
                if (file.isFile()) {
                    listFileNames.add(file.getName());
                }
            }
        }
    }

    public void process() throws IOException, IOException {
        for (String fileName : listFileNames) {
            readFile(fileName);
        }
    }

    public boolean readFile(String fileName) throws FileNotFoundException, IOException {

        File fileInput = null;
        File fileOutput = null;
        File fileStatisc = null;

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            fileInput = new File(baseDir + "/" + fileName);
            
            fileOutput = new File(output);
            fileOutput.createNewFile();
            
            if (!fileOutput.exists()) {
                throw new FileNotFoundException("Cannot create file");
            }

            if (fileInput.exists() && fileInput.isFile()) {

                br = new BufferedReader(new FileReader(fileInput));
                bw = new BufferedWriter(new FileWriter(fileOutput));
                
                String line;

                LogAukDB logAukDB;

                while ((line = br.readLine()) != null) {

                    try {
                        logAukDB = LogAukDB.parseLogToLogAukDB(line);
                        
                        if (!logAukDB.getToken().equals(logAukDB.getProfile().getToken())) {
                            bw.write(logAukDB.toString());
                            bw.write("\n");
                            numsNotmatch += 1;
                        }
                        
                        if(logAukDB.getAction().equals("setProfile")){
                            numsSetProfile+=1;
                        }
                        
                    } catch (ParseLogExeption ex) {

                    }
                }
                
                bw.flush();
                writeStatisc();
            }
        } catch (Exception ex) {
            if (br != null) {
                br.close();
            }
            if(bw!=null){
                bw.close();
            }
        }
        return false;
    }

    private void writeStatisc() {
        
        File fileStatisc;

        BufferedWriter wr = null;

        try {
            fileStatisc = new File(statiscFile);
            
            fileStatisc.createNewFile();
            
            if(!fileStatisc.exists()){
                throw new FileNotFoundException("Cannot create file:" + statiscFile);
            }
            
            wr = new BufferedWriter(new FileWriter(fileStatisc));
            
            wr.write("Numbers of log not matching: "+AnalysisLogAUKDB.numsNotmatch+"\n");
            wr.write("Numbers of log insert or update: "+AnalysisLogAUKDB.numsSetProfile+"\n");
            
            wr.flush();
            
        } catch (Exception ex) {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException ex1) {

                }
            }
        }
    }
    
}
