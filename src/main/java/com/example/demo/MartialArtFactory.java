package com.example.demo;

import java.util.*;

public class MartialArtFactory {
    private static MartialArtFactory instance;
    private List<MartialArt> allMartialArts;
    
    private MartialArtFactory() {
        createMartialArts();
    }
    
    public static MartialArtFactory getInstance() {
        if (instance == null) {
            instance = new MartialArtFactory();
        }
        return instance;
    }
    
    private void createMartialArts() {
        allMartialArts = Arrays.asList(
            new Karate(),
            new Boxing(),
            new Capoeira(),
            new KungFu(),
            new Judo(),
            new Taekwondo(),
            new Muaythai(),
            new Jujitsu(),
            new Aikido(),
            new Krav()
        );
    }
    
    public List<MartialArt> getAllMartialArts() {
        return new ArrayList<>(allMartialArts);
    }
    
    public List<MartialArt> getRandomMartialArts(int count) {
        List<MartialArt> shuffled = new ArrayList<>(allMartialArts);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, Math.min(count, shuffled.size()));
    }
}