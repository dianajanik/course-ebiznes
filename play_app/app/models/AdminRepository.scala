package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

/**
  * Created by dianajanik on 27.04.2019
  */

/**
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class AdminRepository@Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class AdminTable(tag: Tag) extends Table[Admin](tag, "Admin"){

    def idAdmin = column[Int]("idAdmin", O.PrimaryKey, O.AutoInc)
    def adminUsername = column[String]("adminUsername", O.Unique)
    def adminPassword = column[String]("adminPassword")


    def * = (idAdmin, adminUsername, adminPassword)  <> ((Admin.apply _).tupled, Admin.unapply)

  }

  private val admin = TableQuery[AdminTable]

  def create(adminUsername: String, adminPassword: String): Future[Admin] = db.run {
    (admin.map(c => (c.adminUsername, c.adminPassword))
      returning admin.map(_.idAdmin)
      into { case ((adminUsername, adminPassword), idAdmin) => Admin(idAdmin,adminUsername, adminPassword)}
      ) += (adminUsername, adminPassword)
  }

  def list(): Future[Seq[Admin]] = db.run {
    admin.result
  }

  def findById(id: Int): Future[Option[Admin]] = db.run(admin.filter(_.idAdmin === id).result.headOption)
  def delete(id: Int): Future[Unit] = db.run(admin.filter(_.idAdmin === id).delete).map(_ => ())

  def update(value: Admin): Future[Int] = db.run {
    admin.insertOrUpdate(value)
  }
}
