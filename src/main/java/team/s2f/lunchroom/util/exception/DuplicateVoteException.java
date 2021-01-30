package team.s2f.lunchroom.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.OK, reason = "Try tomorrow.") //304
public class DuplicateVoteException extends RuntimeException{

    public DuplicateVoteException(String message) {
        super(message);
    }
}
