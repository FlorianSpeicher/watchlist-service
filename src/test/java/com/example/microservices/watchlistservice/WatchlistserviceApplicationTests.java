package com.example.microservices.watchlistservice;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DisplayName("MovieControllerTest:")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class WatchlistserviceApplicationTests {

	@Test
	@Order(1)
	void listAllWatchLists() {
	}

	@Test
	@Order(2)
	void listWatchlistByID(){

	}

	@Test
	@Order(3)
	void findWatchListByName(){

	}

	@Test
	@Order(4)
	void findActor(){

	}

	@Test
	@Order(5)
	void findRegisseur(){

	}

	@Test
	@Order(6)
	void findMovieWithTitle(){

	}

	@Test
	@Order(7)
	void createWatchlist(){

	}

	@Test
	@Order(8)
	void addToWatchList(){

	}

	@Test
	@Order(9)
	void commentMovieById(){

	}

	@Test
	@Order(10)
	void listAllComments(){

	}

	@Test
	@Order(11)
	void deleteCommentById(){

	}

	@Test
	@Order(12)
	void deleteAllComments(){

	}

	@Test
	@Order(13)
	void deleteFromWatchlist(){

	}

	@Test
	@Order(14)
	void deleteAllFromWatchList(){

	}

	@Test
	@Order(15)
	void deleteWatchListByID(){

	}

	@Test
	@Order(16)
	void deleteAllWatchLists(){

	}

	@Test
	@Order(17)
	void addUser(){

	}

	@Test
	@Order(18)
	void login(){

	}

	@Test
	@Order(19)
	void checkToken(){

	}

	@Test
	@Order(20)
	void deleteUser(){

	}

	@Test
	@Order(21)
	void logout(){

	}

	//Admin Methods

	@Test
	@Order(22)
	void adminDeleteUser(){

	}

	@Test
	@Order(23)
	void adminDeleteWatchList(){

	}

	@Test
	@Order(24)
	void adminDeleteComment(){

	}

	@Test
	@Order(25)
	void adminFindAllWatchLists(){

	}

	@Test
	@Order(26)
	void adminFindAllComments(){

	}

	@Test
	@Order(27)
	void adminAddMovie(){

	}

	@Test
	@Order(28)
	void adminRemoveMovie(){

	}

	@Test
	@Order(29)
	void adminAddActor(){

	}

	@Test
	@Order(30)
	void adminUpdateMovie(){

	}

	@Test
	@Order(31)
	void adminAddWatchList(){

	}

	@Test
	@Order(32)
	void adminAddUser(){

	}

	@Test
	@Order(33)
	void adminAddComment(){

	}

}
