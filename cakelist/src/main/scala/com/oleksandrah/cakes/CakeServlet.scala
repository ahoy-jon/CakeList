package com.oleksandrah.cakes

import com.oleksandrah.cakes.model.{Cake, Tables}
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport
import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration


class CakeServlet(db: H2Profile.backend.Database) extends ScalatraServlet with ScalateSupport {

  def exec[T](action: DBIO[T]): T = Await.result(db.run(action), Duration.Inf)


  def cakes: Map[Int, Cake] = {
    exec(Tables.cakes.result).map(x => x.id -> x).toMap
  }

  get("/") {
    contentType = "text/html"
    jade("/index", "cakes" -> cakes.values.toList.sortBy(_.completed))
  }

  post("/new") {
    val id = cakes.size

    exec(Tables.cakes += Cake(id, params.get("cakes").get, false))
    //cakes.put(id, Cake(id, params.get("cakes").get, completed = false))
    redirect("/")
  }

  get("/:id/completed") {
    val id = params("id").toInt

    exec(Tables.cakes.filter(_.id === id).map(_.completed).update(true))

    redirect("/")
  }

  get("/:id/delete") {
    val id = params("id").toInt
    exec(Tables.cakes.filter(_.id === id).delete)
    redirect("/")
  }
}