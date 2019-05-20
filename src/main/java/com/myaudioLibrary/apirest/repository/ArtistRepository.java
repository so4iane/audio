package com.myaudioLibrary.apirest.repository;

import com.myaudioLibrary.apirest.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {

    Artist findById (int id);

    Artist findByNameEquals(String name);

    List<Artist> findByNameContaining (String name);
}
