package nu.mackli.githubexample.api;

import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by macklinu on 1/8/14.
 */
public interface RestCallback<T> {
    public void onBegin();
    public void onSuccess(T response);
    public void onError(HttpClientErrorException error);

}
