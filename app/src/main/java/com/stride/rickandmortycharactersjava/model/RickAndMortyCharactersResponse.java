package com.stride.rickandmortycharactersjava.model;

import java.util.List;
import java.util.Objects;

public class RickAndMortyCharactersResponse {
    public final List<RickAndMortyCharacter> results;

    public RickAndMortyCharactersResponse(List<RickAndMortyCharacter> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RickAndMortyCharactersResponse that = (RickAndMortyCharactersResponse) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    @Override
    public String toString() {
        return "RickAndMortyCharactersResponse{" +
                "results=" + results +
                '}';
    }
}
