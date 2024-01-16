package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ToDoApi {

    private static ToDoApi toDoApi;

    private ToDoApi() {}

    public static ToDoApi getInstance() {
        if (toDoApi == null) {
            toDoApi = new ToDoApi();
        }
        return toDoApi;
    }

    public Response addToDo(User user, String newToDoName) {
        return RestAssured
                .given()
                .log().all()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType(ContentType.JSON)
                .body("{ \"item\": \" "+ newToDoName +"\", \"isCompleted\": false }")
                .auth().oauth2(user.getAccessToken())
                .when()
                .post("api/v1/tasks")
                .then()
                .extract()
                .response();
    }
}