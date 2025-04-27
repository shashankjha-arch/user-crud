package com.user.curd.user_crud.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.user.curd.user_crud.data.entity.UserDetailsEntity;
import com.user.curd.user_crud.data.repository.UserDetailsRepository;
import com.user.curd.user_crud.dto.request.LoginRequest;
import com.user.curd.user_crud.dto.request.SignUpRequest;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class LoginServiceTest {
    @Autowired
    private LoginService loginService;

    @MockitoBean
    private UserDetailsRepository userDetailsRepository;

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest)")
    void testSignUp() {
        // Arrange
        when(userDetailsRepository.findByEmail(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("U.U.U@U.U.U.UUUU"));

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setEmail("shashank@gmail.com");
        signUpRequest.setFirstName("Shashank");
        signUpRequest.setLastName("Kumar");
        signUpRequest.setPassword("shaasha");
        signUpRequest.setPhone(1);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
    }

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <ul>
     *   <li>Given {@code jane.doe@example.org}.</li>
     *   <li>When {@link SignUpRequest} (default constructor) Email is {@code jane.doe@example.org}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest); given 'jane.doe@example.org'; when SignUpRequest (default constructor) Email is 'jane.doe@example.org'")
    void testSignUp_givenJaneDoeExampleOrg_whenSignUpRequestEmailIsJaneDoeExampleOrg() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setFirstName("shashank");
        userDetailsEntity.setLastName("kumar");
        userDetailsEntity.setPassword("shasha");
        userDetailsEntity.setPhone(1);
        userDetailsEntity.setSecurityAnswer("Security Answer");
        userDetailsEntity.setSecurityQuestion("Security Question");
        userDetailsEntity.setUserId(1);
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setEmail("shashank@gmail.com");
        signUpRequest.setFirstName("shashank");
        signUpRequest.setLastName("kumar");
        signUpRequest.setPassword("shasha");
        signUpRequest.setPhone(1);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
    }

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <ul>
     *   <li>Given {@code john.smith@example.org}.</li>
     *   <li>When {@link SignUpRequest} (default constructor) Email is {@code john.smith@example.org}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest); given 'john.smith@example.org'; when SignUpRequest (default constructor) Email is 'john.smith@example.org'")
    void testSignUp_givenJohnSmithExampleOrg_whenSignUpRequestEmailIsJohnSmithExampleOrg() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setFirstName("shashank");
        userDetailsEntity.setLastName("kumar");
        userDetailsEntity.setPassword("shasha");
        userDetailsEntity.setPhone(1);
        userDetailsEntity.setSecurityAnswer("Security Answer");
        userDetailsEntity.setSecurityQuestion("Security Question");
        userDetailsEntity.setUserId(1);
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setEmail("shashank@gmail.com");
        signUpRequest.setFirstName("shashank");
        signUpRequest.setLastName("kumar");
        signUpRequest.setPassword("shasha");
        signUpRequest.setPhone(1);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
    }

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link SignUpRequest} (default constructor) Email is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest); given 'null'; when SignUpRequest (default constructor) Email is 'null'")
    void testSignUp_givenNull_whenSignUpRequestEmailIsNull() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setFirstName("shashank");
        signUpRequest.setLastName("kumar");
        signUpRequest.setPassword("shasha");
        signUpRequest.setPhone(1);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);
        signUpRequest.setEmail(null);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
    }

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <ul>
     *   <li>Given {@code prof.einstein@example.org}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest); given 'prof.einstein@example.org'")
    void testSignUp_givenProfEinsteinExampleOrg() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setFirstName("shashank");
        userDetailsEntity.setLastName("kumar");
        userDetailsEntity.setPassword("shasha");
        userDetailsEntity.setPhone(123456789);
        userDetailsEntity.setSecurityAnswer("Security Answer");
        userDetailsEntity.setSecurityQuestion("Security Question");
        userDetailsEntity.setUserId(1);
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setEmail("prof.einstein@example.org");
        signUpRequest.setFirstName("Jane");
        signUpRequest.setLastName("Doe");
        signUpRequest.setPassword("iloveyou");
        signUpRequest.setPhone(123456789);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
        verify(userDetailsRepository).findByEmail(eq("prof.einstein@example.org"));
    }

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <ul>
     *   <li>Given {@code Sign Up Request}.</li>
     *   <li>When {@link SignUpRequest} (default constructor) Email is {@code Sign Up Request}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest); given 'Sign Up Request'; when SignUpRequest (default constructor) Email is 'Sign Up Request'")
    void testSignUp_givenSignUpRequest_whenSignUpRequestEmailIsSignUpRequest() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setFirstName("shashank");
        signUpRequest.setLastName("kumar");
        signUpRequest.setPassword("shasha");
        signUpRequest.setPhone(123456789);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);
        signUpRequest.setEmail("Sign Up Request");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
    }

    /**
     * Test {@link LoginService#signUp(SignUpRequest)}.
     * <ul>
     *   <li>Given {@code U.U.U@U.U.U.UUUU}.</li>
     *   <li>When {@link SignUpRequest} (default constructor) Email is {@code U.U.U@U.U.U.UUUU}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signUp(SignUpRequest)}
     */
    @Test
    @DisplayName("Test signUp(SignUpRequest); given 'U.U.U@U.U.U.UUUU'; when SignUpRequest (default constructor) Email is 'U.U.U@U.U.U.UUUU'")
    void testSignUp_givenUUUUUUUuuu_whenSignUpRequestEmailIsUUUUUUUuuu() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setFirstName("shashank");
        userDetailsEntity.setLastName("kumar");
        userDetailsEntity.setPassword("shasha");
        userDetailsEntity.setPhone(123456789);
        userDetailsEntity.setSecurityAnswer("Security Answer");
        userDetailsEntity.setSecurityQuestion("Security Question");
        userDetailsEntity.setUserId(1);
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        signUpRequest.setEmail("U.U.U@U.U.U.UUUU");
        signUpRequest.setFirstName("shashank");
        signUpRequest.setLastName("kumar");
        signUpRequest.setPassword("shasha");
        signUpRequest.setPhone(123456789);
        signUpRequest.setSecurityAnswer("Security Answer");
        signUpRequest.setSecurityQuestion("Security Question");
        signUpRequest.setUserId(1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.signUp(signUpRequest));
        verify(userDetailsRepository).findByEmail(eq("U.U.U@U.U.U.UUUU"));
    }

    /**
     * Test {@link LoginService#signin(LoginRequest)}.
     * <ul>
     *   <li>Given {@link UserDetailsEntity} {@link UserDetailsEntity#getPassword()} return {@code foo}.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signin(LoginRequest)}
     */
    @Test
    @DisplayName("Test signin(LoginRequest); given UserDetailsEntity getPassword() return 'foo'; then return 'false'")
    void testSignin_givenUserDetailsEntityGetPasswordReturnFoo_thenReturnFalse() {
        // Arrange
        UserDetailsEntity userDetailsEntity = mock(UserDetailsEntity.class);
        when(userDetailsEntity.getPassword()).thenReturn("foo");
        doNothing().when(userDetailsEntity).setDateOfBirth(Mockito.<Date>any());
        doNothing().when(userDetailsEntity).setEmail(Mockito.<String>any());
        doNothing().when(userDetailsEntity).setFirstName(Mockito.<String>any());
        doNothing().when(userDetailsEntity).setLastName(Mockito.<String>any());
        doNothing().when(userDetailsEntity).setPassword(Mockito.<String>any());
        doNothing().when(userDetailsEntity).setPhone(anyInt());
        doNothing().when(userDetailsEntity).setSecurityAnswer(Mockito.<String>any());
        doNothing().when(userDetailsEntity).setSecurityQuestion(Mockito.<String>any());
        doNothing().when(userDetailsEntity).setUserId(anyInt());
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setFirstName("shashank");
        userDetailsEntity.setLastName("kumar");
        userDetailsEntity.setPassword("shasha");
        userDetailsEntity.setPhone(123456789);
        userDetailsEntity.setSecurityAnswer("Security Answer");
        userDetailsEntity.setSecurityQuestion("Security Question");
        userDetailsEntity.setUserId(1);
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("shashank@gmail.com");
        loginRequest.setPassword("shasha");

        // Act
        boolean actualSigninResult = loginService.signin(loginRequest);

        // Assert
        verify(userDetailsEntity).getPassword();
        verify(userDetailsEntity).setDateOfBirth(isA(Date.class));
        verify(userDetailsEntity).setEmail(eq("shashank@gmail.com"));
        verify(userDetailsEntity).setFirstName(eq("shashank"));
        verify(userDetailsEntity).setLastName(eq("kumar"));
        verify(userDetailsEntity).setPassword(eq("shasha"));
        verify(userDetailsEntity).setPhone(eq(123456789));
        verify(userDetailsEntity).setSecurityAnswer(eq("Security Answer"));
        verify(userDetailsEntity).setSecurityQuestion(eq("Security Question"));
        verify(userDetailsEntity).setUserId(eq(1));
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
        assertFalse(actualSigninResult);
    }

    /**
     * Test {@link LoginService#signin(LoginRequest)}.
     * <ul>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#signin(LoginRequest)}
     */
    @Test
    @DisplayName("Test signin(LoginRequest); then return 'true'")
    void testSignin_thenReturnTrue() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setPassword("shasha");

        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("shashank@gmail.com");
        loginRequest.setPassword("shasha");

        // Act
        boolean actualSigninResult = loginService.signin(loginRequest);

        // Assert
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
        assertTrue(actualSigninResult);
    }

    /**
     * Test {@link LoginService#changePassword(LoginRequest)}.
     * <ul>
     *   <li>Given {@link UserDetailsRepository} {@link CrudRepository#save(Object)} return {@link UserDetailsEntity} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#changePassword(LoginRequest)}
     */
    @Test
    @DisplayName("Test changePassword(LoginRequest); given UserDetailsRepository save(Object) return UserDetailsEntity (default constructor)")
    void testChangePassword_givenUserDetailsRepositorySaveReturnUserDetailsEntity() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setPassword("shasha");

        UserDetailsEntity userDetailsEntity2 = new UserDetailsEntity();
        userDetailsEntity2
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity2.setEmail("shashank@gmail.com");
        userDetailsEntity2.setPassword("shasha");

        when(userDetailsRepository.save(Mockito.<UserDetailsEntity>any())).thenReturn(userDetailsEntity2);
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("shashank@gmail.com");
        loginRequest.setPassword("shasha");

        // Act
        loginService.changePassword(loginRequest);

        // Assert
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
        verify(userDetailsRepository).save(isA(UserDetailsEntity.class));
    }

    /**
     * Test {@link LoginService#changePassword(LoginRequest)}.
     * <ul>
     *   <li>Then throw {@link IllegalArgumentException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#changePassword(LoginRequest)}
     */
    @Test
    @DisplayName("Test changePassword(LoginRequest); then throw IllegalArgumentException")
    void testChangePassword_thenThrowIllegalArgumentException() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setPassword("shasha");

        when(userDetailsRepository.save(Mockito.<UserDetailsEntity>any())).thenThrow(new IllegalArgumentException("foo"));
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("shashank@gmail.com");
        loginRequest.setPassword("shasha");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.changePassword(loginRequest));
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
        verify(userDetailsRepository).save(isA(UserDetailsEntity.class));
    }

    /**
     * Test {@link LoginService#deleteUser(LoginRequest)}.
     * <ul>
     *   <li>Given {@link UserDetailsRepository} {@link CrudRepository#delete(Object)} does nothing.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#deleteUser(LoginRequest)}
     */
    @Test
    @DisplayName("Test deleteUser(LoginRequest); given UserDetailsRepository delete(Object) does nothing")
    void testDeleteUser_givenUserDetailsRepositoryDeleteDoesNothing() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setPassword("shasha");

        doNothing().when(userDetailsRepository).delete(Mockito.<UserDetailsEntity>any());
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("shashank@gmail.com");
        loginRequest.setPassword("shasha");

        // Act
        loginService.deleteUser(loginRequest);

        // Assert
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
        verify(userDetailsRepository).delete(isA(UserDetailsEntity.class));
    }

    /**
     * Test {@link LoginService#deleteUser(LoginRequest)}.
     * <ul>
     *   <li>Then throw {@link IllegalArgumentException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LoginService#deleteUser(LoginRequest)}
     */
    @Test
    @DisplayName("Test deleteUser(LoginRequest); then throw IllegalArgumentException")
    void testDeleteUser_thenThrowIllegalArgumentException() {
        // Arrange
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity
                .setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        userDetailsEntity.setEmail("shashank@gmail.com");
        userDetailsEntity.setPassword("shasha");
        doThrow(new IllegalArgumentException("foo")).when(userDetailsRepository).delete(Mockito.<UserDetailsEntity>any());
        when(userDetailsRepository.findByEmail(Mockito.<String>any())).thenReturn(userDetailsEntity);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("shashank@gmail.com");
        loginRequest.setPassword("shasha");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> loginService.deleteUser(loginRequest));
        verify(userDetailsRepository).findByEmail(eq("shashank@gmail.com"));
        verify(userDetailsRepository).delete(isA(UserDetailsEntity.class));
    }
}
