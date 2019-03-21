/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.aspectj;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

/**
 * Abstract base aspect that can perform Dependency
 * Injection on objects, however they may be created.
 *
 * @author Ramnivas Laddad
 * @since 2.5.2
 */
public abstract aspect AbstractDependencyInjectionAspect {

	/**
	 * 是一个（组）基于正则表达式的表达式，有点绕，就是说他本身是一个表达式，但是他是基于正则语法的。
	 * 通常一个pointcut，会选取程序中的某些我们感兴趣的执行点，或者说是程序执行点的集合
	 */
	private pointcut preConstructionCondition() :
			leastSpecificSuperTypeConstruction() && preConstructionConfiguration();

	private pointcut postConstructionCondition() :
			mostSpecificSubTypeConstruction() && !preConstructionConfiguration();

	/**
	 * Select least specific super type that is marked for DI
	 * (so that injection occurs only once with pre-construction injection).
	 */
	public abstract pointcut leastSpecificSuperTypeConstruction();

	/**
	 * Select the most-specific initialization join point
	 * (most concrete class) for the initialization of an instance.
	 */
	@CodeGenerationHint(ifNameSuffix="6f1")
	public pointcut mostSpecificSubTypeConstruction() :
			if (thisJoinPoint.getSignature().getDeclaringType() == thisJoinPoint.getThis().getClass());

	/**
	 * 在构建之前选择要配置的bean中的连接点？默认情况下，使用与可配置注释中的默认值匹配的构造后注入。
	 * Select join points in beans to be configured prior to construction?
	 * By default, use post-construction injection matching the default in the Configurable annotation.
	 */
	public pointcut preConstructionConfiguration() : if (false);

	/**
	 * 为要插入依赖项的对象选择构造连接点
	 * Select construction join points for objects to inject dependencies.
	 */
	public abstract pointcut beanConstruction(Object bean);

	/**
	 * 为要插入依赖项的对象选择反序列化联接点
	 * Select deserialization join points for objects to inject dependencies.
	 */
	public abstract pointcut beanDeserialization(Object bean);

	/**
	 *在可配置bean中选择连接点
	 * Select join points in a configurable bean.
	 */
	public abstract pointcut inConfigurableBean();


	/**
	 * 构造前置配置
	 * Pre-construction configuration.
	 */
	@SuppressAjWarnings("adviceDidNotMatch")
	before(Object bean) :
		beanConstruction(bean) && preConstructionCondition() && inConfigurableBean()  {
		configureBean(bean);
	}

	/**
	 * 构造后置配置
	 * Post-construction configuration.
	 */
	@SuppressAjWarnings("adviceDidNotMatch")
	after(Object bean) returning :
		beanConstruction(bean) && postConstructionCondition() && inConfigurableBean() {
		configureBean(bean);
	}

	/**
	 * 后置反序列化配置
	 * Post-deserialization configuration.
	 */
	@SuppressAjWarnings("adviceDidNotMatch")
	after(Object bean) returning :
		beanDeserialization(bean) && inConfigurableBean() {
		configureBean(bean);
	}


	/**
	 * 配置给指定bean
	 * Configure the given bean.
	 */
	public abstract void configureBean(Object bean);

}
