package fr.soyhuce.pagination.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by mathieuedet on 28/03/2017.
 */

public class Pagination {

    private static final String TAG = Pagination.class.getName();

    private int currentPage;
    private URL previousLink;
    private URL nextLink;

    public Pagination(JSONObject json) {
        try {

            JSONObject linksJSONObject = json.getJSONObject("links");
            String nextKey = "next";
            String previousKey = "prev";
            if(linksJSONObject.has(nextKey) && !linksJSONObject.isNull(nextKey)){
                nextLink = URI.create(linksJSONObject.getString(nextKey)).toURL();
            }

            if(linksJSONObject.has(previousKey) && !linksJSONObject.isNull(previousKey)){
                previousLink = URI.create(linksJSONObject.getString(previousKey)).toURL();
            }

            this.currentPage = json.getJSONObject("meta").getInt("current_page");

        } catch (JSONException | MalformedURLException e) {
            Log.e(TAG, e.getMessage(),e);
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }


    public URL getNextLink() {
        return nextLink;
    }

    public URL getPreviousLink() {
        return previousLink;
    }
}
