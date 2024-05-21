package ru.andr.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.andr.library.models.Book;
import ru.andr.library.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY title", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> indexByAuthor() {
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY author", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN Book ON Person.id = Book.person_id WHERE Book.id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
