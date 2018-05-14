package app.controller;

import app.model.Music;
import app.repositories.MusicRepository;
import app.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
public class HomeController{

    @Autowired
    private StorageService storageService;

    @Autowired
    private MusicRepository musicRepository;

    private ArrayList<String> musics = new ArrayList<String>();

    @GetMapping(path = "/")
    public String get(Model model) {
        return "home";
    }

    @GetMapping("/get_all_musics")
    public String getListFiles(Model model) {
        musics.clear();
        musicRepository.findAll().forEach(music -> musics.add(music.getMusicPath()));

        model.addAttribute("listMusics",
                musics.stream()
                        .map(fileName -> MvcUriComponentsBuilder
                                .fromMethodName(HomeController.class, "getMusic", fileName).build().toString())
                        .collect(Collectors.toList()));
        return "home";
    }

    @GetMapping("/musics/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getMusic(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
