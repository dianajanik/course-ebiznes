package models

import com.google.common.base.Optional
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
class CategoryRepository@Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CategoryTable(tag: Tag) extends Table[Category](tag, "Category"){

    def idCategory = column[Int]("idCategory", O.PrimaryKey, O.AutoInc)
    def categoryName = column[String]("categoryName", O.Unique)
    def categoryUpper = column[Option[Int]]("categoryUpper")
    private def categoryUpper_fk = foreignKey("cat_fk",categoryUpper, category)(_.idCategory)


    def * = (idCategory, categoryName, categoryUpper)  <> ((Category.apply _).tupled, Category.unapply)

  }
  private val category = TableQuery[CategoryTable]

  def create(categoryName: String, categoryUpper: Option[Int]): Future[Category] = db.run {
    (category.map(c => (c.categoryName, c.categoryUpper))
      returning category.map(_.idCategory)
      into { case ((categoryUsername, categoryPassword), idCategory) => Category(idCategory,categoryUsername, categoryPassword)}
      ) += (categoryName, categoryUpper)
  }

  def list(): Future[Seq[Category]] = db.run {
    category.result
  }

  def findById(id: Int): Future[Option[Category]] = db.run(category.filter(_.idCategory === id).result.headOption)
  def delete(id: Int): Future[Unit] = db.run(category.filter(_.idCategory === id).delete).map(_ => ())

  def update(value: Category): Future[Int] = db.run {
    category.insertOrUpdate(value)
  }
}
