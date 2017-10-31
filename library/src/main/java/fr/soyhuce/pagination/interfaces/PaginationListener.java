package fr.soyhuce.pagination.interfaces;

import android.support.annotation.NonNull;

import java.net.URL;

/**
 * Created by mathieuedet on 03/05/2017.
 */

public interface PaginationListener {
    /***
     * Methode appeler lorsque l'utilisateur sera en bas d'une liste et pas à la dernière page
     * @param nextLink Lien vers les résultats de la page suivante
     */
    void onBottomPage(@NonNull URL nextLink);
}
