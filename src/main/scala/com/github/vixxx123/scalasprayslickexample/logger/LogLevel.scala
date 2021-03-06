/**
 * Created by Wiktor Tychulski on 2015-04-24.
 *
 * Created on 2015-04-24
 */
package com.github.vixxx123.scalasprayslickexample.logger

/**
 * Logging levels
 */
private[logger] sealed trait LogLevel
case object DebugLevel extends LogLevel
case object InfoLevel extends LogLevel
case object ErrorLevel extends LogLevel
