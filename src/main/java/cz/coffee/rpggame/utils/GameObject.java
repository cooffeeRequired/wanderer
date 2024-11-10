package cz.coffee.rpggame.utils;


import cz.coffee.rpggame.models.GameEntity;
import cz.coffee.rpggame.models.Monster;
import cz.coffee.rpggame.structures.GameStructure;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

@Getter
public class GameObject {
    private final Deque<GameStructure> structures;
    private final Deque<GameEntity> entities;

    public GameObject() {
        structures = new ArrayDeque<>();
        entities = new ArrayDeque<>();
    }

    public void clear() {
        structures.clear();
        entities.clear();
    }

    public void add(GameStructure structure) {
        this.structures.add(structure);
    }

    public void add(GameEntity entity) {
        this.entities.add(entity);
    }

    public Deque<Monster> getMonsters() {
        return this.entities.stream()
                .filter((e) -> e instanceof Monster)
                .map(e -> (Monster) e)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
