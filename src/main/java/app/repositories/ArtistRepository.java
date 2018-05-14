package app.repositories;

import app.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    @Transactional
    void deleteByArtistName(String artistName);

    @Transactional
    Artist findByArtistNameLike(String artistName);

}
