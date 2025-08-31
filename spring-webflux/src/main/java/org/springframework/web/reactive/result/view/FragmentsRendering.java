/*
 * Copyright 2002-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.reactive.result.view;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import org.springframework.core.ReactiveAdapter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Public API for HTML rendering of a collection of fragments each with a view
 * and independent model. For use with frontends technologies such as
 * <a href="https://htmx.org/">htmx</a> where multiple page fragments may be
 * rendered in one response. Supported as a return value from Spring WebFlux
 * controller methods.
 *
 * <p>For full page rendering with a single model and view, use {@link Rendering}.
 *
 * @author Rossen Stoyanchev
 * @since 6.2
 */
public interface FragmentsRendering {

	/**
	 * Return the HTTP status to set the response to.
	 */
	@Nullable
	HttpStatusCode status();

	/**
	 * Return headers to add to the response.
	 */
	HttpHeaders headers();

	/**
	 * Return the fragments to render.
	 */
	Flux<Fragment> fragments();


	/**
	 * Create a builder and add a fragment with a view name and a model.
	 * @param viewName the name of the view for the fragment
	 * @param model attributes for the fragment in addition to model
	 * attributes inherited from the model for the request
	 * @return this builder
	 */
	static Builder with(String viewName, Map<String, Object> model) {
		return withCollection(List.of(Fragment.create(viewName, model)));
	}

	/**
	 * Variant of {@link #with(String, Map)} with a view name only, but also
	 * inheriting model attributes from the shared model for the request.
	 * @param viewName the name of the view for the fragment
	 * @return this builder
	 */
	static Builder with(String viewName) {
		return withCollection(List.of(Fragment.create(viewName)));
	}

	/**
	 * Variant of {@link #with(String, Map)} with a collection of fragments.
	 * @param fragments the fragments to add; each fragment also inherits model
	 * attributes from the shared model for the request
	 * @return the created builder
	 */
	static Builder withCollection(Collection<Fragment> fragments) {
		return new DefaultFragmentsRenderingBuilder(fragments);
	}

	/**
	 * Variant of {@link #with(String, Map)} with a {@link Publisher} of fragments.
	 * @param fragmentsPublisher the fragments to add; each fragment also
	 * inherits model attributes from the shared model for the request
	 * @return the created builder
	 */
	static <P extends Publisher<Fragment>> Builder withPublisher(P fragmentsPublisher) {
		return new DefaultFragmentsRenderingBuilder(fragmentsPublisher);
	}

	/**
	 * Variant of {@link #withPublisher(Publisher)} that allows using any
	 * producer that can be resolved to {@link Publisher} via
	 * {@link ReactiveAdapterRegistry}.
	 */
	static Builder withProducer(Object fragmentsProducer) {
		return new DefaultFragmentsRenderingBuilder(adaptProducer(fragmentsProducer));
	}

	private static Publisher<Fragment> adaptProducer(Object producer) {
		ReactiveAdapter adapter = ReactiveAdapterRegistry.getSharedInstance().getAdapter(producer.getClass());
		Assert.isTrue(adapter != null, "Unknown producer " + producer.getClass());
		return adapter.toPublisher(producer);
	}


	/**
	 * Defines a builder for {@link FragmentsRendering}.
	 */
	interface Builder {

		/**
		 * Specify the status to use for the response.
		 * @param status the status to set
		 * @return this builder
		 */
		Builder status(HttpStatusCode status);

		/**
		 * Add one or more header values under the given name.
		 * @param headerName  the header name
		 * @param headerValues the header value(s)
		 * @return this builder
		 */
		Builder header(String headerName, String... headerValues);

		/**
		 * Provides access to every header declared so far with the possibility
		 * to add, replace, or remove values.
		 * @param headersConsumer the consumer to provide access to
		 * @return this builder
		 */
		Builder headers(Consumer<HttpHeaders> headersConsumer);

		/**
		 * Add a fragment with a view name and a model.
		 * @param viewName the name of the view for the fragment
		 * @param model attributes for the fragment in addition to model
		 * attributes inherited from the model for the request
		 * @return this builder
		 */
		Builder fragment(String viewName, Map<String, Object> model);

		/**
		 * Variant of {@link #fragment(String, Map)} with a view name only, where
		 * the fragment model also inherits model attributes from the shared
		 * model for the request.
		 * @param viewName the name of the view for the fragment
		 * @return this builder
		 */
		Builder fragment(String viewName);

		/**
		 * Variant of {@link #fragment(String, Map)} with a {@link Fragment}.
		 * @param fragment the fragment to add
		 * @return this builder
		 */
		Builder fragment(Fragment fragment);

		/**
		 * Build the {@link FragmentsRendering} instance.
		 */
		FragmentsRendering build();
	}

}
