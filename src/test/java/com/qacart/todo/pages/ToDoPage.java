package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToDoPage {

    private static ToDoPage toDoPage;

    private final By welcomeMessage = By.cssSelector("[data-testid=\"welcome\"]");
    private final By addToDoButton = By.cssSelector("[data-testid=\"add\"]");
    private final By toDoItem = By.cssSelector("[data-testid=\"todo-item\"]");
    private final By deleteIcon = By.cssSelector("[data-testid=\"delete\"]");
    private final By noToDosMessage = By.cssSelector("[data-testid=\"no-todos\"]");

    private ToDoPage() {}

    public static ToDoPage getInstance() {
        if(toDoPage == null){
            return new ToDoPage();
        }
        return toDoPage;
    }

    @Step("Check if welcome message displayed")
    public boolean isWelcomeMessageDisplayed(WebDriver driver) {
       return driver.findElement(welcomeMessage).isDisplayed();
    }

    @Step("Click on the plus method")
    public void clickOnAddToDoButton(WebDriver driver) {
        driver.findElement(addToDoButton).click();
    }

    @Step("Click on the delete icon")
    public void clickOnDeleteButton(WebDriver driver) {
        driver.findElement(deleteIcon).click();
    }

    @Step("Get the text of to do")
    public String getToDoText(WebDriver driver) {
        return driver.findElement(toDoItem).getText();
    }

    @Step("Check if no to do message is displayed")
    public boolean isNoToDoMessageDisplayed(WebDriver driver) {
        return driver.findElement(noToDosMessage).isDisplayed();
    }

    @Step("Visiting the to do page")
    public void load(WebDriver driver) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/todo" );
    }
}