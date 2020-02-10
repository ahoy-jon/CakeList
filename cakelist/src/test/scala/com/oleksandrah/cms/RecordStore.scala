package com.oleksandrah.cms

import org.scalatra._
import org.scalatra.scalate


class RecordStore extends ScalatraServlet {
  get("/artists/:name/info") {
  Artist.find(params("name")) match {
    case Some(artist) => artist.toXml
    case None => status = 404
  }
  }
}