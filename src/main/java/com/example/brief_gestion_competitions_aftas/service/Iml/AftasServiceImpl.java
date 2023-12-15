package com.example.brief_gestion_competitions_aftas.service.Iml;

import com.example.brief_gestion_competitions_aftas.model.Fish;
import com.example.brief_gestion_competitions_aftas.model.Level;
import com.example.brief_gestion_competitions_aftas.service.AftasService;
import com.example.brief_gestion_competitions_aftas.service.FishService;
import com.example.brief_gestion_competitions_aftas.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AftasServiceImpl implements AftasService {

    private final LevelService levelService;
    private final FishService fishService;

    public AftasServiceImpl(LevelService levelService, FishService fishService) {
        this.levelService = levelService;
        this.fishService = fishService;
    }
    
    @Override
    public void initLevel() {
        // here we will init level
        Stream.of("Level 1", "Level 2", "Level 3", "Level 4", "Level 5","level 6", "Level 7", "Level 8", "Level 9", "Level 10").forEach(level -> {
            Level level1 = new Level();
            level1.setDescription(level);
            level1.setPoint(levelService.getAllLevels().stream().mapToInt(Level::getPoint).max().orElse(100) + 100);
            levelService.addLevel(level1);
        });
    }

    @Override
    public void initFish() {
        // here we will init fish
        Stream.of("Fish 1", "Fish 2", "Fish 3", "Fish 4", "Fish 5","Fish 6", "Fish 7", "Fish 8", "Fish 9", "Fish 10").forEach(fish -> {
            Fish fish1 = new Fish();
            fish1.setName(fish);
            fish1.setWeight(fishService.getAllFishes().stream().mapToDouble(Fish::getWeight).max().orElse(100) + 100);
            fish1.setLevel(levelService.getAllLevels().stream().findAny().orElse(null));
            fishService.addFish(fish1);
        });
    }
}
