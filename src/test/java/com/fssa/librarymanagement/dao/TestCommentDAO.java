/**
 *
 */
package com.fssa.librarymanagement.dao;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.utils.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
class TestCommentDAO {
	private CommentDAO commentDAO;

	@BeforeEach
	public void setUp() {
		commentDAO = new CommentDAO();
	}

	@Test
	void testCreateComment() {
		ConnectionUtil.setTestingMode(true);
		Comment comment = new Comment();
		assertThrows(DAOException.class, () -> commentDAO.createComment(comment));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testDeleteComment() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> commentDAO.deleteComment(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testUpdateComment() {
		ConnectionUtil.setTestingMode(true);
		Comment comment = new Comment();
		assertThrows(DAOException.class, () -> commentDAO.updateComment(comment));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testListAllComment() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> commentDAO.listAllComments());

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testListCommentByBook() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> commentDAO.listCommentByBook(0));

		ConnectionUtil.setTestingMode(false);
	}
}
