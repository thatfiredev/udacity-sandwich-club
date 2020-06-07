package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static List<String> parseStringArray(JSONArray jsonArray) throws JSONException {
        ArrayList<String> arrayElements  = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayElements.add(jsonArray.getString(i));
        }
        return arrayElements;
    }

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject("name");
            JSONArray alsoKnownAsJson = nameJson.getJSONArray("alsoKnownAs");
            JSONArray ingredientsJson = sandwichJson.getJSONArray("ingredients");

            String mainName = nameJson.getString("mainName");
            List<String> alsoKnownAs = parseStringArray(alsoKnownAsJson);
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            String image = sandwichJson.getString("image");
            List<String> ingredients = parseStringArray(ingredientsJson);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description,
                    image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
