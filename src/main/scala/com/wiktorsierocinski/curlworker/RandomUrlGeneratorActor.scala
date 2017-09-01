package com.wiktorsierocinski.curlworker

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

import scala.concurrent.duration.Duration
import scala.language.postfixOps
import scala.util.Random

class RandomUrlGeneratorActor extends Actor with ActorLogging {

  import RandomUrlGeneratorActor._
  import context._

  val URL_LIST = List("https://www.polidea.com", "https://www.google.com", "https://www.onet.pl")
  val URL_GENERATION_FREQUENCY_IN_SEC = 5

  def receive = {

    case GenerateNewUrlMessage(recipient) =>
      recipient ! HttpRequestActor.RequestUrlMessage(Random.shuffle(URL_LIST).head)

    case StartGeneratingRandomUrlsMessage(recipient) =>
      system.scheduler.scheduleOnce(
        Duration(URL_GENERATION_FREQUENCY_IN_SEC, TimeUnit.SECONDS),
        self,
        StartGeneratingRandomUrlsMessage(recipient)
      )
      self ! GenerateNewUrlMessage(recipient)
  }
}

object RandomUrlGeneratorActor {

  val props = Props[RandomUrlGeneratorActor]

  case class GenerateNewUrlMessage(recipient: ActorRef)

  case class StartGeneratingRandomUrlsMessage(recipient: ActorRef)

}
