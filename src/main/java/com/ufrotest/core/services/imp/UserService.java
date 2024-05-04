package com.ufrotest.core.services.imp;

import com.ufrotest.constants.UserData;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.user.build.UserValidationChain;
import com.ufrotest.core.services.exceptions.validator.imp.user.imp.UserAlreadyExists;
import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.model.UserDTO;
import com.ufrotest.core.repositories.UserRepo;
import com.ufrotest.constants.UserType;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserService{
    private final UserRepo userRepo;
    private final UserValidationChain validator;
    private final BookService bookService;

    public void saveUser(UserDTO user) throws UserValidationException {
        new UserAlreadyExists(userRepo).validate(user);
        validator.validate(user);
        userRepo.save(user);
    }

    public UserDTO findUserById(int id) throws UserValidationException {
        UserDTO user = userRepo.findById(id);
        validator.validate(user);
        return user;
    }

    public UserDTO getUserByName(String username) throws UserValidationException {
        UserDTO user = userRepo.findAll().stream().filter(userDTO -> userDTO.username().equals(username)).findFirst().orElse(null);
        validator.validate(user);
        return user;
    }

    public boolean exists(int userId) throws UserValidationException {
        UserDTO user = findUserById(userId);
        validator.validate(user);
        return userRepo.findAll()
                .stream()
                .anyMatch(userDTO -> userDTO.id() == user.id() || userDTO.username().equals(user.username()));
    }

    public boolean exists(String username) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username));
    }

    public boolean login(String username, String password) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.password().equals(password));
    }

    public boolean isAdmin(UserDTO user) throws UserValidationException {
        return equalsTo(user, UserType.ADMIN);
    }

    public boolean isAdminById(int id) {
        return equalsTo(id, UserType.ADMIN);
    }

    public boolean isAdminByUsername(String username) {
        return equalsTo(username, UserType.ADMIN);
    }

    public boolean isStudent(UserDTO user) throws UserValidationException {
        return equalsTo(user, UserType.STUDENT);
    }

    public boolean isStudentById(int id) {
        return equalsTo(id, UserType.STUDENT);
    }

    public boolean isStudentByUsername(String username) {
        return equalsTo(username, UserType.STUDENT);
    }

    public boolean isTeacher(UserDTO user) throws UserValidationException {
        return equalsTo(user, UserType.TEACHER);
    }

    public boolean isTeacherById(int id) {
        return equalsTo(id, UserType.TEACHER);
    }

    public boolean isTeacherByUsername(String username) {
        return equalsTo(username, UserType.TEACHER);
    }

    public boolean isLibrarian(UserDTO user) throws UserValidationException {
        return equalsTo(user, UserType.LIBRARIAN);
    }

    public boolean isLibrarianById(int id) {
        return equalsTo(id, UserType.LIBRARIAN);
    }

    public boolean isLibrarianByUsername(String username) {
        return equalsTo(username, UserType.LIBRARIAN);
    }

    private boolean equalsTo(int id, UserType userType){
        return userRepo.findAll().stream().anyMatch(user -> user.id() == id && user.type().equals(userType));
    }

    private boolean equalsTo(String username, UserType userType){
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.type().equals(userType));
    }

    private boolean equalsTo(UserDTO user, UserType type) throws UserValidationException {
        validator.validate(user);
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id() && userDTO.type().equals(type));
    }

    public void borrowBook(int userId, int bookId) throws UserValidationException, BookValidationException {
        UserDTO user = findUserById(userId);
        validator.validate(user);

        if (user.borrowedBooksHistory().stream().anyMatch(book -> book.id() == bookId)){
            throw new BookValidationException("Book already loaned");
        }

        BookDTO bookDTO = bookService.findById(bookId);
        ArrayList<BookDTO> loanList = new ArrayList<>(user.borrowedBooksHistory());

        loanList.add(bookDTO);

        UserDTO updatedUser = new UserDTO(
                user.id(),
                user.username(),
                user.password(),
                user.type(),
                loanList,
                user.reservationHistory(),
                user.ratings()
        );

        userRepo.update(
                updatedUser.id(),
                updatedUser
        );

        updateUserBookList(userId);
    }

    public void addReservationBook(int userId, int bookId) throws UserValidationException, BookValidationException {
        UserDTO user = findUserById(userId);
        validator.validate(user);
        ArrayList<BookDTO> reservationList = new ArrayList<>(user.reservationHistory());
        reservationList.add(bookService.findById(bookId));
        userRepo.update(
                user.id(),
                new UserDTO(
                        user.id(),
                        user.username(),
                        user.password(),
                        user.type(),
                        user.borrowedBooksHistory(),
                        reservationList,
                        user.ratings()
                )
        );
    }

    public void addBookRatings(int userId, int bookId, int rating) throws UserValidationException, BookValidationException {
        bookService.addRating(bookId, rating);

        UserDTO user = findUserById(userId);

        validator.validate(user);


        ArrayList<BookDTO> ratings = new ArrayList<>(user.ratings());

        ratings.add(bookService.findById(bookId));

        userRepo.update(
                user.id(),
                new UserDTO(
                        user.id(),
                        user.username(),
                        user.password(),
                        user.type(),
                        user.borrowedBooksHistory(),
                        user.reservationHistory(),
                        ratings
                )
        );
        updateUserBookList(userId);
    }

    public void removeBorrowBook(int userId, int bookId) throws UserValidationException, BookValidationException {
        UserDTO user = findUserById(userId);
        validator.validate(user);
        ArrayList<BookDTO> loanList = new ArrayList<>(user.borrowedBooksHistory());
        loanList.remove(bookService.findById(bookId));
        UserDTO newUser = new UserDTO(
                user.id(),
                user.username(),
                user.password(),
                user.type(),
                loanList,
                user.reservationHistory(),
                user.ratings()
        );
        userRepo.update(
                user.id(),
                newUser
        );
    }

    public void addBookComment(int userId, int bookId, String comment) throws UserValidationException, BookValidationException {
        UserDTO user = findUserById(userId);
        validator.validate(user);

        bookService.addComment(bookId, comment);

        updateUserBookList(userId);
    }

    public void updateUserBookList(int id) {
        try {
            List<BookDTO> books = bookService.findAll();
            UserDTO userDTO = userRepo.findById(id);
            validator.validate(userDTO);

            List<BookDTO> loanBooks = filterBooks(userDTO.borrowedBooksHistory(), books);
            List<BookDTO> reservationBooks = filterBooks(userDTO.reservationHistory(), books);
            List<BookDTO> ratedBooks = filterBooks(userDTO.ratings(), books);

            UserDTO newUser = new UserDTO(
                    userDTO.id(),
                    userDTO.username(),
                    userDTO.password(),
                    userDTO.type(),
                    loanBooks,
                    reservationBooks,
                    ratedBooks
            );

            userRepo.update(newUser.id(), newUser);
            UserData.getInstance().setUserLogged(newUser);
        } catch (BookValidationException | UserValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public String format(UserDTO userDTO){
        return "Username: " + userDTO.username() + "\n" +
                "Type: " + userDTO.type() + "\n" +
                "Loan History: \n" + formatHistory(userDTO.borrowedBooksHistory()) + "\n" +
                "Reservation History: \n" + formatHistory(userDTO.reservationHistory()) + "\n" +
                "Ratings: \n" + formatHistory(userDTO.ratings()) + "\n";
    }

    private List<String> formatHistory(List<BookDTO> bookDTOS) {
        return bookDTOS.stream().map(bookDTO -> '\n'+bookService.format(bookDTO)).toList();
    }

    private List<BookDTO> filterBooks(List<BookDTO> userBooks, List<BookDTO> allBooks) {
        return userBooks.stream()
                .flatMap(userBook -> allBooks.stream().filter(book -> book.id() == userBook.id()))
                .toList();
    }

}
