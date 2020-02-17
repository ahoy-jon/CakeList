package com.oleksandrah.cakes

import com.oleksandrah.cakes.model.Cakes
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

class CakeServlet(cakes : collection.mutable.Map[Integer, Cakes]) extends ScalatraServlet with ScalateSupport {

  get("/") {
    contentType = "text/html"
    jade("/index", "cakes" -> cakes.values.toList.sortBy(_.completed))
  }

  post("/new") {
    val id = cakes.size
    cakes.put(id, Cakes(id, params.get("cakes").get, completed = false))
    redirect("/")
  }

  get("/:id/completed") {
    val cake = cakes.get(params("id").toInt).get
    cake.completed = true
    redirect("/")
  }

  get("/:id/delete") {
     cakes.remove(params("id").toInt)
    redirect("/")
  }
}