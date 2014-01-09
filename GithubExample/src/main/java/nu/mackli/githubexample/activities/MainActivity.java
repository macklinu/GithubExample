package nu.mackli.githubexample.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import nu.mackli.githubexample.R;
import nu.mackli.githubexample.fragments.SearchFragment;
import nu.mackli.githubexample.fragments.SearchFragment_;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @ViewById
    FrameLayout container;

    @ViewById
    ProgressBar progressBar;

    @AfterViews
    public void afterViews() {
        setFragment();
    }

    private void setFragment() {
        SearchFragment searchFragment = new SearchFragment_();

        FragmentTransaction fragmentTransaction = getFragmentManager()
                .beginTransaction();
        fragmentTransaction
                .replace(container.getId(), searchFragment)
                .commit();
    }

}
