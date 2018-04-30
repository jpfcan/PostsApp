package com.juanpablofajardo.postsapp.navigators;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailNavigator {

    private static final String MAIL_TO_URI = "mailto:";

    private static final String HTTP_PREFIX = "http://";
    private static final String HTTPS_PREFIX = "https://";


    @Inject
    public PostDetailNavigator() {
    }

    public void launchEmailIntent(final Context context, final @NonNull String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(MAIL_TO_URI));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        context.startActivity(intent);
    }

    public void launchBrowserIntent(final Context context, @NonNull String website) {
        if (!website.contains(HTTP_PREFIX) && !website.contains(HTTPS_PREFIX)) {
            website = HTTP_PREFIX + website;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
        context.startActivity(intent);
    }

}
