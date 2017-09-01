package com.wiktorsierocinski.curlworker

import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.stream.ActorMaterializer

class HttpRequestActor extends Actor with ActorLogging {

  import HttpRequestActor._

  implicit val system = context.system
  implicit val ec = system.dispatcher
  implicit val materializer = ActorMaterializer()(system)

  def receive = {
    case RequestUrlMessage(url) =>
      log.info(s"Url requested: $url")

      val response = Http().singleRequest(HttpRequest(uri = url))
      val result = response map {
        case HttpResponse(StatusCodes.OK, _, _, _) =>
          s"$url is OK"
        case HttpResponse(code, _, _, _) =>
          s"$url returned response code: $code"
        case _ =>
          s"$url returned unknown response"
      }

      result.onComplete(m => log.info(m.toString))
  }
}

object HttpRequestActor {

  val props = Props[HttpRequestActor]

  case class RequestUrlMessage(url: String)
}
