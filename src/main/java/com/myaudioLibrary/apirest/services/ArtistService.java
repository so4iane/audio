package com.myaudioLibrary.apirest.services;

import com.myaudioLibrary.apirest.model.Artist;
import com.myaudioLibrary.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    //Afficher un artiste
    public Artist findArtist(Long id) {
        Artist artist = artistRepository.findOne(id);
        if (artist == null) {
            throw new EntityNotFoundException("L'id : " + id + " ne correspont à aucun arstist");
        }
        return artist;
    }

    //Recherche Artiste
    public List<Artist> findByName(String name) {
        List<Artist> artist = artistRepository.findByNameContaining(name);
        if (artist == null) {
            throw new EntityNotFoundException("Artiste non trouvé");
        }

        //if (artistRepository.findByNameContaining(name).isEmpty()){
        //    throw new EntityExistsException("Aucun artist ne correspond au nom suivant : " +name);
        //}
        return artistRepository.findByNameContaining(name);

    }

    //Count des artistes
    public long countArtist() {
        return artistRepository.count();
    }

    //Liste des Artistes
    public Page<Artist> pagingArtist(int page, int size, String sortProperty, String sortDirection) {
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        if (page < 0) {
            throw new IllegalArgumentException("Le numéro de page doit être supérieur à 0");
        } else if (page > countArtist() / size) {
            throw new IllegalArgumentException("Le numéro de page est trop élevé");
        } else if (size > countArtist()) {
            throw new IllegalArgumentException("La taille est trop grande");
        }
        return artistRepository.findAll(pageRequest);
    }

    //Creation d'un artiste
    public Artist createArtist(Artist artist) {
        if (artistRepository.findOne(artist.getId()) != null) {
            throw new EntityExistsException("L'id : " + artist.getId() + "appartient déjà à un artiste");
        } else if (artistRepository.findByNameEquals(artist.getName()) != null) {
            throw new EntityExistsException("Le nom : " + artist.getName() + " appartient déjà à un artiste");
        }

        return artistRepository.save(artist);
    }

    //Modifier Artiste
    public Artist updateArtist(Long id, Artist artist) {
        Artist artistModifie = artistRepository.findOne(id);
        if (artist == null) {
            throw new EntityNotFoundException("Artiste inexistant");
        }
        artistModifie.setName(artist.getName());
        artistModifie.setAlbums(artist.getAlbums());
        return artistRepository.save(artistModifie);
    }

    //Supprimer Artiste
    public void deleteArtist(Long id) {
        Artist artist = artistRepository.findOne(id);
        if (artist == null) {
            throw new EntityNotFoundException("Artiste inexistant");
        }
        artistRepository.delete(artist);
    }

}

