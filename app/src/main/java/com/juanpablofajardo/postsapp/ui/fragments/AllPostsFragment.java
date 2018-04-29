package com.juanpablofajardo.postsapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.app.AppManager;
import com.juanpablofajardo.postsapp.presenters.AllPostsPresenter;
import com.juanpablofajardo.postsapp.ui.BaseFragment;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;
import com.juanpablofajardo.postsapp.ui.view_interfaces.AllPostsView;
import com.juanpablofajardo.postsapp.utils.PagerFragmentLifeCycle;
import com.juanpablofajardo.postsapp.utils.PostItemTouchHelper;
import com.juanpablofajardo.postsapp.utils.PostItemTouchHelper.PostItemTouchHelperListener;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class AllPostsFragment extends BaseFragment implements AllPostsView, PagerFragmentLifeCycle, PostItemTouchHelperListener {


    @BindView(R.id.posts_recycler_view)
    protected RecyclerView allPostRecyclerView;


    @Inject
    protected AllPostsPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.DAGGER_COMPONENT.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);
        presenter.fetchAllPostsFromDB();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_fragment, menu);
        menu.findItem(R.id.menu_reload).setVisible(presenter.getShouldShowReload());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reload:
                presenter.fetchAllPostFromService();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupRecyclerView(final PostsAdapter adapter) {
        if (isAdded()) {
            allPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            allPostRecyclerView.setAdapter(adapter);
            SimpleCallback touchHelperCallBack = new PostItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
            new ItemTouchHelper(touchHelperCallBack).attachToRecyclerView(allPostRecyclerView);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        presenter.removeItem(position);
    }

    @Override
    public void refreshOptionsMenu() {
        if (isAdded()) {
            getActivity().invalidateOptionsMenu();
        }
    }

    @Override
    public void onResumeFragment() {
        //NO-OP
    }

    @Override
    public void showMessageToast(@NonNull String message) {
        if (isAdded()) {
            showToastMessage(message);
        }
    }

    @Override
    public void showLoading() {
        if (isAdded() && loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (isAdded() && loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void destroyView() {
        presenter.setView(null);
    }

    @Override
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_post_list;
    }
}