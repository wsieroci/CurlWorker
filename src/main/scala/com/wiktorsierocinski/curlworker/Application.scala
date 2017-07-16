package com.wiktorsierocinski.curlworker

import akka.actor.{ActorRef, ActorSystem}

object Application extends App {
  val system = ActorSystem("MyActorSystem")
  val curlActor = system.actorOf(HttpRequestActor.props, "httpRequestActor")
  val randomUrlGeneratorActor = system.actorOf(RandomUrlGeneratorActor.props, "randomUrlGeneratorActor")
  randomUrlGeneratorActor ! RandomUrlGeneratorActor.GenerateNewUrlMessage(curlActor)
}