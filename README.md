# PostsApp

This app was created with the purpose of being evaluated as Android developer candidate for Zemoga, taking into account the requirements sent by the recruiter
It was developed using an MVP architecure which allows to separate presentation layers from the logic. This helps the application to be more easily readable, extensible and maintainable.
The minimum API required for the app is 21, which runs on about 71% of the android devices in the world.

The details for the app will be listed below:
1. The app presents a Splash screen, which lasts as long as the service takes to return the Post list
2. For the splash screen if theres a network error, a dialog is shown and, when dismissed, the app will run in offline mode, reading from the Realm instance, if theres no stored data, empty states will be shown.
3. If the service responds successfully, the posts are saved to the realm instance and the splash screen is finished. The Lists view will be shown.
4. If there's data in realm the "All" posts fragment loads it from there, if not, it tries once more to fecth from service.
5. When we have the data, from realm or from the service, it is shown in the list, the first 20 posts will be marked as unread (per test requirements), if the data is fetched from service (assumption). If the data is loaded from Realm only, it'll not mark them as unread, since it's assumed that they're not new posts.
5. From the lists, you can delete a post by swiping, which removes it from the list and from Realm, also if it's marked as favorite and/or as read, it'll no longer be saved as so.
6. You can also mark a Post as favorite from the list item. This change can be seen immediately by tapping on the "Favorites" tab.
7. From any of both lists (All/Favorites) you can open a post's detail. this will mark the post as read.
8. In the detail, you can read the title and the whole content of the post. 
9. You can also see the information of the user who posted it and launch an email intent when you tap the user's email, or a browser intent when you tap the user's website
10. You can also see the comments list, which lets you know the title of the comment, its content and its author.
11. From the detail screen you can also mark/unmark a post as favorite.
12. When you return to the list, it's uptaded from Realm so that if you marked/unmarked the post, it'll be seen.
13. From the "All" posts section, if you tap the reload button, it'll fetch all post from the service again and update the Realm instance, marking the first 20 posts as not read.
14. From the "Favorites" posts section, if you tap the reload button, it reloads the favorite posts from Realm.
15. The "All" posts section features a "Delete all" button, which allows to remove all posts from the lists, and also from the database. leaving the screen on an Empty state view.
16. In case of network errors, alert dialogs are shown when fetching posts from service, in case of error while fetching user information or comment, only a toast is shown and no information is displayed.

For the app, the following libraries were used:
1. Butterknife -> It allows us to bind our views and resources in a more readable way, also keeping the code clean by avoiding the use of findViewById.
2. Retrofit -> Used to consume the required services.
3. Gson -> Used together with retrofit, so that it can serialize the responses into our defined objects.
4. Okhttp3 -> Used with retrofit to log the requests and responses, this is useful in the debugging process.
5. Dagger -> For dependency injection.
6. Lottie -> library used for animations.
7. The support libraries from android were also used as well as the kotlin libraries

