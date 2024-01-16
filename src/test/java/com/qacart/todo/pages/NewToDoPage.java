package com.qacart.todo.pages;

import com.qacart.todo.apis.ToDoApi;
import com.qacart.todo.models.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewToDoPage {

    private static NewToDoPage newToDoPage;
    private final By newToDoInput = By.cssSelector("[data-testid=\"new-todo\"]");
    private final By submitButton = By.cssSelector("[data-testid=\"submit-newTask\"]");

    //Constructors
    private NewToDoPage() {}

    public static NewToDoPage getInstance() {
        if(newToDoPage == null){
            return new NewToDoPage();
        }
        return newToDoPage;
    }

    @Step("Add to do using the UI")
    public void addToDo(WebDriver driver, String toDoName) {
        driver.findElement(newToDoInput).sendKeys(toDoName);
        driver.findElement(submitButton).click();
    }

    @Step("Add to do using the API")
    public void addToDoUsingApi(User user, String toDoName) {
        ToDoApi.getInstance().addToDo(user, toDoName);
    }
}