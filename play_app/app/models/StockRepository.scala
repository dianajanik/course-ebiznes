package models

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
class StockRepository@Inject()(dbConfigProvider: DatabaseConfigProvider, productRepository: ProductRepository)
                                                                               (implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._
  import productRepository.ProductTable

  class StockTable(tag: Tag) extends Table[Stock](tag, "Stock"){

    def idStock = column[Int]("idStock", O.PrimaryKey, O.AutoInc)
    def idProduct = column[Int]("idProduct")
    def quantity = column[Int]("quantity")
    private def idProduct_fk = foreignKey("cat_fk",idProduct, product)(_.idProduct)


    def * = (idStock, idProduct, quantity)  <> ((Stock.apply _).tupled, Stock.unapply)

  }
  import productRepository.ProductTable
  private val stock = TableQuery[StockTable]
  private val product = TableQuery[ProductTable]

  def create(idProduct: Int, quantity: Int): Future[Stock] = db.run {
    (stock.map(c => (c.idProduct, c.quantity))
      returning stock.map(_.idStock)
      into { case ((idProdyct, quantity), idStock) => Stock(idStock,idProduct, quantity)}
      ) += (idProduct, quantity)
  }

  def list(): Future[Seq[Stock]] = db.run {
    stock.result
  }
  def findById(id: Int): Future[Option[Stock]] = db.run(stock.filter(_.idStock === id).result.headOption)
  def delete(id: Int): Future[Unit] = db.run(stock.filter(_.idStock === id).delete).map(_ => ())
}
