package com.project.dojosub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dojosub.models.Sub;
import com.project.dojosub.repositories.SubRepository;

@Service
public class SubService {
	private SubRepository subRepository;
		
	public SubService(SubRepository subRepository){
		this.subRepository = subRepository;
	}

	public Sub create(Sub sub){
		return this.subRepository.save(sub);
	}

	public ArrayList<Sub> all(){
		return (ArrayList<Sub>) this.subRepository.findAll();
	}

	public Sub find(long id){
		return (Sub) this.subRepository.findOne(id);
	}

	public void update(Sub sub){
		this.subRepository.save(sub);
	}

	public void destroy(long id){
		this.subRepository.delete(id);
	}
	public List<Sub> findAllSubsAvailable(){
		return this.subRepository.findAllSubsAvailable();
	}
	
}
