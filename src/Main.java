
import analysislog.AnalysisLogAUKDB;
import analysislog.AnalysisLogAUKRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        AnalysisLogAUKRequest analysisLogAUKRequest = new AnalysisLogAUKRequest("/data/logs/gt-ppt-appusertoken-req", "log-merged");
        analysisLogAUKRequest.process();
        AnalysisLogAUKDB analysisLogAUKDB = new AnalysisLogAUKDB("/data/logs/gt-ppt-appusertoken-db", "log-not-matching");
        analysisLogAUKDB.process();
        
        
//        String jsonString = "{\"token\":1299130769502863360,\"tAppUserInfo\":[{\"appID\":\"kvm\",\"userID\":\"1298123358281441280\",\"channel\":\"zing\",\"socialID\":\"minhtri19932011\"}],\"updatedTs\":1548500456068,\"__isset_bitfield\":3}";
//        Profile profile = new Gson().fromJson(jsonString, Profile.class);
//        System.out.println(profile);
    }
}
