package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.ToDoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @Test(description = "Should be able to signup")
    public void shouldBeAbleToRegisterToTheApplication() {
        RegisterPage.getInstance().load(driver.get());

        User user = new User();

        RegisterPage.getInstance().register(driver.get(), user);

        Assert.assertTrue(ToDoPage.getInstance().isWelcomeMessageDisplayed(driver.get()));
    }
}