package com.wiktorsierocinski.curlworker

import akka.actor.{Actor, ActorLogging, Props}

import scalaj.http.Http

class HttpRequestActor extends Actor with ActorLogging {
  import HttpRequestActor._

  def receive = {
    case CurlSiteMessage(url) =>
      val responseCode = Http(url).asString.code
      println(s"Url requested: $url")
      println(s"Response: $responseCode")
  }
}

object HttpRequestActor {
  val props = Props[HttpRequestActor]
  case class CurlSiteMessage(url: String)
}
