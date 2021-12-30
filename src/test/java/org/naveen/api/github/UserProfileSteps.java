package org.naveen.api.github;

import org.apache.http.HttpResponse;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

public class UserProfileSteps {
    private String api;
    private String nonExistentUser;
    private HttpResponse response;
    private int githubResponseCode;

    @Given("github user profile api")
    public void givenGithubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    @Given("a random non-existent username")
    public void givenANonexistentUsername() {
        nonExistentUser = "abcd";
    }

    @When("I look for the random user via the api")
    public void whenILookForTheUserViaTheApi() throws IOException, URISyntaxException {
        response = UserProfileService.getGithubUserProfile(nonExistentUser);

    }

    @When("I look for $user via the api")
    public void whenILookForSomeNonExistentUserViaTheApi(
            String user) throws IOException, URISyntaxException {
        response = UserProfileService.getGithubUserProfile(user);

    }

    @Then("github respond: 404 not found")
    public void thenGithubRespond404NotFound() {
        githubResponseCode = getResponseCode(response);
        assertTrue(404 == githubResponseCode);
    }

    @Then("github respond: 200 success")
    public void thenGithubRespond200Succes(){
        githubResponseCode = getResponseCode(response);
        assertTrue(200 == githubResponseCode);
    }

    public int getResponseCode(HttpResponse response){
        return response.getStatusLine().getStatusCode();
    }


}
