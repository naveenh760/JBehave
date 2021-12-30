package org.naveen.api.github;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;

public class UserProfileService {
    public static HttpResponse getGithubUserProfile(URI callURI) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get(callURI));
        return response;
    }

    private static HttpGet get(URI callURI) {
        return new HttpGet(callURI);
    }
}
