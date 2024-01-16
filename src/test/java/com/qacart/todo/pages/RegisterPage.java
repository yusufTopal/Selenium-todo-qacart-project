package com.qacart.todo.pages;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private static RegisterPage registerPage;

    private final By firstNameInput = By.cssSelector("[data-testid=\"first-name\"]");
    private final By lastNameInput = By.cssSelector("[data-testid=\"last-name\"]");
    private final By emailInput = By.cssSelector("[data-testid=\"email\"]");
    private final By passwordInput = By.cssSelector("[data-testid=\"password\"]");
    private final By confirmPasswordInput = By.cssSelector("[data-testid=\"confirm-password\"]");
    private final By submitButton = By.cssSelector("[data-testid=\"submit\"]");

    private RegisterPage() {}

    public static RegisterPage getInstance() {
        if(registerPage == null){
            registerPage = new RegisterPage();
        }
        return registerPage;
    }

    @Step("Visit the sign up page")
    public void load(WebDriver driver) {
       driver.get(ConfigUtils.getInstance().getBaseUrl() +"/signup");
    }

    @Step("Register using the UI")
    public void register(WebDriver driver, User user) {
        driver.findElement(firstNameInput).sendKeys(user.getFirstName());
        driver.findElement(lastNameInput).sendKeys(user.getLastName());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(confirmPasswordInput).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }

    @Step("Register using API")
    public void registerUsingApi(WebDriver driver, User user) {
        Response response = UserApi.getInstance().registerUser(user);

        String accessToken = response.path("access_token");
        String userID = response.path("userID");
        String firstName = response.path("firstName");

        user.setAccessToken(accessToken);

        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        Cookie userIdCookie = new Cookie("userID", userID);
        Cookie firstNameCookie = new Cookie("firstName", firstName);

        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIdCookie);
        driver.manage().addCookie(firstNameCookie);
        RegisterPage.getInstance().load(driver);
    }
}