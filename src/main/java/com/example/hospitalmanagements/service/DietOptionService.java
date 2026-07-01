package com.example.hospitalmanagements.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.DietOption;
import com.example.hospitalmanagements.repository.DietOptionRepository;

@Service
public class DietOptionService {
	@Autowired
    private DietOptionRepository repo;

	public List<DietOption> getAllDietOptions() {
        return repo.findAll();
    }

    public List<DietOption> getByType(String type) {
        return repo.findByType(type);
    }

    public Optional<DietOption> getDietOptionById(Long id) {
        return repo.findById(id);
    }

    public DietOption saveDietOption(DietOption dietOption) {
        return repo.save(dietOption);
    }

    public void deleteDietOption(Long id) {
        repo.deleteById(id);
    }
}