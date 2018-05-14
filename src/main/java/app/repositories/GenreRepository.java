package app.repositories;

import app.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// É especificado em CrudRepository o tipo de entidade(Person) e do ID(Long)
// Herdando de CrudRepository ganha-se vários métodos para trabalhar com a persistência de 'Person',
// incluindo métodos para excluir, salvar, procurar entidades 'Person'
public interface GenreRepository extends CrudRepository<Genre, String> {

    List<Genre> findByGenreName(String genreName);

}
// Em aplicações Java normais, se esperaria que se implementasse uma classe com esta interface
// Mas isso é o que faz Spring Data JPA tão poderoso: Você não precisa implementar a interface
// Spring Data JPA cria uma implementação quando se executa a aplicação