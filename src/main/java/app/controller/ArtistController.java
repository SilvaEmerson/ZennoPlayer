package app.controller;

import app.model.Artist;
import app.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping(path = "get_artists")
    public @ResponseBody
    int getArtists(){
        ArrayList<Artist> artists = new ArrayList<Artist>();
        artistRepository.findAll().forEach(artists::add);

        for(Artist art: artists){
            System.out.println(art.toString());
        }
        return artists.size();
    }

    @PostMapping(path = "add_artist")
    public @ResponseBody String addArtist(@RequestParam String artistName,
                                          @RequestParam int artistAge,
                                          @RequestParam String artistOriginCountry){

        try {
            this.artistRepository.save(new Artist(artistName, artistAge, artistOriginCountry));
            return "Saved!";
        }catch (Exception e){
            return "Not saved, error: " + e.getMessage();
        }
    }

    @DeleteMapping(path = "/delete_artist")
    public @ResponseBody String deleteArtist(@RequestParam String artistName){
        try{
            this.artistRepository.deleteByArtistName(artistName);
            return "Deleted";
        }catch (Exception e){
            return "Not deleted, error: " + e.getMessage();
        }
    }
}
