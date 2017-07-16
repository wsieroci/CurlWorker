package com.wiktorsierocinski.curlworker

import akka.actor.ActorSystem

object Application extends App {
  val system = ActorSystem("MyActorSystem")
  val curlActor = system.actorOf(CurlActor.props, "curlActor")
  curlActor ! CurlActor.CurlSiteMessage("https://www.polidea.com")
}