package app.controller;

import app.model.Genre;
import app.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GenreController{

    @Autowired
    private GenreRepository genreRepository;


    @PostMapping(path = "add_genre")
    public @ResponseBody String add(@RequestParam  String genreName) {
        try {
            Genre genre = new Genre(genreName);
            genreRepository.save(genre);
            return "Saved";
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }


    @GetMapping(path = "/get_genres")
    public @ResponseBody Iterable<Genre> get() {
        return genreRepository.findAll();
    }


    @DeleteMapping(path = "/delete_genre")
    public @ResponseBody String delete(@RequestParam String genreName) {
        try {
            genreRepository.deleteById(genreName);
            return "Deleted";
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }

    }
}
