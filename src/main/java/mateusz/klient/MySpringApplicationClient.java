package mateusz.klient;
import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mateusz.klient.wsdl.AddBookResponse;
import mateusz.klient.wsdl.BookInfo;
import mateusz.klient.wsdl.DeleteBookResponse;
import mateusz.klient.wsdl.GetAllBooksResponse;
import mateusz.klient.wsdl.GetBookByIdResponse;
import mateusz.klient.wsdl.ServiceStatus;
import mateusz.klient.wsdl.UpdateBookResponse;
@SpringBootApplication
public class MySpringApplicationClient {  
	public static void main(String[] args) {
		SpringApplication.run(MySpringApplicationClient.class, args);
    }       
	@Bean
	CommandLineRunner lookup(BookClient bookClient) {
		return args -> {
			System.out.println(" Znajdź książkę poprzez ID ");
			GetBookByIdResponse bookByIdResponse = bookClient.getBookById(10);
			BookInfo bookInfo = bookByIdResponse.getBookInfo();
		    System.out.println(bookInfo.getBookId() + ", " + bookInfo.getTytul()
	        + ", "  + bookInfo.getAutor() + bookInfo.getRok() + ", " + bookInfo.getCena());
			
			System.out.println("Lista wszystkich książek ---");
			GetAllBooksResponse allBooksResponse = bookClient.getAllBooks();
			allBooksResponse.getBookInfo().stream()
			   .forEach(e -> System.out.println(e.getBookId() + ", "+ e.getTytul()
		        + ", "  + e.getAutor() + e.getRok() + ", " + e.getCena()));
			
			System.out.println("Dodawanie ksiązki ...");
		    String tytul = "Programing in 21st century..";
		    String autor = "Janusz Tracz";
		    Integer rok = Integer.valueOf(2018);
		    BigDecimal cena = BigDecimal.valueOf(69.5);
			AddBookResponse addBookResponse = bookClient.addBook(tytul, autor, rok, cena);
			bookInfo = addBookResponse.getBookInfo();
			if (bookInfo != null) {
			  System.out.println(bookInfo.getBookId() + ", "+ bookInfo.getTytul()
			       + ", "  + bookInfo.getAutor() + bookInfo.getRok() + ", " + bookInfo.getCena());
			}
			ServiceStatus serviceStatus = addBookResponse.getServiceStatus();
			System.out.println("Status: " + serviceStatus.getStatusCode() + 
					", Raport: " + serviceStatus.getMessage());
			
			System.out.println("Aktualizowanie książki");
			bookInfo = new BookInfo();
			bookInfo.setBookId(1);
			bookInfo.setTytul("Java programing under the ground");
			bookInfo.setAutor("Grażyna Tracz");
			bookInfo.setRok(Integer.valueOf(2009));
			bookInfo.setCena(BigDecimal.valueOf(49.3));
			
			UpdateBookResponse updateBookResponse = bookClient.updateBook(bookInfo);
			serviceStatus = updateBookResponse.getServiceStatus();
			System.out.println("Status: " + serviceStatus.getStatusCode() + 
					", Raport: " + serviceStatus.getMessage());
			System.out.println("Usuwanie książki ...");
			long bookId = 3;
			DeleteBookResponse deleteBookResponse = bookClient.deleteBook(bookId);
			serviceStatus = deleteBookResponse.getServiceStatus();
			System.out.println("Status: " + serviceStatus.getStatusCode() + 
					", Raport: " + serviceStatus.getMessage());			
		};
	}	
}         
