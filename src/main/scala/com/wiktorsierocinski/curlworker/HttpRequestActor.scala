package com.wiktorsierocinski.curlworker

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.stream.ActorMaterializer

class HttpRequestActor extends Actor with ActorLogging {

  import HttpRequestActor._

  implicit val system = ActorSystem(Application.ACTOR_SYSTEM_NAME)

  def receive = {
    case RequestUrlMessage(url) =>
      println(s"Url requested: $url")

      implicit val ec = system.dispatcher
      implicit val materializer = ActorMaterializer()(system)

      val response = Http().singleRequest(HttpRequest(uri = url))
      val result = response map {
        case HttpResponse(StatusCodes.OK, _, _, _) =>
          s"$url is OK"
        case HttpResponse(code, _, _, _) =>
          s"$url got response code: $code"
        case _ =>
          s"$url got unknown response"
      }

      result.onComplete( println(_) )
  }
}

object HttpRequestActor {

  val props = Props[HttpRequestActor]

  case class RequestUrlMessage(url: String)

}
