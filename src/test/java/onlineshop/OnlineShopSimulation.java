package onlineshop;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class OnlineShopSimulation extends Simulation {

    HttpProtocolBuilder httpProtocolAuth = HttpDsl.http.baseUrl("http://localhost");

    public OnlineShopSimulation() {
        this.setUp(
          Scenario.mainScenario.injectOpen(
                  CoreDsl.constantUsersPerSec(1).during(1)
          )
        ).protocols(httpProtocolAuth);
    }
}
