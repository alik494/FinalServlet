package ua.training.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.domain.User;
import ua.training.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserRepoImplTest {

    @Mock
    private UserRepository userRepository;
    private UserRepository UserRepositoryNotMock;

    public UserRepoImplTest() {
        MockitoAnnotations.initMocks(this);
        UserRepositoryNotMock = new UserRepositoryImpl();
    }

    @Test
    public void getOne_Using_Mock_Should_Return_True() {
        given(userRepository.getOne("admin@test.ru")).willReturn(
                new User("admin@test.ru", "sdfd", "sdsd")
        );
        boolean userExists = userRepository.getOne(
                "admin@test.ru"
        ).getPassword().equals("sdsd");

        assertThat(userExists).isTrue();
        verify(userRepository).getOne("admin@test.ru");
    }

    @Test
    public void getOne_ADMIN_Should_Return_True() {

        User user = UserRepositoryNotMock.getOne(
                "admin@mail.com");
        Assert.assertEquals(user.getPassword(), "admin");
    }

    @Test
    public void getOne_Using_Mock_Should_Return_False() {
        given(userRepository.getOne("admin@test.ru")).willReturn(
               null
        );
        boolean userExists = userRepository.getOne(
                "admin@test.ru"
        ) != null;

        assertThat(userExists).isFalse();
    }
    @Test(expected = Exception.class)
    public void getOne_Using_Mock_Should_Throw_Exeption() {
        given(userRepository.getOne(anyString())).willThrow(
                Exception.class
        );
        boolean userExists = userRepository.getOne(
                "admin@test.ru"
        ) != null;

    }

}