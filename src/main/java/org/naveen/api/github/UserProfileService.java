package org.naveen.api.github;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UserProfileService {
    public static HttpResponse getGithubUserProfile(String userName) throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get(userName));
        return response;
    }

    private static HttpGet get(String userName) throws URISyntaxException {
        URI callURI =
                new URIBuilder()
                .setScheme("https")
                .setHost("api.github.com")
                .setPathSegments("users",userName)
                .build();
        return new HttpGet(callURI);
    }
}
