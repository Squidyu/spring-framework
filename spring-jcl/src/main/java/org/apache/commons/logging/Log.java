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
 *
 *
 * 将这些日志级别映射到底层日志系统所使用的概念是依赖于实现的。不过，该实现应该确保这种排序行为符合预期。
 *
 *
 * 性能通常是一个日志记录问题。通过检查适当的属性，组件可以避免昂贵的操作（生成要记录的信息）
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
 */
public interface Log {

	/**
	 * 当前是否启用Fatal(严重)级日志记录
	 * 当日志级别大于Fatal(严重)级别时，调用此方法以防止执行昂贵的操作
	 * 如果在底层记录器中启用了fatal，则返回true。
	 */
	boolean isFatalEnabled();

	/**
	 * 当前是否启用Error级日志记录
	 *
	 * 如果在底层记录器中启用了Error，则返回true
	 */
	boolean isErrorEnabled();

	/**
	 * 当前是否启用Warn级日志记录
	 *
	 * 如果在底层记录器中启用了Warn，则返回true
	 */
	boolean isWarnEnabled();

	/**
	 * 当前是否启用Info级日志记录
	 *
	 * 如果在底层记录器中启用了Info，则返回true
	 */
	boolean isInfoEnabled();

	/**
	 * 当前是否启用Debug级日志记录
	 *
	 * 如果在底层记录器中启用了Debug，则返回true
	 */
	boolean isDebugEnabled();

	/**
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
