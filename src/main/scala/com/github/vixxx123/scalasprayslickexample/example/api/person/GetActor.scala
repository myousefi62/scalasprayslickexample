/**
 * Created by Wiktor Tychulski on 2015-04-24.
 *
 * Created on 2015-04-24
 */
package com.github.vixxx123.scalasprayslickexample.example.api.person

import akka.actor.{Props, Actor}
import com.github.vixxx123.scalasprayslickexample.logger.Logging
import com.github.vixxx123.scalasprayslickexample.websocket.PublishWebSocket
import spray.routing.RequestContext
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

case class GetMessage(ctx: RequestContext, userId: Option[Int])

/**
 * Actor handling person get message
 */
class GetActor(personDao: PersonDao) extends Actor with Logging with PublishWebSocket {

  override val logTag: String = getClass.getName

  override def receive: Receive = {

    // get all persons
    case GetMessage(ctx, None) =>
      L.info("Getting all persons")
      ctx.complete(personDao.getAll)


    // get person by id
    case GetMessage(ctx, Some(id)) =>
      L.info(s"Getting person id = $id")
      val localCtx = ctx
      localCtx.complete(personDao.getById(id))
  }
}

object GetActor {
  val Name = s"${ResourceName}GetRouter"
  def props(personDao: PersonDao) = Props(classOf[GetActor], personDao)
}
