package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class RegistrationsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.registrations, "/*")

  override def afterAll() {
    super.afterAll()
    Registration.deleteAll()
  }

  def newRegistration = FactoryGirl(Registration).create()

  it should "show registrations" in {
    get("/registrations") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/registrations/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/registrations.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/registrations.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a registration in detail" in {
    get(s"/registrations/${newRegistration.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/registrations/${newRegistration.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/registrations/${newRegistration.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/registrations/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a registration" in {
    post(s"/registrations",
      "title" -> "dummy",
      "name" -> "dummy",
      "email" -> "dummy",
      "designation" -> "dummy",
      "organisation" -> "dummy",
      "country" -> "dummy",
      "presenting" -> "true",
      "displaying" -> "true",
      "presentation_title1" -> "dummy",
      "presentation_title2" -> "dummy",
      "presentation_title3" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/registrations",
        "title" -> "dummy",
        "name" -> "dummy",
        "email" -> "dummy",
        "designation" -> "dummy",
        "organisation" -> "dummy",
        "country" -> "dummy",
        "presenting" -> "true",
        "displaying" -> "true",
        "presentation_title1" -> "dummy",
        "presentation_title2" -> "dummy",
        "presentation_title3" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Registration.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/registrations/${newRegistration.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a registration" in {
    put(s"/registrations/${newRegistration.id}",
      "title" -> "dummy",
      "name" -> "dummy",
      "email" -> "dummy",
      "designation" -> "dummy",
      "organisation" -> "dummy",
      "country" -> "dummy",
      "presenting" -> "true",
      "displaying" -> "true",
      "presentation_title1" -> "dummy",
      "presentation_title2" -> "dummy",
      "presentation_title3" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/registrations/${newRegistration.id}",
        "title" -> "dummy",
        "name" -> "dummy",
        "email" -> "dummy",
        "designation" -> "dummy",
        "organisation" -> "dummy",
        "country" -> "dummy",
        "presenting" -> "true",
        "displaying" -> "true",
        "presentation_title1" -> "dummy",
        "presentation_title2" -> "dummy",
        "presentation_title3" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a registration" in {
    delete(s"/registrations/${newRegistration.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/registrations/${newRegistration.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
