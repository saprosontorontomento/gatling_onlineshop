package onlineshop;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Session;

public class Scenario {

    public static ScenarioBuilder mainScenario = CoreDsl.scenario("Main scenario")
        .exec(Steps.authorizationRequest)
        .exec(session -> {
            System.out.println("session token =" + session.get("token"));
            return session.set("token", session.get("token"));
        })
        .exec(Steps.getOrdersByUser)
        .exec(session -> {
            System.out.println("session order=" + session);
            return session;
        })
        .exec(Steps.getOrderById)
//        .exec(Steps.getGroups)
        ;
}
