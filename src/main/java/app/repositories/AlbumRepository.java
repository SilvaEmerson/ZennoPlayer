package app.repositories;

import app.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {


    @Transactional
    Album findByAlbumNameLike(String albumName);

    @Transactional
    void deleteByAlbumName(String albumName);
}
