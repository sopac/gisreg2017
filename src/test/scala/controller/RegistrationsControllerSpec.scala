package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class RegistrationsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Registration.deleteAll()
  }

  def createMockController = new RegistrationsController with MockController
  def newRegistration = FactoryGirl(Registration).create()

  describe("RegistrationsController") {

    describe("shows registrations") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/registrations/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/registrations/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a registration") {
      it("shows HTML response") {
        val registration = newRegistration
        val controller = createMockController
        controller.showResource(registration.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Registration]("item") should equal(Some(registration))
        controller.renderCall.map(_.path) should equal(Some("/registrations/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/registrations/new"))
      }
    }

    describe("creates a registration") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
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
          "presentation_title3" -> "dummy")
        controller.createResource()
        controller.status should equal(200)
      }

      it("fails with invalid parameters") {
        val controller = createMockController
        controller.prepareParams() // no parameters
        controller.createResource()
        controller.status should equal(400)
        controller.errorMessages.size should be >(0)
      }
    }

    it("shows a resource edit input form") {
      val registration = newRegistration
      val controller = createMockController
      controller.editResource(registration.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/registrations/edit"))
    }

    it("updates a registration") {
      val registration = newRegistration
      val controller = createMockController
      controller.prepareParams(
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
        "presentation_title3" -> "dummy")
      controller.updateResource(registration.id)
      controller.status should equal(200)
    }

    it("destroys a registration") {
      val registration = newRegistration
      val controller = createMockController
      controller.destroyResource(registration.id)
      controller.status should equal(200)
    }

  }

}
