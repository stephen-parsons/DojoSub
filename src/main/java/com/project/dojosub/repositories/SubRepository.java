package com.project.dojosub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dojosub.models.Sub;

@Repository 												
public interface SubRepository extends CrudRepository<Sub,Long>{
	@Query(value="select * from sub where available=1", nativeQuery=true)
    List<Sub> findAllSubsAvailable();
}
