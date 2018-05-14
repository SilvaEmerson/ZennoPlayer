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
//    @Transactional
//    @Modifying
//    @Query("delete from Album where  albumGenre = :genre and albumName = :albumName")
//    void deleteByNameAndGenre(@Param("albumName") String albumName, @Param("genreName") Genre genreName);
//    Album findAlbumByAlbumNameAndAlbumGenre(String albumName, Genre genreName);
//    void deleteByAlbumGenreAndAlbumName(Genre albumGenre, String albumName);
}
