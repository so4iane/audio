package com.myaudioLibrary.apirest.controller;

import com.myaudioLibrary.apirest.model.Artist;
import com.myaudioLibrary.apirest.repository.ArtistRepository;
import com.myaudioLibrary.apirest.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    private ArtistService artistService;

//Afficher un artiste

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Artist findArtist(@PathVariable(name = "id") Long id) {
        return artistService.findArtist(id);
    }

//Recherche par nom

    @RequestMapping(params = "name", method = RequestMethod.GET, produces = "application/json")
    public List<Artist> findByName(@RequestParam(value = "name") String name) {

        return artistService.findByName(name);
    }

//Liste des Artistes
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Artist> findAllArtist(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection, @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty) {
        return artistService.pagingArtist(page, size, sortProperty, sortDirection);
    }

//Creation d'un artiste
     @RequestMapping(value = "", method = RequestMethod.POST)
     public Artist createArtist(@RequestBody Artist artist) {
     return artistService.createArtist(artist);
     }

//Modifier Artiste
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Artist modifyArtist(@PathVariable(value = "id") Long id, @RequestBody Artist artist) {
        return artistService.updateArtist(id, artist);
    }

//Supprimer Artiste
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void supprArtist(@PathVariable(value = "id") Long id) {
        artistService.deleteArtist(id);
        }

    //Compter ke nombre d'artistes
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long countArtist() {
        return artistService.countArtist();
    }

}
