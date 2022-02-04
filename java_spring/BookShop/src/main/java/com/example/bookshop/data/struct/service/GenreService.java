package com.example.bookshop.data.struct.service;

import com.example.bookshop.data.struct.genre.GenreEntity;
import com.example.bookshop.data.struct.genre.GenreFrontView;
import com.example.bookshop.data.struct.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class GenreService {

    private  final GenreRepository genreRepository;
    private final Logger logger;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        logger = Logger.getLogger(GenreService.class.getName());
    }

    public GenreEntity getGenreEntityById(Integer id) {
        return genreRepository.getGenreEntityById(id);
    }

    public int getBooksCountByGenre(int genreId) {
        return genreRepository.countById(genreId);
    }

    public List<GenreFrontView> getGenresTree() {
        List<GenreEntity> genreEntities = genreRepository.findAll();
        List<GenreFrontView> genreFrontViews = genreEntities.stream().
                map(genre -> {
                    GenreFrontView genreFrontView = new GenreFrontView(genre);
                    int booksCount = getBooksCountByGenre(genre.getId());
                    genreFrontView.setBooksCount(booksCount);
                    return genreFrontView;
                }).
                sorted(Comparator.comparingInt(GenreFrontView::getBooksCount).reversed()).
                collect(Collectors.toList());
        return GenreFrontView.makeTree(genreFrontViews);
    }

    public GenreEntity getGenreEntityBySlug(String genreSlug) {
        return genreRepository.getGenreEntityBySlug(genreSlug);
    }

    public List<GenreEntity> getParents(GenreEntity genre) {
        List<GenreEntity> parents = new ArrayList<>();
        Integer parentId = genre.getParentId();
        if (parentId == null) return parents;
        GenreEntity parent = getGenreEntityById(parentId);
        parents.addAll(getParents(parent));
        parents.add(parent);
        return parents;
    }
}
