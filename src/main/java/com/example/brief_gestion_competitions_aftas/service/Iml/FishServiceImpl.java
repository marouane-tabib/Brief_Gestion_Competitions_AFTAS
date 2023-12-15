package com.example.brief_gestion_competitions_aftas.service.Iml;

import com.example.brief_gestion_competitions_aftas.handlers.exception.ResourceNotFoundException;
import com.example.brief_gestion_competitions_aftas.model.Fish;
import com.example.brief_gestion_competitions_aftas.repository.FishRepository;
import com.example.brief_gestion_competitions_aftas.service.FishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {
    private FishRepository fishRepository;
    private LevelServiceImpl levelService;

    public FishServiceImpl(FishRepository fishRepository, LevelServiceImpl levelService) {
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }
    @Override
    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fish id " + id + " not found"));
    }

    @Override
    public List<Fish> getAllFishes() {
        return fishRepository.findAll();
    }

    @Override
    public Fish addFish(Fish fish) {
        // check if fish name is not already exist
        if(fishRepository.findByName(fish.getName()) != null) {
            throw new ResourceNotFoundException("Fish name " + fish.getName() + " already exist");
        }

        // check if level is already exist
        if(levelService.getLevelById(fish.getLevel().getId()) == null) {
            throw new ResourceNotFoundException("Level id " + fish.getLevel().getId() + " not found");
        }

        return fishRepository.save(fish);
    }

    @Override
    public Fish updateFish(Fish fish, Long id) {
        Fish existingFish = getFishById(id);
        existingFish.setName(fish.getName());
        existingFish.setWeight(fish.getWeight());
        // check if level is already exist
        if(levelService.getLevelById(fish.getLevel().getId()) == null) {
            throw new ResourceNotFoundException("Level id " + fish.getLevel().getId() + " not found");
        }
        existingFish.setLevel(fish.getLevel());
        return fishRepository.save(existingFish);
    }

    @Override
    public void deleteFish(Long id) {
        fishRepository.deleteById(id);
    }
}
