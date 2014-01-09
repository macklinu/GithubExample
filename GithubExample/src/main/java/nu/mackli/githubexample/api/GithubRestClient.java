package nu.mackli.githubexample.api;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import nu.mackli.githubexample.models.User;

/**
 * Created by macklinu on 1/8/14.
 */
@Rest(rootUrl = "https://api.github.com", converters = {GsonHttpMessageConverter.class})
public interface GithubRestClient extends RestClientSupport {
    @Get("/users/{username}")
    public User getUser(String username);

}
