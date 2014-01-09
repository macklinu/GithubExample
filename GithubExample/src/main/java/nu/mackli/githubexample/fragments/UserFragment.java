package nu.mackli.githubexample.fragments;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    @FragmentArg
    User user;

    @ViewById
    ImageView gravatar;

    @ViewById
    TextView name;

    @ViewById
    TextView login;

    @AfterViews
    public void afterViews() {
        name.setText(user.getName());
        login.setText(user.getLogin());
        Picasso.with(getActivity())
                .load(user.getAvatarUrl())
                .into(gravatar);
    }

}
