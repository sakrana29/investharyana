import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Projectsitedetail entity.
 */
class ProjectsitedetailGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the Projectsitedetail entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
        .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all projectsitedetails")
            .get("/api/projectsitedetails")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new projectsitedetail")
            .post("/api/projectsitedetails")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "projectid":null, "siteaddress":"SAMPLE_TEXT", "district":null, "block":null, "city_town_village":null, "tehsil_subtehsil":null, "multyvillageinvolved":null, "villageinvolved":"SAMPLE_TEXT", "falls_in_aravalli":null, "islandprocured":null, "allotedbyhsiidc":null, "estate":"SAMPLE_TEXT", "cluster":"SAMPLE_TEXT", "phase":"SAMPLE_TEXT", "sector":"SAMPLE_TEXT", "plotno":"SAMPLE_TEXT", "hadbastno":"SAMPLE_TEXT", "khasra_document":null, "liesunder_mc":null, "distance_from_mc":"0", "islocated_in_urban":null, "revenu_shajra_document":null, "jamabandi":null, "nonencumbrance_certificate":null, "totalproposedprojectarea":null, "proposedbuilt_up_area":null, "certifiedownership":null, "ownership_document":null, "leaseapplicable":null, "lease_document":null, "landagreementapplicable":null, "landagreement_document":null, "sitelayoutplan":null, "locationplan":null, "linearstripplan":null, "connectingroad":null, "intersectiondistance":null, "railwaydistance":null, "confirmitylanduse":null, "landzoneuse_type":null, "sitesituated_document":null, "buildingexisted":null, "existing_building_applicable":null, "buildingplan_document":null, "site_situated_in_controlled_area":null, "controlledarea_document":null}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_projectsitedetail_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created projectsitedetail")
                .get("${new_projectsitedetail_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created projectsitedetail")
            .delete("${new_projectsitedetail_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(100) over (1 minutes))
    ).protocols(httpConf)
}