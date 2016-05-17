/*
 * Copyright 2016 the original author or authors.
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

package org.springframework.kafka.listener.adapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.util.Assert;

/**
 * An abstract message listener adapter that implements de-duplication logic
 * via a {@link DeDuplicationStrategy}.
 *
 * @param <K> the key type.
 * @param <V> the value type.
 *
 * @author Gary Russell
 *
 */
public abstract class AbstractDeDuplicatingMessageListener<K, V> {

	private final DeDuplicationStrategy<K, V> deDupStrategy;

	protected AbstractDeDuplicatingMessageListener(DeDuplicationStrategy<K, V> deDupStrategy) {
		Assert.notNull(deDupStrategy, "'deDupStrategy' cannot be null");
		this.deDupStrategy = deDupStrategy;
	}

	protected boolean isDuplicate(ConsumerRecord<K, V> consumerRecord) {
		return this.deDupStrategy.isDuplicate(consumerRecord);
	}

}