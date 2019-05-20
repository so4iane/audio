package com.myaudioLibrary.apirest.repository;

import com.myaudioLibrary.apirest.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
}
