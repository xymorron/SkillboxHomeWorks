package com.example.bookshop.data.struct.genre;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
@RequiredArgsConstructor
public class GenreFrontView {

    private final int id;
    private final Integer parentId;
    private final String slug;
    private final String name;

    @Setter
    private int booksCount;

    private List<GenreFrontView> subgenres;

    private static final Logger logger = Logger.getLogger(GenreFrontView.class.getName());

    public GenreFrontView(GenreEntity genre) {
        this.id = genre.getId();
        this.parentId = genre.getParentId();
        this.slug = genre.getSlug();
        this.name = genre.getName();
        this.subgenres = new ArrayList<>();
    }

    public static List<GenreFrontView> makeTree(List<GenreFrontView> genres) {
        List<GenreFrontView> genresTree = new ArrayList<>();

        for (GenreFrontView genre : genres) {
            if (genre.getParentId() == null)
                genresTree.add(genre);
            else {
                GenreFrontView parent = findParent(genres, genre.getParentId());
                if (parent == null)
                    logger.warning("Unable to find parent for genre with id " + genre.getId());
                else
                    parent.addSubgenre(genre);
            }
        }
        logger.info("genresTree formed " + genresTree);
        return genresTree;
    }

    public String getTagClass() {
        return this.hasGrandchild() ? "Tags_embed" : "";
    }

    public boolean hasGrandchild() {
        return subgenres.stream().anyMatch(GenreFrontView::hasChild);
    }

    public boolean hasChild() {
        return !subgenres.isEmpty();
    }

    private static GenreFrontView findParent(List<GenreFrontView> genres, int parentId) {
        for (GenreFrontView genre: genres) {
            if (genre.getId() == parentId)
                return genre;
        }
        return null;
    }

    public void addSubgenre(GenreFrontView genre) {
        subgenres.add(genre);
    }

    @Override
    public String toString() {
        // форма этого метода принципиальна для отладки, через Lombok выходит не читабельно!!!
        return "{" +
                "name='" + name + '\'' +
                ", tag='" + this.getTagClass() + '\'' +
                ", booksCount=" + booksCount +
                ", subgenres=" + subgenres +
                '}';
    }
}
