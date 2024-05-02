package com.ufrotest.services;

import com.ufrotest.model.UserDTO;
import com.ufrotest.repositories.UserRepo;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.validator.user.UserValidationChain;
import com.ufrotest.utils.UserType;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserValidationChain validator;

    public void saveUser(UserDTO user) throws UserValidationException {
        userRepo.save(user);
    }

    public void deleteUser(UserDTO user) throws UserValidationException {
        userRepo.delete(user);
    }

    public void updateUser(UserDTO user) throws UserValidationException {
        userRepo.update(user.id(),user);
    }

    public UserDTO getUserById(int id) {
        return userRepo.findById(id);
    }

    public UserDTO getUserByName(String username) {
        return userRepo.findAll().stream().filter(user -> user.username().equals(username)).findFirst().orElseThrow();
    }

    public boolean exists(UserDTO user) throws UserValidationException {
        return userRepo.findAll()
                .stream()
                .anyMatch(userDTO -> userDTO.id() == user.id() || userDTO.username().equals(user.username()));
    }

    public boolean exists(String username) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username));
    }

    public boolean exists(int id) {
        return userRepo.findAll().stream().anyMatch(user -> user.id() == id);
    }

    public boolean login(String username, String password) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.password().equals(password));
    }

    public boolean isAdmin(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id() && userDTO.type().equals(UserType.ADMIN));
    }

    public boolean isAdminById(int id) {
        return userRepo.findAll().stream().anyMatch(user -> user.id() == id && user.type().equals(UserType.ADMIN));
    }

    public boolean isAdminByUsername(String username) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.type().equals(UserType.ADMIN));
    }

    public boolean isStudent(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id() && userDTO.type().equals(UserType.STUDENT));
    }

    public boolean isStudentById(int id) {
        return userRepo.findAll().stream().anyMatch(user -> user.id() == id && user.type().equals(UserType.STUDENT));
    }

    public boolean isStudentByUsername(String username) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.type().equals(UserType.STUDENT));
    }

    public boolean isTeacher(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id() && userDTO.type().equals(UserType.TEACHER));
    }

    public boolean isTeacherById(int id) {
        return userRepo.findAll().stream().anyMatch(user -> user.id() == id && user.type().equals(UserType.TEACHER));
    }

    public boolean isTeacherByUsername(String username) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.type().equals(UserType.TEACHER));
    }

    public boolean isLibrarian(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id() && userDTO.type().equals(UserType.LIBRARIAN));
    }

    public boolean isLibrarianById(int id) {
        return userRepo.findAll().stream().anyMatch(user -> user.id() == id && user.type().equals(UserType.LIBRARIAN));
    }

    public boolean isLibrarianByUsername(String username) {
        return userRepo.findAll().stream().anyMatch(user -> user.username().equals(username) && user.type().equals(UserType.LIBRARIAN));
    }




}
