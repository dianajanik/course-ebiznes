package models

import java.sql.Timestamp

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.sql.SqlProfile.ColumnOption.SqlType

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
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class TransactionTable(tag: Tag) extends Table[Transaction](tag, "Transaction"){

    def idTransaction = column[Int]("idTransaction", O.PrimaryKey, O.AutoInc)
    def idUser = column[Int]("idUser")
    def transactionDate = column[Timestamp]("transactionDate", SqlType("timestamp not null default CURRENT_TIMESTAMP"))
    private def idUser_fk = foreignKey("cat_fk",idUser, user)(_.idUser)


    def * = (idTransaction, idUser, transactionDate)  <> ((Transaction.apply _).tupled, Transaction.unapply)

  }

  import userRepository.UserTable
  private val transaction = TableQuery[TransactionTable]
  private val user = TableQuery[UserTable]

  def create(idUser: Int, transactionDate: Timestamp): Future[Transaction] = db.run {
    (transaction.map(c => (c.idUser, c.transactionDate))
      returning transaction.map(_.idTransaction)
      into { case ((idUser, transactionDate), idTransaction) => Transaction(idTransaction,idUser, transactionDate)}
      ) += (idUser, transactionDate)
  }

  def list(): Future[Seq[Transaction]] = db.run {
    transaction.result
  }

  def findById(id: Int): Future[Option[Transaction]] = db.run(transaction.filter(_.idTransaction === id).result.headOption)
  def delete(id: Int): Future[Unit] = db.run(transaction.filter(_.idTransaction === id).delete).map(_ => ())
}
