package app.controller;

import app.Player;
import app.model.Music;
import app.repositories.AlbumRepository;
import app.repositories.ArtistRepository;
import app.repositories.GenreRepository;
import app.repositories.MusicRepository;
import app.services.StorageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class MusicController {

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StorageService storageService;

    private Player player;

    public static final String uploadingdir = System.getProperty("user.dir") + "/Zenno/";

    @GetMapping(path = "get_musics")
    public @ResponseBody Iterable<Music> getMusics(){
        return musicRepository.findAll();
    }

    @PostMapping(path = "/add_music", consumes = {"multipart/form-data"})
    public String addMusic(@RequestParam("musics") MultipartFile[] musics,
                           Model model){

        byte[] musicBytes = null;
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/Músicas");
        System.out.println(path);
        System.out.println(uploadingdir);

        this.player = new Player(genreRepository, albumRepository, artistRepository, musicRepository);

        for (MultipartFile multipartFile: musics){
            try{
                String destinyPath = uploadingdir + File.separator + multipartFile.getOriginalFilename();
                musicBytes = multipartFile.getBytes();

                InputStream musicFile = multipartFile.getInputStream();
                Long musicLength = multipartFile.getSize();
                String musicType = multipartFile.getContentType();
                String originalPath = multipartFile.getOriginalFilename();
//                String musicURI = "data:" + musicType + ";base64, " + new String(Base64.encodeBase64(musicBytes));
                storageService.store(multipartFile);
//                System.out.println(transferFile.toString());

                player.setMusicType(musicFile, musicType, musicLength);
                this.player.addMusic(musicFile, originalPath);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

//        model.addAttribute("musics", musicsURI);
        return "redirect:/get_all_musics";
    }

    @DeleteMapping(path = "delete_music")
    public @ResponseBody String deleteMusic(@RequestParam String musicName,
                                            @RequestParam int musicArtist){
        try{
            this.musicRepository.deleteByMusicNameAndMusicArtist(musicName, musicArtist);
            return "Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
