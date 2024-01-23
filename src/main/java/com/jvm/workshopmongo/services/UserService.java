package com.jvm.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.workshopmongo.domain.User;
import com.jvm.workshopmongo.dto.UserDTO;
import com.jvm.workshopmongo.repository.UserRepository;
import com.jvm.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		//Usando o metodo findById para buscar o usuario pelo id
		findById(id);
		repo.deleteById(id);
	}
	
	public User update (User obj ) {
		//Instanciar um objeto User (Tenho que buscar o objeto no banco de dados, seria o repo.find...)
		User newObj = repo.findById(obj.getId()).get();
		/*Usar o metodo updateData passando newObj e obj.
		  Esse metodo é responsavel por copiar os novos dados do obj para o newObj */
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
