package com.oleksandrah.cakes

import com.oleksandrah.cakes.model.Tables
import org.scalatra.{FutureSupport, ScalatraBase, ScalatraServlet}
import slick.jdbc.H2Profile.api._

import scala.concurrent._
import scala.concurrent.duration.Duration

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

  protected implicit def executor: ExecutionContextExecutor = scala.concurrent.ExecutionContext.Implicits.global

}