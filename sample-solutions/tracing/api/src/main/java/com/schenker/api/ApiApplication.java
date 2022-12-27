package com.schenker.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.schenker.api.model.Album;
import com.schenker.api.model.PetEntity;
import com.schenker.api.model.User;
import com.schenker.api.repositories.AlbumRepository;
import com.schenker.api.repositories.PetEntityRepository;
import com.schenker.api.repositories.UserRepository;
import com.schenker.api.services.JsonPlaceholderService;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PetEntityRepository petRepository, UserRepository userRepository, AlbumRepository albumRepository, ObservationRegistry observationRegistry) {
		return args -> {
			WebClient client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
			HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
			JsonPlaceholderService jps = factory.createClient(JsonPlaceholderService.class);

			addUsers(jps, userRepository, observationRegistry);
			addAlbums(jps, albumRepository, observationRegistry);
			addPets(petRepository, observationRegistry);
		};
	}

	private void addAlbums(JsonPlaceholderService jps, AlbumRepository albumRepository, ObservationRegistry observationRegistry) {
		List<Album> albums = Observation
				.createNotStarted("json-place-holder.load-albums", observationRegistry)
				.lowCardinalityKeyValue("some-funky-custom-value", "The answer is 4")
				.observe(jps::loadAlbums);

		Observation
				.createNotStarted("album-repository.save-all", observationRegistry)
				.observe(() -> albumRepository.saveAll(albums));
}

	private void addUsers(JsonPlaceholderService jps, UserRepository userRepository, ObservationRegistry observationRegistry) {
		List<User> users = Observation
				.createNotStarted("json-place-holder.load-users", observationRegistry)
				.observe(jps::loadUsers);

		Observation
				.createNotStarted("user-repository.save-all", observationRegistry)
				.observe(() -> userRepository.saveAll(users));
	}

	private void addPets(PetEntityRepository petRepository, ObservationRegistry observationRegistry) {
		List<PetEntity> pets = new ArrayList<PetEntity>();
		pets.add(new PetEntity("pet-1", "Pluto", "https://example.com/animals/images/pet-1", "dog"));
		pets.add(new PetEntity("pet-2", "Eli 1", "https://example.com/animals/images/pet-2", "elephant"));
		pets.add(new PetEntity("pet-3", "Mousy", "https://example.com/animals/images/pet-3", "mouse"));

		Observation
				.createNotStarted("pet-repository.save-all", observationRegistry)
				.observe(() -> petRepository.saveAll(pets));
	}

}
