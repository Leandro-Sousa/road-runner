import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test

class ApiTests {

    @Before
    fun setup() {
        RestAssured.baseURI = "http://localhost/api"
        RestAssured.port = 80

        //Starts Application
        main(arrayOf(""))
    }

    @Test
    fun shouldRequestInvalidEndpointResultNotFound() {

        given().
            `when`()
            .get("/test")
            .then()
            .statusCode(404)
    }

    @Test
    fun shouldRequestUserInfoResultSuccess() {

        given().
            `when`()
            .get("users/Leandro-Sousa")
            .then()
            .statusCode(200)
    }

    @Test
    fun shouldRequestUserInfoBodyWellFormed() {

        given().
            `when`()
            .get("users/Leandro-Sousa")
            .then()
            .body("name", equalTo("Leandro Sousa"))
            .body("id", equalTo("19270245"))
            .body("login", equalTo("Leandro-Sousa"))
            .body("avatar_url", equalTo("https://avatars1.githubusercontent.com/u/19270245?v=4"))
            .body("html_url", equalTo("https://github.com/Leandro-Sousa"))
            .statusCode(200)
    }

    @Test
    fun shouldRequestUserInfoResultNotFound() {

        given().
            `when`()
            .get("users/Leandro-Sousa-Abvd")
            .then()
            .statusCode(404)
    }

    @Test
    fun shouldRequestUserReposResultNotFound() {

        given().
            `when`()
            .get("users/Leandro-Sousa-Abvd/repos")
            .then()
            .statusCode(404)
    }

    @Test
    fun shouldRequestUserReposResultSuccess() {

        given().
            `when`()
            .get("users/Leandro-Sousa/repos")
            .then()
            .statusCode(200)
    }

    @Test
    fun shouldRequestUserReposResultWellFormed() {

        given().
            `when`()
            .get("users/Leandro-Sousa/repos")
            .then()
            .body("", everyItem(hasKey("id")))
            .body("", everyItem(hasKey("name")))
            .body("", everyItem(hasKey("description")))
            .body("", everyItem(hasKey("html_url")))
            .statusCode(200)
    }
}