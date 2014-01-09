package nu.mackli.githubexample.activities;

import android.app.Activity;
import android.app.FragmentTransaction;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import nu.mackli.githubexample.R;
import nu.mackli.githubexample.fragments.UserFragment;
import nu.mackli.githubexample.fragments.UserFragment_;
import nu.mackli.githubexample.models.User;

/**
 * Created by macklinu on 1/9/14.
 */
@EActivity(R.layout.activity_user)
public class UserActivity extends Activity {

    @Extra
    User user;

    @AfterViews
    public void onAfterViews() {
        UserFragment userFragment = UserFragment_.builder()
                .user(user)
                .build();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, userFragment)
                .commit();
    }
}
