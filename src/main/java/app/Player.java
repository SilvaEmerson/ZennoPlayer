package app;

import app.model.Album;
import app.model.Artist;
import app.model.Genre;
import app.model.Music;
import app.repositories.AlbumRepository;
import app.repositories.ArtistRepository;
import app.repositories.GenreRepository;
import app.repositories.MusicRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Player {

    private GenreRepository genreRepository;
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private MusicRepository musicRepository;

    private MusicMetadaService musicMetadaService;

    public Player() {}

    public Player(GenreRepository genreRepository, AlbumRepository albumRepository, ArtistRepository artistRepository,
                  MusicRepository musicRepository) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.musicRepository = musicRepository;
    }

    public void setMusicType(InputStream musicFile, String musicType, long musicLength) {
        this.musicMetadaService = new MusicMetadaService(musicFile, musicType, musicLength);
    }

    public void addMusic(InputStream musicFile, String musicPath) throws Exception{
        try{
//            this.musicMetadaService.setMusicFilePath(musicPath);

            String musicTitle = this.musicMetadaService.getMusicTitle();
            String musicDuration = this.musicMetadaService.getDuration();
            String musicFormat = this.musicMetadaService.getMusicFormat();
            int musicLaunchYear = this.musicMetadaService.getMusicDate();
            String musicAlbum = musicMetadaService.getMusicAlbum();
            String musicGenre = musicMetadaService.getMusicGenre();
            String musicArtist = musicMetadaService.getMusicArtist();

            try {
                this.genreRepository.save(new Genre(musicGenre));
                this.albumRepository.save(new Album(musicAlbum));
                this.artistRepository.save(new Artist(musicArtist));
            }catch (Exception e){
                e.printStackTrace();
            }

            int musicAlbumId = this.albumRepository.findByAlbumNameLike(musicAlbum).getAlbumId();
            int musicArtistId = this.artistRepository.findByArtistNameLike(musicArtist).getId();
            this.musicRepository.save(new Music(musicTitle, musicPath, musicDuration, musicFormat, musicLaunchYear,
                    musicArtistId, musicGenre, musicAlbumId));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void moveMusic(String destinyPath, byte[] bytes){
        Path path = Paths.get(destinyPath);
//        File file = new File(destinyPath);
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
