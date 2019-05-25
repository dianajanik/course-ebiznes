package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import models.CategoryRepository

/**
  * Created by dianajanik on 27.04.2019
  */

/**
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */

@Singleton
class ProductRepository@Inject()(dbConfigProvider: DatabaseConfigProvider, categoryRepository: CategoryRepository)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._


  class ProductTable(tag: Tag) extends Table[Product](tag, "Product"){

    def idProduct = column[Int]("idProduct", O.PrimaryKey, O.AutoInc)
    def productName = column[String]("productName")
    def productCategory = column[Int]("productCategory")
    def productPrice = column[Int]("productPrice")
    def productDescription = column[String]("productDescription")
    def productPhoto = column[String]("productPhoto")
    def productNotSaled = column[Boolean]("productNotSaled")
    private def productCategory_fk = foreignKey("cat_fk",productCategory, category)(_.idCategory)

    def * = (idProduct, productCategory, productPrice, productName, productDescription, productPhoto, productNotSaled)  <> ((Product.apply _).tupled, Product.unapply)

  }

  private val product = TableQuery[ProductTable]
  import categoryRepository.CategoryTable
  private val category = TableQuery[CategoryTable]

  def create(productCategory: Int, productPrice: Int, productName: String, productDescription: String, productPhoto: String, productNotSaled: Boolean): Future[Product] = db.run {
    (product.map(c => (c.productCategory, c.productPrice, c.productName, c.productDescription, c.productPhoto, c.productNotSaled))
      returning product.map(_.idProduct)
      into { case ((productCategory, productPrice, productName, productDescription, productPhoto, productNotSaled), idProduct) => Product(idProduct,productCategory, productPrice, productName, productDescription, productPhoto, productNotSaled)}
      ) += (productCategory, productPrice, productName, productDescription, productPhoto, productNotSaled)
  }

  def list(): Future[Seq[Product]] = db.run {
    product.result
  }

  def findById(id: Int): Future[Option[Product]] = db.run(product.filter(_.idProduct === id).result.headOption)
  def delete(id: Int): Future[Unit] = db.run(product.filter(_.idProduct === id).delete).map(_ => ())
}
