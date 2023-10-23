/**
 * 
 */
package com.fssa.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.model.User;

/**
 * 
 */
class TestCommentService {

	private CommentService commentService;
	private Comment comment;
	private int deleteCommentId;

	@BeforeEach
	public void setUp() {
		commentService = new CommentService();
		comment = new Comment();
		User user = new User();
		user.setUserId(1);
		Book book = new Book();
		book.setBookId(2);
		comment.setBook(book);
		comment.setUser(user);
		comment.setDescription("This Book is excellent to read!!");
	}

	@Test
	@Order(1)
	void testValidCreateComment() {
		assertDoesNotThrow(() -> commentService.createComment(comment));
	}

	@Test
	@Order(2)
	void testValidUpdateComment() {
		comment.setDescription("Nice Book!");
		assertDoesNotThrow(() -> commentService.updateComment(comment));
	}

	@Test
	@Order(3)
	void testInValidCreateComment() {
		comment.setDescription(null);
		ServiceException result = assertThrows(ServiceException.class, () -> commentService.createComment(comment));
		assertEquals("Description cannot be empty.", result.getMessage());
	}

	@Test
	@Order(4)
	void testInValidUpdateComment() {
		comment.setDescription("");
		ServiceException result = assertThrows(ServiceException.class, () -> commentService.updateComment(comment));
		assertEquals("Description cannot be empty.", result.getMessage());
	}

	@Test
	@Order(3)
	void testValidListAllCommentsByBook() {
		assertDoesNotThrow(() -> commentService.listCommentsByBook(5));
	}

	@Test
	@Order(4)
	void testValidListAllComments() {
		try {
			List<Comment> comments = commentService.listAllComments();
			deleteCommentId = comments.size() - 1;
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not Throw Service Exception");
		}
	}

	@Test
	@Order(5)
	void testValidDeleteComment() {
		assertDoesNotThrow(() -> commentService.deleteComment(deleteCommentId));
	}
}
