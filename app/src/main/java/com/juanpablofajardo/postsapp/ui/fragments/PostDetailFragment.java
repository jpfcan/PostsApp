package com.juanpablofajardo.postsapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.app.AppManager;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.presenters.detail.PostDetailPresenter;
import com.juanpablofajardo.postsapp.ui.BaseFragment;
import com.juanpablofajardo.postsapp.ui.adapters.detail.PostDetailAdapter;
import com.juanpablofajardo.postsapp.ui.view_interfaces.PostDetailView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailFragment extends BaseFragment implements PostDetailView {

    public static final String POST_KEY = "post";

    public static PostDetailFragment newInstance(final Post post) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(POST_KEY, post);

        PostDetailFragment postDetailFragment = new PostDetailFragment();
        postDetailFragment.setArguments(bundle);
        return postDetailFragment;
    }

    @BindView(R.id.post_detail_content)
    protected RecyclerView postDetailContent;

    private MenuItem menuItemFavorite;

    @Inject
    protected PostDetailPresenter presenter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(POST_KEY) && presenter != null) {
            presenter.setView(this);
            presenter.setPost(arguments.getParcelable(POST_KEY));
            presenter.setupPostDetailDelegateAdapter();
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_post_detail, menu);

        menuItemFavorite = menu.findItem(R.id.menu_item_favorite);
        if (presenter != null) {
            menuItemFavorite.setVisible(presenter.getShouldShowFavorite());
            if (menuItemFavorite.isVisible()) {
                menuItemFavorite.setIcon(presenter.getFavoriteStateIcon());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_favorite:
                if (presenter != null) {
                    presenter.onFavoriteItemAction();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDetailAdapter(final PostDetailAdapter detailAdapter) {
        if (isAdded()) {
            postDetailContent.setLayoutManager(new LinearLayoutManager(getActivity()));
            postDetailContent.setAdapter(detailAdapter);
        }
    }

    @Override
    public void refreshOptionsMenu() {
        if (isAdded()) {
            getActivity().invalidateOptionsMenu();
        }
    }

    @Override
    public void showMessageToast(@NonNull final String message) {
        showToastMessage(message);
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
        if (presenter != null) {
            presenter.setView(null);
        }
    }

    @Override
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }

    @Override
    protected void injectDependencies() {
        AppManager.DAGGER_COMPONENT.inject(this);
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_post_detail;
    }
}
