package com.example.rinor.familyplanning.utilities;

import com.example.rinor.familyplanning.model.Institution;
import com.example.rinor.familyplanning.model.InstitutionCategory;
import com.example.rinor.familyplanning.model.Language;
import com.example.rinor.familyplanning.model.LifeSituation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtil {

    private JsonUtil() {
    }

    public static List<InstitutionCategory> extractInstitutionsCategory(JSONObject responseObject) throws JSONException {

        List<InstitutionCategory> institutionCategoriesList = new ArrayList<>();

        JSONArray dataArray = responseObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject dataObj = dataArray.getJSONObject(i);

            int id = dataObj.getInt("ID");
//            int parentId = dataObj.getInt("ParentID");
            String categoryName = dataObj.getString("CategoryName");
//            String description = dataObj.getString("Description");
//            String icon = dataObj.getString("Icon");
//            String languageID = dataObj.getString("languageID");

            InstitutionCategory objInstitution = new InstitutionCategory(id, 0, categoryName,
                    "", "", "");

            institutionCategoriesList.add(objInstitution);
        }

        return institutionCategoriesList;
    }

    public static List<Institution> extractAllInstitutions(JSONObject responseObject) throws JSONException {

        List<Institution> institutionList = new ArrayList<>();

        JSONArray dataArray = responseObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject dataObj = dataArray.getJSONObject(i);

            int id = dataObj.optInt("ID");
            String name = dataObj.optString("InstitutionName");
            String description = dataObj.optString("Description");
            String image = dataObj.optString("Photo");
            String logo = dataObj.optString("Icon");
            double lat = dataObj.optDouble("Lat");
            double lng = dataObj.optInt("Lng");
            String website = dataObj.optString("Website");
            String services = dataObj.optString("Services");

            Institution institution = new Institution(id, name, description, image, logo, lat, lng, services, website);

            institutionList.add(institution);
        }

        return institutionList;
    }

    public static List<Language> extractAllLanguages(JSONObject responseObject) throws JSONException {

        List<Language> languagesList = new ArrayList<>();

        JSONArray dataArray = responseObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject dataObj = dataArray.getJSONObject(i);

            int id = dataObj.getInt("ID");
            String languageName = dataObj.getString("LanguageName");
            String languageIso = dataObj.getString("LanguageIso");
            String description = dataObj.getString("Description");
            String languageFlag = dataObj.getString("LanguageFlag");

            Language language = new Language(id, languageName, languageIso, description, languageFlag);

            languagesList.add(language);
        }

        return languagesList;
    }

    public static List<LifeSituation> extractAllLifeSituation(JSONObject responseObject) throws JSONException {

        List<LifeSituation> lifeSituationList = new ArrayList<>();

        JSONArray dataArray = responseObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {

            JSONObject dataObj = dataArray.getJSONObject(i);

            int id = dataObj.getInt("ID");
            String lifeSituationName = dataObj.getString("LifeSituationName");
            //String icon = dataObj.getString("Icon");
            int languageID = dataObj.getInt("LanguageID");
            //int institutionCategory = dataObj.getInt("InstitutionCategory");
            int statusi = dataObj.getInt("Statusi");

            LifeSituation lifeSituation = new LifeSituation(id, lifeSituationName, languageID, statusi);

            lifeSituationList.add(lifeSituation);
        }

        return lifeSituationList;
    }


}
