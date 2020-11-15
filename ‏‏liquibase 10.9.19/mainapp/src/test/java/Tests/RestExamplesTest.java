package Tests;

import com.app.LiquibaseApplication;
import com.entities.*;
import com.repositories.AuthorDao;
import com.repositories.BookDao;
import com.repositories.NoteDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = LiquibaseApplication.class)
public class RestExamplesTest {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void PostTest() throws InterruptedException {
        Note note = new Note();
        note.setContent("Content");
        note.setTitle("title");
        Note save = noteDao.save(note);
        Thread.sleep(2000); // simulated work
        save = noteDao.save(note);
//        restTemplate.postForObject(WsAddressConstants.restExamplesFullUrl + "post", "ss", Void.class);
    }

    @Test
    public void BookTest() {
        String authorName = "authorName";
        Author author = authorDao.getByName(authorName);
        if (author == null) {
            author = authorDao.save(new Author(authorName));
        }

        author.getPhoneNumber().add(+97250758);
        author.getPhoneNumber().add(+97250754);

        Address home_address = new Address();
        home_address.setStreet("2nd cross");
        home_address.setCountry("India");

        Address bussiness_address = new Address();
        bussiness_address.setStreet("8th cross");
        bussiness_address.setCountry("India");

        author.addAddress(AddressTypeEnum.Home.toString(), home_address);
        author.addAddress(AddressTypeEnum.business.toString(), bussiness_address);

        Author authorSaved = authorDao.save(author);

        String book1Name = "bookName1";
        Book book1 = bookDao.getByName(book1Name);
        if (book1 == null) {
             book1 = bookDao.save(new Book(book1Name, author));
        }


        String book2Name = "bookName2";
        Book book2 = bookDao.getByName(book2Name);
        if (book2 == null) {
            book2 = bookDao.save(new Book(book2Name, author));
        }

    }

}
