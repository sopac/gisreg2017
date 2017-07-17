package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Registration(
  id: Long,
  title: String,
  name: String,
  email: String,
  designation: String,
  organisation: String,
  country: String,
  presenting: Boolean,
  displaying: Boolean,
  presentationTitle1: Option[String] = None,
  presentationTitle2: Option[String] = None,
  presentationTitle3: Option[String] = None,
  createdAt: DateTime,
  updatedAt: DateTime
)

object Registration extends SkinnyCRUDMapper[Registration] with TimestampsFeature[Registration] {
  override lazy val tableName = "registrations"
  override lazy val defaultAlias = createAlias("r")

  /*
   * If you're familiar with ScalikeJDBC/Skinny ORM, using #autoConstruct makes your mapper simpler.
   * (e.g.)
   * override def extract(rs: WrappedResultSet, rn: ResultName[Registration]) = autoConstruct(rs, rn)
   *
   * Be aware of excluding associations like this:
   * (e.g.)
   * case class Member(id: Long, companyId: Long, company: Option[Company] = None)
   * object Member extends SkinnyCRUDMapper[Member] {
   *   override def extract(rs: WrappedResultSet, rn: ResultName[Member]) =
   *     autoConstruct(rs, rn, "company") // "company" will be skipped
   * }
   */
  override def extract(rs: WrappedResultSet, rn: ResultName[Registration]): Registration = new Registration(
    id = rs.get(rn.id),
    title = rs.get(rn.title),
    name = rs.get(rn.name),
    email = rs.get(rn.email),
    designation = rs.get(rn.designation),
    organisation = rs.get(rn.organisation),
    country = rs.get(rn.country),
    presenting = rs.get(rn.presenting),
    displaying = rs.get(rn.displaying),
    presentationTitle1 = rs.get(rn.presentationTitle1),
    presentationTitle2 = rs.get(rn.presentationTitle2),
    presentationTitle3 = rs.get(rn.presentationTitle3),
    createdAt = rs.get(rn.createdAt),
    updatedAt = rs.get(rn.updatedAt)
  )
}
