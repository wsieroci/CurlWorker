package com.wiktorsierocinski.curlworker

import akka.actor.ActorSystem

object Application extends App {
  val ACTOR_SYSTEM_NAME = "MyActorSystem"

  val system = ActorSystem(ACTOR_SYSTEM_NAME)
  val httpRequestActor = system.actorOf(HttpRequestActor.props, "httpRequestActor")
  val randomUrlGeneratorActor = system.actorOf(RandomUrlGeneratorActor.props, "randomUrlGeneratorActor")
  randomUrlGeneratorActor ! RandomUrlGeneratorActor.StartGeneratingRandomUrlsMessage(httpRequestActor)
}