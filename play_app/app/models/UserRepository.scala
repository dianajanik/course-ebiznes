package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

/**
  * Created by dianajanik on 16.04.2019
  */


/**
*
* @param dbConfigProvider The Play db config provider. Play will inject this for you.
*/
@Singleton
class UserRepository@Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class UserTable(tag: Tag) extends Table[User](tag, "User"){

    def idUser = column[Int]("idUser", O.PrimaryKey, O.AutoInc)
    def userEmail = column[String]("userEmail", O.Unique)
    def userPassword = column[String]("userPassword")
    def userName = column[String]("userName")
    def userSurname = column[String]("userSurname")
    def userStreet = column[String]("userStreet")
    def userHomeNumber = column[String]("userHomeNumber")
    def userCity = column[String]("userCity")
    def userCountry = column[String]("userCountry")
    def userPostalCode = column[String]("userPostalCode")

    def * = (idUser,userEmail, userPassword, userName, userSurname, userStreet, userHomeNumber, userCity, userCountry, userPostalCode ) <> ((User.apply _).tupled, User.unapply)

  }

  private val user = TableQuery[UserTable]

  def create(userEmail: String, userPassword: String, userName: String, userSurname: String,
             userStreet: String, userHomeNumber: String, userCity: String, userCountry: String, userPostalCode: String): Future[User] = db.run {
    (user.map(c => (c.userEmail, c.userPassword, c.userName, c.userSurname, c.userStreet, c.userHomeNumber, c.userCity, c.userCountry, c.userPostalCode))
      returning user.map(_.idUser)
      into { case ((userEmail, userPassword, userName, userSurname, userStreet, userHomeNumber, userCity, userCountry, userPostalCode), idUser) => User(idUser,userEmail, userPassword, userName, userSurname, userStreet, userHomeNumber, userCity, userCountry, userPostalCode )}
      ) += (userEmail, userPassword, userName, userSurname, userStreet, userHomeNumber, userCity, userCountry, userPostalCode)
  }

  def list(): Future[Seq[User]] = db.run {
    user.result
  }

  def findById(id: Int): Future[Option[User]] = db.run(user.filter(_.idUser === id).result.headOption)
  def delete(id: Int): Future[Unit] = db.run(user.filter(_.idUser === id).delete).map(_ => ())

  def update(value: User): Future[Int] = db.run {
    user.insertOrUpdate(value)
  }
}
