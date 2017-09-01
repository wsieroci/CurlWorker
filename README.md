CurlWorker
=============

This is sample program written in Scala, done for purposes related to learning Scala programming language.
Program is creating two actors using Akka Actors. First of them picks random url every 5 seconds
and send it to second one. Second actor is making http request to that url and prints response code. That's it.
