package com.wiktorsierocinski.curlworker

import akka.actor.{Actor, ActorLogging, Props}

import scalaj.http.Http

class CurlActor extends Actor with ActorLogging {
  import CurlActor._

  def receive = {
    case CurlSite(url) =>
      val response = Http(url).param("q", "polidea").asString
      println(response)
  }
}

object CurlActor {
  val props = Props[CurlActor]
  case class CurlSite(url: String)
}
