package nu.mackli.githubexample.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;

import nu.mackli.githubexample.models.User;

/**
 * Created by macklinu on 1/8/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class GithubRestApi {
    @RestService
    GithubRestClient client;

    @AfterInject
    public void setup() {
        setGsonConversionOptions();
    }

    @Background
    public void getUser(String username, RestCallback<User> callback) {
        callback.onBegin();
        try {
            User user = client.getUser(username);
            callback.onSuccess(user);
        } catch (HttpClientErrorException e) {
            callback.onError(e);
        }
    }

    /**
     * Convert API JSON variables with underscores to camelCase
     * Example:
     * <code>gravatar_id</code> becomes <code>gravatarId</code>
     */
    private void setGsonConversionOptions() {
        GsonHttpMessageConverter converter =
                (GsonHttpMessageConverter) client.getRestTemplate()
                .getMessageConverters()
                .get(0);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        converter.setGson(gson);
    }
}
