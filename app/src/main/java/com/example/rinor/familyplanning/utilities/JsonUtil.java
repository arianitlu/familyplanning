package com.example.rinor.familyplanning.utilities;

import com.example.rinor.familyplanning.model.Institution;
import com.example.rinor.familyplanning.model.InstitutionCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtil {

    private JsonUtil(){
    }

    public static List<InstitutionCategory> extractInstitutionsCategory(JSONObject responseObject) throws JSONException {

        List<InstitutionCategory> institutionCategoriesList = new ArrayList<>();

        JSONArray dataArray = responseObject.getJSONArray("data");

        for(int i = 0; i < dataArray.length(); i++){

            JSONObject dataObj = dataArray.getJSONObject(i);

            int id = dataObj.getInt("ID");
//            int parentId = dataObj.getInt("ParentID");
            String categoryName = dataObj.getString("CategoryName");
//            String description = dataObj.getString("Description");
//            String icon = dataObj.getString("Icon");
//            String languageID = dataObj.getString("languageID");

            InstitutionCategory objInstitution = new InstitutionCategory(id,0,categoryName,
                    "","","");

            institutionCategoriesList.add(objInstitution);
        }

        return institutionCategoriesList;
    }

    public static List<Institution> extractInstitutionsByCategory(JSONObject responseObject) throws JSONException {

        List<Institution> institutionList = new ArrayList<>();

        JSONArray dataArray = responseObject.getJSONArray("data");

        for(int i = 0; i < dataArray.length(); i++){

            JSONObject dataObj = dataArray.getJSONObject(i);

            int id = dataObj.getInt("ID");
            String name = dataObj.getString("InstitutionName");
            String description = dataObj.getString("Description");
            String image = dataObj.getString("Photo");
            String logo = dataObj.getString("Icon");
            int lat = dataObj.getInt("Lat");
            int lng = dataObj.getInt("Lng");
            String website = dataObj.getString("Website");
            String services = dataObj.getString("Services");

            Institution institution = new Institution(id,name,description,image,logo,lat,lng,services,website);

            institutionList.add(institution);
        }

        return institutionList;
    }
}
