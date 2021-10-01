public class InvalidPetException extends RuntimeException {
    public InvalidPetException() {
        this("Your pet is invalid!");
    }

    public InvalidPetException(String s) {
        super(s);
    }
}
