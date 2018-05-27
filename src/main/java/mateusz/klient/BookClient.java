package mateusz.klient;

import java.math.BigDecimal;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import mateusz.klient.wsdl.AddBookRequest;
import mateusz.klient.wsdl.AddBookResponse;
import mateusz.klient.wsdl.BookInfo;
import mateusz.klient.wsdl.DeleteBookRequest;
import mateusz.klient.wsdl.DeleteBookResponse;
import mateusz.klient.wsdl.GetAllBooksRequest;
import mateusz.klient.wsdl.GetAllBooksResponse;
import mateusz.klient.wsdl.GetBookByIdRequest;
import mateusz.klient.wsdl.GetBookByIdResponse;
import mateusz.klient.wsdl.UpdateBookRequest;
import mateusz.klient.wsdl.UpdateBookResponse;

public class BookClient extends WebServiceGatewaySupport  {
	public GetBookByIdResponse getBookById(long bookId) {
		GetBookByIdRequest request = new GetBookByIdRequest();
		request.setBookId(bookId);
		GetBookByIdResponse response = (GetBookByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ksiegarnia/getBookByIdRequest"));
		return response;
	}
	public GetAllBooksResponse getAllBooks() {
		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = (GetAllBooksResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ksiegarnia/getAllBooksRequest"));
     	return response;
	}	
	public AddBookResponse addBook(String tytul, String autor, Integer rok, BigDecimal cena) {
		AddBookRequest request = new AddBookRequest();
		request.setTytul(tytul);
		request.setAutor(autor);
		request.setRok(rok);
		request.setCena(cena);
		AddBookResponse response = (AddBookResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ksiegarnia/addBookRequest"));
     	return response;
	}	
	public UpdateBookResponse updateBook(BookInfo bookInfo) {
		UpdateBookRequest request = new UpdateBookRequest();
		request.setBookInfo(bookInfo);
		UpdateBookResponse response = (UpdateBookResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ksiegarnia/updateBookRequest"));
     	return response;
	}	
	public DeleteBookResponse deleteBook(long bookId) {
		DeleteBookRequest request = new DeleteBookRequest();
		request.setBookId(bookId);
		DeleteBookResponse response = (DeleteBookResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ksiegarnia/deleteBookRequest"));
     	return response;
	}		
}

