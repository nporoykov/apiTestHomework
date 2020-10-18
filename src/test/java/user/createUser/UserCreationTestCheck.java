package user.createUser;

import dto.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.UserService;

import static org.hamcrest.Matchers.*;

public class UserCreationTestCheck {

    @Test(priority = 0)
    public void creationUserCheck1() {
        creationUserCheck("qwerty@ya.ru", "qwerty", 10L, "qwerty", "qwerty123", "111-111-111", "qwerty");
    }

    @Test(priority = 0)
    public void creationUserCheck2() {
        creationUserCheck("asdfgh@ya.ru", "asdfgh", 11L, "asdfgh", "asdfgh123", "222-222-222", "asdfgh");
    }


    @Test(priority = 1)
    public void checkGetUser1() throws InterruptedException {
        checkGetUser("qwerty","111-111-111");
    }

    @Test(priority = 1)
    public void checkGetUser2() throws InterruptedException {
        checkGetUser("asdfgh","222-222-222");
    }














///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void creationUserCheck(String email, String firstName, Long id, String lastName, String password, String phone, String username){
        UserService userService = new UserService();
        User user;
        Response response;
        user = User.builder()
                .email(email)
                .firstName(firstName)
                .id(id)
                .lastName(lastName)
                .password(password)
                .phone(phone)
                .username(username)
                .userStatus(101)
                .build();

        response = userService.addUserRequest(user);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("type", equalTo("unknown"))
                .body("message", comparesEqualTo(id.toString()));
    }

    public void checkGetUser(String userName, String phone) throws InterruptedException {
        UserService userService = new UserService();
        Response response;
        response = userService.getUserRequest(userName);
        response.then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("username", equalTo(userName))
                .body("phone", equalTo(phone));
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}