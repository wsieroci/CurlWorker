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

  def receive = {
    case GenerateNewUrlMessage(recipient) =>
      recipient ! HttpRequestActor.CurlSiteMessage(Random.shuffle(URL_LIST).head)
      system.scheduler.scheduleOnce(Duration(2, TimeUnit.SECONDS), self, GenerateNewUrlMessage(recipient))
  }
}

object RandomUrlGeneratorActor {
  val props = Props[RandomUrlGeneratorActor]
  case class GenerateNewUrlMessage(recipient: ActorRef)
}
