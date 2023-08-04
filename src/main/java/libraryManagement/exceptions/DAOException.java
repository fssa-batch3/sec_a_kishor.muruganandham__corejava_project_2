package libraryManagement.exceptions;

import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException(SQLException message) {
        super(message);
    }
}
