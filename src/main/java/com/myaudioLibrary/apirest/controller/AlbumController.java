package com.myaudioLibrary.apirest.controller;

import com.myaudioLibrary.apirest.model.Album;
import com.myaudioLibrary.apirest.repository.AlbumRepository;
import com.myaudioLibrary.apirest.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    private AlbumService albumService;


//Ajout Album
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Album newAlbum(@RequestBody Album album) {
        return albumService.createAlbum(album);
    }

//Suppression Album
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void supprAlbum(@PathVariable(value = "id") Long id) {
        albumService.deleteAlbum(id);
    }

}
