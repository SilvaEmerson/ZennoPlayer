package app.controller;

import app.model.Album;
import app.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AlbumController{

    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping(path = "add_album")
    public @ResponseBody String add(@RequestParam String albumName,
                                    @RequestParam int albumYear,
                                    @RequestParam String albumRecorder) {

        try {
            albumRepository.save(new Album(albumName, albumYear, albumRecorder));
            return "Saved";
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping(path = "get_albums")
    public @ResponseBody
    int get() {
        ArrayList<Album> albums = new ArrayList<Album>();
        albumRepository.findAll().forEach(albums::add);
        return albums.size();
    }

    public String update(String param) {
        return null;
    }

    @DeleteMapping(path = "delete_album")
    public @ResponseBody String delete(@RequestParam String albumName) {

        try{
            albumRepository.deleteByAlbumName(albumName);
            return "Deleted";
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
