package com.wiktorsierocinski.curlworker

import akka.actor.{Actor, ActorLogging, Props}

import scalaj.http.Http

class CurlActor extends Actor with ActorLogging {
  import CurlActor._

  def receive = {
    case CurlSiteMessage(url) =>
      val response = Http(url).param("q", "polidea").asString
      println(response)
  }
}

object CurlActor {
  val props = Props[CurlActor]
  case class CurlSiteMessage(url: String)
}
