package fr.soyhuce.pagination.models;

import com.android.volley.VolleyError;

/**
 * Created by mathieuedet on 04/05/2017.
 */

public class PaginateVolleyError extends VolleyError {
    private String link;

    public PaginateVolleyError(VolleyError error, String link){
        super(error);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
