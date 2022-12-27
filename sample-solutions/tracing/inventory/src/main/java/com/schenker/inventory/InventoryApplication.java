package com.schenker.inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.schenker.inventory.model.Post;
import com.schenker.inventory.model.Species;
import com.schenker.inventory.repositories.PostRepository;
import com.schenker.inventory.repositories.SpeciesRepository;
import com.schenker.inventory.services.JsonPlaceholderService;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepository, SpeciesRepository speciesRepository, ObservationRegistry observationRegistry) {
		return args -> {
			addPosts(postRepository, observationRegistry);
			addSpecies(speciesRepository, observationRegistry);
		};
	}

	private void addPosts(PostRepository postRepository, ObservationRegistry observationRegistry) {
			WebClient client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
			HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
			JsonPlaceholderService jps = factory.createClient(JsonPlaceholderService.class);

			List<Post> posts = Observation
					.createNotStarted("json-place-holder.load-posts", observationRegistry)
					.lowCardinalityKeyValue("some-value", "100")
					.observe(jps::loadPosts);

			Observation
					.createNotStarted("post-repository.save-all", observationRegistry)
					.observe(() -> postRepository.saveAll(posts));

	}

	private void addSpecies(SpeciesRepository speciesRepository, ObservationRegistry observationRegistry) {
		List<Species> species = new ArrayList<Species>();
		species.add(new Species("dog", "Dog", "Human's best friend"));
		species.add(new Species("mouse", "Mouse", "Small gray mammal"));
		species.add(new Species("elephant", "Elephant", "Big gray mammal"));

		Observation
				.createNotStarted("species-repository.save-all", observationRegistry)
				.observe(() -> speciesRepository.saveAll(species));
	}
}
