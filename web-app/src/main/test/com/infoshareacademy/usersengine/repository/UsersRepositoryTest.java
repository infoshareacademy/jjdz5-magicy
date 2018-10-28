package com.infoshareacademy.usersengine.repository;

import com.infoshareacademy.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UserRepository Test")
class UsersRepositoryTest {

    private static final String TEST_NAME = "admin";
    private static final String TEST_PASSWORD = "p";

    private UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        usersRepository = new UsersRepository();
        usersRepository.init();
    }

    @Test
    @DisplayName("Should return appropriate User by given name and password.")
    void returnsAppropriateUserByGivenNameAndPasswordInFindUserByUsernameAndPasswordMethod() {

        // act
        User userFromRepository = usersRepository.findUserByUsernameAndPassword(TEST_NAME, TEST_PASSWORD).get();

        // assert
        assertThat(userFromRepository.getName()).isEqualTo(TEST_NAME);
        assertThat(userFromRepository.getPassword()).isEqualTo(TEST_PASSWORD);
    }

}