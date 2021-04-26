package com.rsystems.noriel.userrecomandations;

import com.rsystems.noriel.jsoncsvconverter.CsvSearch;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UserRecommendationsDAOService {

    public static final int INDEX_PRODUCT_ID = 1;
    public static final int ACTION_TYPE = 0;
    private static List<String[]> recommendations;
    private static List<String[]> userActions;

    
    public String getUserProdIDs (String userId, String fileLocalPath) {
    	String userprodIDs = new String();
    	 
    	recommendations = CsvSearch.searchCsv(new File(fileLocalPath), userId, "PartnerId", false);
        AtomicReference<List<String[]>> similarProductIds = new AtomicReference<>();
        List firstElem = Arrays.asList(recommendations.get(0));
        userprodIDs = (String) firstElem.get(INDEX_PRODUCT_ID);
    	System.out.println("IDN PROFILE " + userprodIDs);
    	
    	return userprodIDs;
    }
    
    public String getRecommendationsByUser(String emailHash) {
    	
    	
    	 //blobClient.downloadToFile("/Users/marius.stancalie/Downloads/user_based_item_recommendation1.csv");
        recommendations = CsvSearch.searchCsv(new File("/Users/marius.stancalie/Downloads/user_based_item_recommendation.csv"), emailHash, "PartnerId", false);
        // recommendations.forEach(s -> System.out.println(Arrays.toString((String[]) s)));
        
        
        AtomicReference<List<String[]>> similarProductIds = new AtomicReference<>();
        List firstElem = Arrays.asList(recommendations.get(0));
        String recomendIds = (String) firstElem.get(INDEX_PRODUCT_ID);
        //System.out.println("lista = " + recomendIds);
        userActions = CsvSearch.searchCsv(new File("/Users/marius.stancalie/Downloads/aaa.csv"), emailHash, "emailHash", true);
//        List<String[]> userActionsFiltered = userActions.stream().filter(c -> {
//            try {
//                Date xxx = new SimpleDateFormat("yyyy-MM-dd").parse(c[3]);
//                long currenttime = System.currentTimeMillis() - 15 * 60 * 1000;
//                return xxx.getTime() > currenttime;
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }).collect(Collectors.toList());

        userActions.forEach(s -> {
            Date actionTimestamp = null;
            try {
                actionTimestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(s[3]);

                long lastPeriodMinTimestamp = System.currentTimeMillis() - 15 * 60 * 1000;
                if (actionTimestamp.getTime() > lastPeriodMinTimestamp) {
                    switch (s[ACTION_TYPE]) {
                        case "addToCart":
                            similarProductIds.set(CsvSearch.searchCsv(new File("/Users/marius.stancalie/Downloads/item_based_recommendation.csv"), s[2], "ItemId", false));

                        case "viewProduct": {break;}
                        case "removeFromCart": {break;}


                    }
                }
                System.out.println(Arrays.toString((String[]) s));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });


        return recomendIds;
    }

}
