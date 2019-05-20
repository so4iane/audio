package com.myaudioLibrary.apirest.services;

import com.myaudioLibrary.apirest.model.Album;
import com.myaudioLibrary.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    //Creation Album
    public Album createAlbum(Album album) {

        if (album == null) {
            throw new IllegalArgumentException("Album inexistant");
        }
        return albumRepository.save(album);
    }

    //Suppression Album
    public void deleteAlbum (Long id){
        Album album = albumRepository.findOne(id);
        if (album == null) {
            throw new EntityNotFoundException("L'id : " + id + " ne correspond à aucun album");
        }
        albumRepository.delete(album);
    }
}
