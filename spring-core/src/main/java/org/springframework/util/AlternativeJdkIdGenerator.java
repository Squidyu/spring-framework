/*
 * Copyright 2002-2015 the original author or authors.
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

package org.springframework.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * An {@link IdGenerator} that uses {@link SecureRandom} for the initial seed and
 * {@link Random} thereafter, instead of calling {@link UUID#randomUUID()} every
 * time as {@link org.springframework.util.JdkIdGenerator JdkIdGenerator} does.
 * This provides a better balance between securely random ids and performance.
 *
 * @author Rossen Stoyanchev
 * @author Rob Winch
 * @since 4.0
 *
 * 在安全和性能上有一个平衡的UUID生成器
 */
public class AlternativeJdkIdGenerator implements IdGenerator {

	private final Random random;


	public AlternativeJdkIdGenerator() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] seed = new byte[8];
		/*生成用户指定的随机字节数*/
		secureRandom.nextBytes(seed);
		/*Random范围为BigInteger(seed)的long值*/
		this.random = new Random(new BigInteger(seed).longValue());
	}


	@Override
	public UUID generateId() {
		byte[] randomBytes = new byte[16];
		/*生成随机字节并将其放入用户提供的字节数组中。产生的随机字节数等于字节数组的长度。*/
		this.random.nextBytes(randomBytes);
		/*UUID的最高有效位*/
		long mostSigBits = 0;
		for (int i = 0; i < 8; i++) {
			mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
		}
		/*UUID的最低有效位*/
		long leastSigBits = 0;
		for (int i = 8; i < 16; i++) {
			leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
		}

		return new UUID(mostSigBits, leastSigBits);
	}

	public static void main(String[] args) {
		AlternativeJdkIdGenerator alternativeJdkIdGenerator = new AlternativeJdkIdGenerator();

		System.out.println(alternativeJdkIdGenerator.generateId());
		System.out.println(UUID.randomUUID());
	}
}
