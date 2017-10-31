package fr.soyhuce.pagination.interfaces;

import android.support.annotation.NonNull;

import java.util.List;

import fr.soyhuce.pagination.models.PaginateVolleyError;

/**
 * Created by mathieuedet on 04/05/2017.
 */

public interface PaginateResponseListener<T> {
    void onResponse(@NonNull List<T> items);
    void onError(@NonNull PaginateVolleyError error);
}
