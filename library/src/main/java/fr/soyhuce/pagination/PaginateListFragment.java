package fr.soyhuce.pagination;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.List;

import fr.soyhuce.pagination.interfaces.PaginateResponseListener;
import fr.soyhuce.pagination.interfaces.PaginationListener;
import fr.soyhuce.pagination.models.PaginateVolleyError;
import fr.soyhuce.pagination.models.Pagination;

/**
 * Created by SoyHuCe on 03/05/2017.
 */

public abstract class PaginateListFragment<T> extends ListFragment implements AbsListView.OnScrollListener, PaginateResponseListener<T>, SwipeRefreshLayout.OnRefreshListener, PaginationListener {

    protected Pagination pagination;
    protected View view;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected Context context;

    private int preLast = -1;
    private PaginationListener paginationListener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, PaginationListener paginationListener, View view, int swipeRefreshLayoutId) {
        this.paginationListener = paginationListener;
        this.context = container.getContext();
        this.view = view;
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(swipeRefreshLayoutId);
        this.swipeRefreshLayout.setOnRefreshListener(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) { /*onScrollStateChanged*/ }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(pagination == null){
            return;
        }

        final int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem == totalItemCount && preLast != lastItem) { //Pour éviter plusieurs appels du dernier item
            if (pagination.getNextLink() != null && paginationListener != null) {
                paginationListener.onBottomPage(pagination.getNextLink());
            }
            preLast = lastItem;
        }
    }

    public void onResponse(List<T> items, Pagination pagination, PaginateVolleyError error, PaginateResponseListener listener){
        swipeRefreshLayout.setRefreshing(false);
        if(error != null){
            listener.onError(error);
            return;
        }
        this.pagination = pagination;
        if(pagination.getCurrentPage() == 1){
            preLast = -1;
        }
        listener.onResponse(items);

    }
}
