package org.naveen.api.github;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class UserProfileSteps {
    private String hostName;
    private String userName;
    private HttpResponse response;
    private int githubResponseCode;

    @Given("github user profile api host name $host")
    public void givenGithubUserProfileApi(String host) {
        hostName = host;
    }

    @Given("a random non-existent username")
    public void givenANonexistentUsername() {
        userName = "abcd";
    }

    @When("I look for the random user via the api")
    public void whenILookForTheUserViaTheApi() throws IOException, URISyntaxException {
        URI callURI = getUri(userName);
        response = UserProfileService.getGithubUserProfile(callURI);

    }

    @When("I look for $user via the api")
    public void whenILookForSomeNonExistentUserViaTheApi(
            String user) throws IOException, URISyntaxException {
        userName = user;
        URI callURI = getUri(userName);
        response = UserProfileService.getGithubUserProfile(callURI);

    }

    @Then("github respond: 404 not found")
    public void thenGithubRespond404NotFound() {
        githubResponseCode = getResponseCode(response);
        assertEquals(404, githubResponseCode);
    }

    @Then("github respond: 200 success")
    public void thenGithubRespond200Succes(){
        githubResponseCode = getResponseCode(response);
        assertEquals(200, githubResponseCode);
    }

    public int getResponseCode(HttpResponse response){
        return response.getStatusLine().getStatusCode();
    }

    private URI getUri(String userName) throws URISyntaxException {
        URI callURI =
                new URIBuilder()
                        .setScheme("https")
                        .setHost(hostName)
                        .setPathSegments("users", userName)
                        .build();
        return callURI;
    }


}
