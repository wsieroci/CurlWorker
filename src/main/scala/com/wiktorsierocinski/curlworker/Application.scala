package com.wiktorsierocinski.curlworker

import akka.actor.ActorSystem

object Application extends App {
  val system = ActorSystem("MyActorSystem")
  val httpRequestActor = system.actorOf(HttpRequestActor.props, "httpRequestActor")
  val randomUrlGeneratorActor = system.actorOf(RandomUrlGeneratorActor.props, "randomUrlGeneratorActor")
  randomUrlGeneratorActor ! RandomUrlGeneratorActor.StartGeneratingRandomUrlsMessage(httpRequestActor)
}