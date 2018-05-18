package app;

import app.model.Album;
import app.model.Artist;
import app.model.Genre;
import app.model.Music;
import app.repositories.AlbumRepository;
import app.repositories.ArtistRepository;
import app.repositories.GenreRepository;
import app.repositories.MusicRepository;
import app.services.MusicMetadaService;

import java.io.InputStream;

/**
 * Padrão de Projeto: Facade
 */

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

    public void setMusicType(InputStream musicFile, String musicType) {
        this.musicMetadaService = new MusicMetadaService(musicFile, musicType);
    }

    public void addMusic(InputStream musicFile, String musicPath) throws Exception{
        try{
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
}
