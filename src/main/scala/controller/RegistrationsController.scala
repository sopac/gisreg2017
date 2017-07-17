package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Registration

class RegistrationsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Registration
  override def resourcesName = "registrations"
  override def resourceName = "registration"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(
    createParams,
    paramKey("title") is required & maxLength(512),
    paramKey("name") is required & maxLength(512),
    paramKey("email") is required & maxLength(512),
    paramKey("designation") is required & maxLength(512),
    paramKey("organisation") is required & maxLength(512),
    paramKey("country") is required & maxLength(512),
    paramKey("presentation_title1") is maxLength(512),
    paramKey("presentation_title2") is maxLength(512),
    paramKey("presentation_title3") is maxLength(512)
  )
  override def createFormStrongParameters = Seq(
    "title" -> ParamType.String,
    "name" -> ParamType.String,
    "email" -> ParamType.String,
    "designation" -> ParamType.String,
    "organisation" -> ParamType.String,
    "country" -> ParamType.String,
    "presenting" -> ParamType.Boolean,
    "displaying" -> ParamType.Boolean,
    "presentation_title1" -> ParamType.String,
    "presentation_title2" -> ParamType.String,
    "presentation_title3" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(
    updateParams,
    paramKey("title") is required & maxLength(512),
    paramKey("name") is required & maxLength(512),
    paramKey("email") is required & maxLength(512),
    paramKey("designation") is required & maxLength(512),
    paramKey("organisation") is required & maxLength(512),
    paramKey("country") is required & maxLength(512),
    paramKey("presentation_title1") is maxLength(512),
    paramKey("presentation_title2") is maxLength(512),
    paramKey("presentation_title3") is maxLength(512)
  )
  override def updateFormStrongParameters = Seq(
    "title" -> ParamType.String,
    "name" -> ParamType.String,
    "email" -> ParamType.String,
    "designation" -> ParamType.String,
    "organisation" -> ParamType.String,
    "country" -> ParamType.String,
    "presenting" -> ParamType.Boolean,
    "displaying" -> ParamType.Boolean,
    "presentation_title1" -> ParamType.String,
    "presentation_title2" -> ParamType.String,
    "presentation_title3" -> ParamType.String
  )

}
