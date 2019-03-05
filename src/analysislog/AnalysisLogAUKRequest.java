/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysislog;

import exception.ParseLogExeption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import object.key.TokenAppUser;
import object.log.ExInfo;
import object.log.LogAukReq;
import object.log.ResultTokenMerging;

/**
 *
 * @author root
 */
public class AnalysisLogAUKRequest {

    public static String statiscFile = "AnalysisLogAUKRequest-statisc.txt";

    private static long numsCreate = 0;
    private static long numsMerge = 0;
    private static long numsRelogin = 0;
    private static long totalTimeBetweenTwoLogin = 0;
    private static long numsUpdated = 0;
    private static long totalTimeBetweenTwoUpdate = 0;

    Map<TokenAppUser, Long> holderTokenAppUser = new Hashtable<>();
    Map<String, Long> holderToken = new Hashtable<>();

    String baseDir;
    String output;

    List<String> listFileNames = new LinkedList<>();

    public AnalysisLogAUKRequest(String baseDir, String output) throws FileNotFoundException {
        this.baseDir = baseDir;
        this.output = output;

        File folder = new File(baseDir);

        if (!folder.exists() || !folder.isDirectory()) {
            throw new java.io.FileNotFoundException("Cannot find folder:" + baseDir);
        }

        File[] files = folder.listFiles((d, s) -> {
            return s.toLowerCase().startsWith("gt-ppt-auk-req-2019");
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
            processFile(fileName);
        }
    }

    public boolean processFile(String fileName) throws FileNotFoundException, IOException {

        File fileInput = null;
        File fileOutput = null;
        File fileStatisc = null;

        BufferedReader br = null;
        BufferedWriter bw = null;
        
        long totalLine = 0;

        try {
            fileInput = new File(baseDir + "/" + fileName);

            fileOutput = new File(output);
            fileOutput.createNewFile();

            if (!fileOutput.exists()) {
                throw new FileNotFoundException("Cannot create file output AnalysisLogAUKRequest");
            }

            if (fileInput.exists() && fileInput.isFile()) {

                br = new BufferedReader(new FileReader(fileInput));
                bw = new BufferedWriter(new FileWriter(fileOutput));

                String line;

                LogAukReq logAukReq;

                while ((line = br.readLine()) != null) {
                    
                    totalLine += 1 ;
                    
                    try {
                        logAukReq = LogAukReq.parseToLogAukReq(line);

                        if (logAukReq.getAction().equals("getTAppUserToken")) {

                            // token create not equals 0 meant to this is creating
                            if (!logAukReq.getTokenPassing().equals("0")) {
                                numsCreate += 1;
                            } else {
                                // token can update or merge
                                if (logAukReq.getResultTokenMerging().getCode().equals("OK")) {

                                    String token2 = logAukReq.getResultTokenMerging().getToken2();

                                    // if token2 is greater than 0 meant to merging
                                    if (!token2.startsWith("-")) {
                                        numsMerge += 1;
                                        bw.write(logAukReq.toString());
                                        bw.write("\n");
                                    } else {
                                        // if token update
                                        if (holderToken.containsKey(logAukReq.getResultTokenMerging().getToken1())) {
                                            totalTimeBetweenTwoUpdate += (Long.valueOf(logAukReq.getTimestamp()) - holderToken.get(logAukReq.getResultTokenMerging().getToken1()));
                                            numsUpdated +=1;
                                        }
                                        
                                    }

                                }
                            }

                            TokenAppUser newTokenAppUser = new TokenAppUser(logAukReq.getReturnToken(),
                                    logAukReq.getAppID(),
                                    logAukReq.getUserID());

                            // if re-login
                            if (holderTokenAppUser.containsKey(newTokenAppUser)) {
                                numsRelogin += 1;
                                totalTimeBetweenTwoLogin += (Long.valueOf(logAukReq.getTimestamp()) - holderTokenAppUser.get(newTokenAppUser));
                            }

                            holderTokenAppUser.put(newTokenAppUser, Long.valueOf(logAukReq.getTimestamp()));
                            holderToken.put(logAukReq.getReturnToken(), Long.valueOf(logAukReq.getTimestamp()));
                        } else {
                            // this is unknown log
                            System.out.println(logAukReq);
                        }

                    } catch (ParseLogExeption ex) {

                    }
                }
                
                System.out.println("total line:"+totalLine);
                System.out.println("Numbers of create: " + AnalysisLogAUKRequest.numsCreate + "\n");
                System.out.println("Total time between two login: " + AnalysisLogAUKRequest.totalTimeBetweenTwoLogin + "\n");
                System.out.println("Numbers of re-loggin " + AnalysisLogAUKRequest.numsRelogin + "\n");
                System.out.println("Numbers of numsMerge: " + AnalysisLogAUKRequest.numsMerge + "\n");
                System.out.println("Numbers of numsUpdated: " + AnalysisLogAUKRequest.numsUpdated + "\n");
                System.out.println("Total time between two updating: " + AnalysisLogAUKRequest.totalTimeBetweenTwoUpdate + "\n");
                
                bw.flush();
                writeStatisc();
            }
        } catch (Exception ex) {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
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

            if (!fileStatisc.exists()) {
                throw new FileNotFoundException("Cannot create file:" + statiscFile);
            }

            wr = new BufferedWriter(new FileWriter(fileStatisc));

            wr.write("Numbers of create: " + AnalysisLogAUKRequest.numsCreate + "\n");
            wr.write("Total time between two login: " + AnalysisLogAUKRequest.totalTimeBetweenTwoLogin + "\n");
            wr.write("Numbers of re-loggin " + AnalysisLogAUKRequest.numsRelogin + "\n");
            wr.write("Numbers of numsMerge: " + AnalysisLogAUKRequest.numsMerge + "\n");
            wr.write("Numbers of numsUpdated: " + AnalysisLogAUKRequest.numsUpdated + "\n");
            wr.write("Total time between two updating: " + AnalysisLogAUKRequest.totalTimeBetweenTwoUpdate + "\n");
            

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
