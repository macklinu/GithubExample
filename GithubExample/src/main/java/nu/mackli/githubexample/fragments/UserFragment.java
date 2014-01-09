package nu.mackli.githubexample.fragments;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import nu.mackli.githubexample.R;
import nu.mackli.githubexample.models.User;

/**
 * Created by macklinu on 1/9/14.
 */
@EFragment(R.layout.fragment_user)
public class UserFragment extends Fragment {

    private static final int GRAVATAR_SIZE = 400;

    @FragmentArg
    User user;

    @ViewById
    ImageView gravatar;

    @ViewById
    TextView name;

    @ViewById
    TextView login;

    private String gravatarUrl;

    /**
     * Compose a gravatar URL to retrieve a specific gravatar image size
     */
    @AfterInject
    public void afterInject() {
        gravatarUrl = String.format("http://www.gravatar.com/avatar/%s?s=%s", // base URL
                user.getGravatarId(), // first %s
                GRAVATAR_SIZE); // second %s
    }

    @AfterViews
    public void afterViews() {
        name.setText(user.getName() == null ? "no name" : user.getName());
        login.setText(user.getLogin());
        Picasso.with(getActivity())
                .load(gravatarUrl)
                .into(gravatar);
    }

}
