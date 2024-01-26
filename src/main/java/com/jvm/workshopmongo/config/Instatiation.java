package com.jvm.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jvm.workshopmongo.domain.Post;
import com.jvm.workshopmongo.domain.User;
import com.jvm.workshopmongo.dto.AuthorDTO;
import com.jvm.workshopmongo.repository.PostRepository;
import com.jvm.workshopmongo.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		/*Salvar primeiro os usuarios para que eles tenham um ID proprio criado pelo banco de dados 
		  e depois fazer a copia para o AuthorDTO (embaixo)*/
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", 
				"Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria)); 
		/*Criei a  classe AuthorDTO para eu ter atributos especificos do author e tive que refatorar
		  instanciando o author e recebendo o usuario como argumento */
		
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", 
				"Acordei feliz hoje", new AuthorDTO(maria));
		
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
