package com.example.demo;

import java.util.List;

public interface PlayerStrategy {
    List<Strike> selectAttacks(List<MartialArt> availableArts, MartialArt selectedArt);
}