package app.repositories;

import app.model.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MusicRepository extends CrudRepository<Music, String> {

    @Transactional
    void deleteByMusicNameAndMusicArtist(String musicName, int musicArtist);
}
