package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.NewToDoPage;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.ToDoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ToDoTests extends BaseTest {

    @Test(description = "Should be able to add new add to do")
    public void shouldBeAbleToAddToDo() {
        User user = new User();

        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsingApi(driver.get(), user);

        ToDoPage.getInstance().clickOnAddToDoButton(driver.get());
        NewToDoPage.getInstance().addToDo(driver.get(), "Learn German");
        Assert.assertEquals(ToDoPage.getInstance().getToDoText(driver.get()), "Learn German");
    }

    @Test(description = "Should be able to remove a to do")
    public void shouldBeAbleToDeleteAToDo() {
        User user = new User();

        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsingApi(driver.get(), user);
        NewToDoPage.getInstance().addToDoUsingApi(user, "Learn German");
        ToDoPage.getInstance().load(driver.get());
        ToDoPage.getInstance().clickOnDeleteButton(driver.get());

        Assert.assertTrue(ToDoPage.getInstance().isNoToDoMessageDisplayed(driver.get()));
    }
}