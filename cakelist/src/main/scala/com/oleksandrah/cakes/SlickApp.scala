package com.oleksandrah.cakes

import org.scalatra.{ScalatraBase, FutureSupport, ScalatraServlet}

import slick.jdbc.H2Profile.api._
import scala.concurrent._
import scala.concurrent.duration.Duration

object Tables {

  // Definition of the CakeList table
  class CakeList(tag: Tag) extends Table[(Int, String)](tag, "CAKELIST") {
    def id = column[Int]("CAKE_ID", O.PrimaryKey) // This is the primary key column
    def name = column[String]("CAKE_NAME")


    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name)
  }


  // Table query for the CakeList table, represents all tuples
  val cakelist = TableQuery[CakeList]

  val getAllCakes = {
    for {
      c <- cakelist
    } yield (c.name)
  }

  // DBIO Action which runs several queries inserting sample data
  val insertCakesData = DBIO.seq(
    Tables.cakelist += (1, "Tiramisu"),
    Tables.cakelist += (2, "Cheesecake New York"))

  // DBIO Action which creates the schema
  val createSchemaAction = (cakelist.schema).create

  // DBIO Action which drops the schema
  val dropSchemaAction = (cakelist.schema).drop


  val createDatabase = DBIO.seq(createSchemaAction, insertCakesData)

}

trait SlickRoutes extends ScalatraBase with FutureSupport {

  def db: Database

  get("/db/create-db") {
    db.run(Tables.createDatabase)
  }

  get("/db/drop-db") {
    db.run(Tables.dropSchemaAction)
  }

/*  get("/cakes") {
    db.run(Tables.getAllCakes.result)
    .map(a => a.name)

  }
 */
}

class SlickApp(val db: Database) extends ScalatraServlet with FutureSupport with SlickRoutes {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

}