package nu.mackli.githubexample.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import nu.mackli.githubexample.R;
import nu.mackli.githubexample.api.GithubRestApi;
import nu.mackli.githubexample.api.RestCallback;
import nu.mackli.githubexample.models.User;

/**
 * Created by macklinu on 1/9/14.
 */
@EFragment(R.layout.fragment_search)
public class SearchFragment extends Fragment {

    @Bean
    GithubRestApi api;

    @ViewById
    EditText searchInput;

    @ViewById
    ProgressBar progressBar;

    @Click
    void searchButton() {
        String searchTerm = searchInput.getText().toString();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            // make API call
            api.getUser(searchTerm, new RestCallback<User>() {
                @Override
                public void onBegin() {
                    setProgressBarVisible(ProgressBar.VISIBLE);
                }

                @Override
                public void onSuccess(User response) {
                    // show the current user
                    setProgressBarVisible(ProgressBar.GONE);
                    if (response.getMessage() == null) {
                        // good to go
                        makeToast(response.getName() + " is the name.");
                        // create the UserFragment
                        showUserFragment(response);
                    } else if (response.getMessage().equals("Not Found")) {
                        // then there was an error where the user was not found
                        makeToast("User not found");
                    }
                }

                @Override
                public void onError(HttpClientErrorException error) {
                    // show an error message saying something didn't go right
                    setProgressBarVisible(ProgressBar.GONE);
                    if (error.getStatusCode() == HttpStatus.NOT_FOUND) {
                        makeToast("Invalid username");
                    }
                }
            });
        }
    }

    @UiThread
    public void showUserFragment(User user) {
        // build a fragment with Android Annotations
        UserFragment userFragment = UserFragment_.builder()
                .user(user)
                .build();
        // create and complete the fragment transaction
        FragmentTransaction fragmentTransaction = getFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.container, userFragment)
                .addToBackStack(null)
                .commit();
    }

    @UiThread
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void setProgressBarVisible(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
