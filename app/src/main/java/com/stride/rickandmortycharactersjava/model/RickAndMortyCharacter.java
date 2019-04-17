package com.stride.rickandmortycharactersjava.model;

import java.util.Objects;

public final class RickAndMortyCharacter {
    public final long id;
    public final String name;
    public final String image;

    public RickAndMortyCharacter(long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RickAndMortyCharacter that = (RickAndMortyCharacter) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, image);
    }

    @Override
    public String toString() {
        return "RickAndMortyCharacter{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
