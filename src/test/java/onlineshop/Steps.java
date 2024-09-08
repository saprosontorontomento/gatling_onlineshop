package onlineshop;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.jdbc.JdbcDsl;

import java.util.List;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;

public class Steps extends Simulation {
    private static FeederBuilder<?> productsFeeder;

    public static ChainBuilder authorizationRequest = CoreDsl.exec(
        HttpDsl.http("Auth Request")
            .post(":8081/authorization-api/login")
            .header("Content-Type", "application/json")
            .body(ElFileBody("data/payload.json"))
            .check(jsonPath(("$.accessToken")).saveAs("token"))
            .check(HttpDsl.status().is(200))

    );

    public static ChainBuilder getOrdersByUser = CoreDsl.exec(
        HttpDsl.http("Get Order")
            .get(":8083/products-api/orders/getOrdersByUser")
            .header("accept", "application/json")
            .header("username", "user1")
            .header("Authorization", "#{token}")
            .check(jsonPath("$..id").saveAs("buyID"))

    );

    public static ChainBuilder getOrderById = CoreDsl.exec(
        HttpDsl.http("Get Order By ID")
            .get(":8083/products-api/orders/getOrderById/#{buyID}")
            .header("accept", "application/json")
            .header("username", "user1")
            .header("Authorization", "#{token}")
    );


    public static ChainBuilder getGroups = CoreDsl.exec(
      HttpDsl.http("Get Groups")
          .get(":8083/products-api/groups")
          .header("accept", "application/json")
    );

}
