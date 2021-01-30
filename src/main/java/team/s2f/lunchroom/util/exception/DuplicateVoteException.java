package team.s2f.lunchroom.util.exception;

public class DuplicateVoteException extends RuntimeException{

    public DuplicateVoteException(String message) {
        super(message);
    }
}
