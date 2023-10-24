/**
 * 
 */
package com.fssa.librarymanagement.service;

import static org.junit.Assert.assertTrue;
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
import com.fssa.librarymanagement.utils.ConnectionUtil;

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
	void testValidListAllCommentsByBook() {
		assertDoesNotThrow(() -> commentService.listCommentsByBook(2));
	}

	@Test
	@Order(3)
	void testValidUpdateComment() {
		try {
			List<Comment> comments = commentService.listAllComments();
			comment.setDescription("Nice Book!");
			deleteCommentId = comments.get(comments.size() - 1).getCommentId();
			comment.setCommentId(deleteCommentId);
			assertTrue(commentService.updateComment(comment));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not Throw Service Exception");
		}
	}

	@Test
	@Order(4)
	void testValidDeleteComment() {
		try {
			List<Comment> comments = commentService.listAllComments();
			deleteCommentId = comments.get(comments.size() - 1).getCommentId();
			assertTrue(commentService.deleteComment(deleteCommentId));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not Throw Service Exception");
		}
	}

	@Test
	@Order(5)
	void testInValidCreateComment() {
		comment.setDescription(null);
		ServiceException result = assertThrows(ServiceException.class, () -> commentService.createComment(comment));
		assertEquals("Description cannot be empty.", result.getMessage());
	}

	@Test
	@Order(6)
	void testInValidUpdateComment() {
		comment.setDescription("");
		ServiceException result = assertThrows(ServiceException.class, () -> commentService.updateComment(comment));
		assertEquals("Description cannot be empty.", result.getMessage());
	}

	@Test
	@Order(7)
	void testDeleteComment_ServiceExpection() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> commentService.deleteComment(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(8)
	void testListAllComment_ServiceExpection() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> commentService.listAllComments());

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(9)
	void testListCommentByBook_ServiceExpection() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> commentService.listCommentsByBook(0));

		ConnectionUtil.setTestingMode(false);
	}
}
