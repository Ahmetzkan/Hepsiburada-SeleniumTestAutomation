import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static Base.Data.requestBodyAhmetzkan;
import static Base.Data.requestBodyAhmetzkanAndAlizkan;

public class PetStoreTest {
    @Test
    @Description("Create a new user with provided details")
    @Step("Creating a user with username: ahmetzkan")
    public void CreateUser() {
        RestAssured.given().body(requestBodyAhmetzkan).when().contentType(ContentType.JSON).post("https://petstore.swagger.io/v2/user");
    }

    @Test
    @Description("Update an existing user with new details")
    @Step("Updating user with username: ahmetzkan")
    public void UpdateUser() {
        RestAssured.given().body(requestBodyAhmetzkan).when().contentType(ContentType.JSON).put("https://petstore.swagger.io/v2/user/ahmetzkan");
    }

    @Test
    @Description("Delete a user by username")
    @Step("Deleting user with username: alizkan")
    public void DeleteUser() {
        RestAssured.given().contentType(ContentType.JSON).when().delete("https://petstore.swagger.io/v2/user/alizkan");
    }

    @Test
    @Description("Create multiple users using an array")
    @Step("Creating users using an array")
    public void CreateUserWithArray() {
        RestAssured.given().body(requestBodyAhmetzkanAndAlizkan)
                .contentType(ContentType.JSON)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithArray")
                .then()
                .statusCode(200)
                .body("message", equalTo("ok"));
    }

    @Test
    @Description("Create multiple users using an list")
    @Step("Creating users using an list")
    public void CreateUserWithList() {
        RestAssured.given().body(requestBodyAhmetzkanAndAlizkan)
                .contentType(ContentType.JSON)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .statusCode(200)
                .body("message", equalTo("ok"));
    }

    @Test
    @Description("User information fetched")
    @Step("User with username: ahmetzkan")
    public void GetUser() {
        RestAssured.given().contentType(ContentType.JSON).when().get("https://petstore.swagger.io/v2/user/ahmetzkan");
    }

    @Test
    @Description("Login a user to the system")
    @Step("Logging in user with username: ahmetzkan")
    public void UserLogin() {
        Map<String,Object> queryParamsMap= new HashMap<>();
        queryParamsMap.put("username","ahmetzkan");
        queryParamsMap.put("password","ahmet");
        RestAssured.given().queryParams(queryParamsMap).contentType(ContentType.JSON).when().get("https://petstore.swagger.io/v2/user/login");
    }

    @Test
    @Description("Logout a user to the system")
    @Step("User is logout")
    public void UserLogout() {
        RestAssured.get("https://petstore.swagger.io/v2/user/logout");
    }
}
