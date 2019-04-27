package models

import java.sql.Timestamp

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by dianajanik on 27.04.2019
  */

/**
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class TransactionRepository@Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository)
                              (implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class TransactionTable(tag: Tag) extends Table[Transaction](tag, "Transaction"){

    def idTransaction = column[Int]("idTransaction", O.PrimaryKey, O.AutoInc)
    def idUser = column[Int]("idUser")
    def transactionDate = column[Timestamp]("transactionDate")
    def idUser_fk = foreignKey("cat_fk",idUser, user)(_.idUser)


    def * = (idTransaction, idUser, transactionDate)  <> ((Transaction.apply _).tupled, Transaction.unapply)

  }
  import userRepository.UserTable
  val transaction = TableQuery[TransactionTable]
  val user = TableQuery[UserTable]

  def create(idUser: Int, transactionDate: Timestamp): Future[Transaction] = db.run {
    (transaction.map(c => (c.idUser, c.transactionDate))
      returning transaction.map(_.idTransaction)
      into { case ((idUser, transactionDate), idTransaction) => Transaction(idTransaction,idUser, transactionDate)}
      ) += (idUser, transactionDate)
  }

  def list(): Future[Seq[Transaction]] = db.run {
    transaction.result
  }

}
