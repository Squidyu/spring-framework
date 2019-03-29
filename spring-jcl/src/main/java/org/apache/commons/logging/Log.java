/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.logging;

/**
 * 抽象日志API的简单日志接口，logfactory实例化该接口。实现此接口的类必须具有一个构造函数，该构造函数采用表示此日志“name”的单个字符串参数。
 * A simple logging interface abstracting logging APIs.  In order to be
 * instantiated successfully by {@link LogFactory}, classes that implement
 * this interface must have a constructor that takes a single String
 * parameter representing the "name" of this Log.
 *
 * <p>The six logging levels used by <code>Log</code> are (in order):
 * <ol>
 * <li>trace (the least serious)</li>
 * <li>debug</li>
 * <li>info</li>
 * <li>warn</li>
 * <li>error</li>
 * <li>fatal (the most serious)</li>
 * </ol>
 *
 * 将这些日志级别映射到底层日志系统所使用的概念是依赖于实现的。不过，该实现应该确保这种排序行为符合预期。
 * The mapping of these log levels to the concepts used by the underlying
 * logging system is implementation dependent.
 * The implementation should ensure, though, that this ordering behaves
 * as expected.
 *
 * 性能通常是一个日志记录问题。通过检查适当的属性，组件可以避免昂贵的操作（生成要记录的信息）
 * <p>Performance is often a logging concern.
 * By examining the appropriate property,
 * a component can avoid expensive operations (producing information
 * to be logged).
 *
 * <p>For example,
 * <pre>
 *    if (log.isDebugEnabled()) {
 *        ... do something expensive ...
 *        log.debug(theResult);
 *    }
 * </pre>
 *
 * 底层日志记录系统的配置通常在日志记录API外部通过该系统支持的任何机制进行。
 *
 * <p>Configuration of the underlying logging system will generally be done
 * external to the Logging APIs, through whatever mechanism is supported by
 * that system.
 *
 * @author Juergen Hoeller (for the {@code spring-jcl} variant)
 * @since 5.0
 */
public interface Log {

	/**
	 * Is fatal logging currently enabled?
	 * <p>Call this method to prevent having to perform expensive operations
	 * (for example, <code>String</code> concatenation)
	 * when the log level is more than fatal.
	 * @return true if fatal is enabled in the underlying logger.
	 * 当前是否启用Fatal(严重)级日志记录
	 * 当日志级别大于Fatal(严重)级别时，调用此方法以防止执行昂贵的操作
	 * 如果在底层记录器中启用了fatal，则返回true。
	 */
	boolean isFatalEnabled();

	/**
	 * Is error logging currently enabled?
	 * <p>Call this method to prevent having to perform expensive operations
	 * (for example, <code>String</code> concatenation)
	 * when the log level is more than error.
	 * @return true if error is enabled in the underlying logger.
	 * 当前是否启用Error级日志记录
	 *
	 * 如果在底层记录器中启用了Error，则返回true
	 */
	boolean isErrorEnabled();

	/**
	 * Is warn logging currently enabled?
	 * <p>Call this method to prevent having to perform expensive operations
	 * (for example, <code>String</code> concatenation)
	 * when the log level is more than warn.
	 * @return true if warn is enabled in the underlying logger.
	 * 当前是否启用Warn级日志记录
	 *
	 * 如果在底层记录器中启用了Warn，则返回true
	 */
	boolean isWarnEnabled();

	/**
	 * Is info logging currently enabled?
	 * <p>Call this method to prevent having to perform expensive operations
	 * (for example, <code>String</code> concatenation)
	 * when the log level is more than info.
	 * @return true if info is enabled in the underlying logger.
	 * 当前是否启用Info级日志记录
	 *
	 * 如果在底层记录器中启用了Info，则返回true
	 */
	boolean isInfoEnabled();

	/**
	 * Is debug logging currently enabled?
	 * <p>Call this method to prevent having to perform expensive operations
	 * (for example, <code>String</code> concatenation)
	 * when the log level is more than debug.
	 * @return true if debug is enabled in the underlying logger.
	 * 当前是否启用Debug级日志记录
	 *
	 * 如果在底层记录器中启用了Debug，则返回true
	 */
	boolean isDebugEnabled();

	/**
	 * Is trace logging currently enabled?
	 * <p>Call this method to prevent having to perform expensive operations
	 * (for example, <code>String</code> concatenation)
	 * when the log level is more than trace.
	 * @return true if trace is enabled in the underlying logger.
	 * 当前是否启用Trace级日志记录
	 *
	 * 如果在底层记录器中启用了Trace，则返回true
	 */
	boolean isTraceEnabled();


	/**
	 * Logs a message with fatal log level.
	 * @param message log this message
	 */
	void fatal(Object message);

	/**
	 * Logs an error with fatal log level.
	 * @param message log this message
	 * @param t log this cause
	 */
	void fatal(Object message, Throwable t);

	/**
	 * Logs a message with error log level.
	 * @param message log this message
	 */
	void error(Object message);

	/**
	 * Logs an error with error log level.
	 * @param message log this message
	 * @param t log this cause
	 */
	void error(Object message, Throwable t);

	/**
	 * Logs a message with warn log level.
	 * @param message log this message
	 */
	void warn(Object message);

	/**
	 * Logs an error with warn log level.
	 * @param message log this message
	 * @param t log this cause
	 */
	void warn(Object message, Throwable t);

	/**
	 * Logs a message with info log level.
	 * @param message log this message
	 */
	void info(Object message);

	/**
	 * Logs an error with info log level.
	 * @param message log this message
	 * @param t log this cause
	 */
	void info(Object message, Throwable t);

	/**
	 * Logs a message with debug log level.
	 * @param message log this message
	 */
	void debug(Object message);

	/**
	 * Logs an error with debug log level.
	 * @param message log this message
	 * @param t log this cause
	 */
	void debug(Object message, Throwable t);

	/**
	 * Logs a message with trace log level.
	 * @param message log this message
	 */
	void trace(Object message);

	/**
	 * Logs an error with trace log level.
	 * @param message log this message
	 * @param t log this cause
	 */
	void trace(Object message, Throwable t);

}
