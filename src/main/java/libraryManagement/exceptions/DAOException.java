package libraryManagement.exceptions;

import java.sql.SQLException;

public class DAOException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(SQLException message) {
        super(message);
    }
}
