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
class SubtransactionRepository@Inject()(dbConfigProvider: DatabaseConfigProvider,
                                        productRepository: ProductRepository,
                                        transactionRepository: TransactionRepository)
                                                                                      (implicit ec: ExecutionContext) {

  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class SubtransactionTable(tag: Tag) extends Table[Subtransaction](tag, "Subtransaction"){

    def idSubtransaction = column[Int]("idSubtransaction", O.PrimaryKey, O.AutoInc)
    def idTransaction = column[Int]("idTransaction")
    def idProduct = column[Int]("idProduct")
    def quantity = column[Int]("quantity")
    private def idProduct_fk = foreignKey("product_fk",idProduct, product)(_.idProduct)
    private def idTransaction_fk = foreignKey("transaction_fk",idTransaction, transaction)(_.idTransaction)


    def * = (idSubtransaction, idTransaction, idProduct, quantity)  <> ((Subtransaction.apply _).tupled, Subtransaction.unapply)

  }
  import productRepository.ProductTable
  import transactionRepository.TransactionTable
  private val subtransaction = TableQuery[SubtransactionTable]
  private val transaction = TableQuery[TransactionTable]
  private val product = TableQuery[ProductTable]

  def create(idTransaction: Int, idProduct: Int, quantity:Int): Future[Subtransaction] = db.run {
    (subtransaction.map(c => (c.idTransaction, c.idProduct, c.quantity))
      returning subtransaction.map(_.idSubtransaction)
      into { case (( idTransaction, idProduct, quantity), idSubtransaction) => Subtransaction(idSubtransaction, idTransaction, idProduct, quantity)}
      ) += (idTransaction, idProduct, quantity)
  }

  def list(): Future[Seq[Subtransaction]] = db.run {
    subtransaction.result
  }

  def findById(id: Int): Future[Option[Subtransaction]] = db.run(subtransaction.filter(_.idSubtransaction === id).result.headOption)
}
