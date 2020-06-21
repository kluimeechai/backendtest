package com.meechai.backendtest;

import com.meechai.backendtest.entity.UserEntity;
import com.meechai.backendtest.exception.InvalidSalaryException;
import com.meechai.backendtest.model.RegisterRequest;
import com.meechai.backendtest.repository.UserRepository;
import com.meechai.backendtest.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
public class RegisterServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public RegisterService registerService() {
            return new RegisterService();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Autowired
    private RegisterService registerService;
    @MockBean
    private UserRepository userRepository;


    @Test
    public void SaveNewUser_Silver_SuccessCase() throws Exception {
        UserEntity user1 = createUser("1", 29999, "Silver");
        Mockito.when(userRepository.save(argThat((UserEntity user) -> user.getMemberType().equalsIgnoreCase("Silver")))).thenReturn(user1);

        RegisterRequest registerRequest = createRegisterRequest("1", 29999);

        UserEntity savedUser = registerService.save(registerRequest);

        assertThat(savedUser.getMemberType()).isEqualTo("Silver");

    }


    @Test
    public void SaveNewUser_Gold_SuccessCase() throws Exception {
        UserEntity user2 = createUser("2", 30000, "Gold");
        Mockito.when(userRepository.save(argThat((UserEntity user) -> user.getMemberType().equalsIgnoreCase("Gold")))).thenReturn(user2);

        RegisterRequest registerRequest = createRegisterRequest("2", 30000);

        UserEntity savedUser = registerService.save(registerRequest);

        assertThat(savedUser.getMemberType()).isEqualTo("Gold");

    }

    @Test
    public void SaveNewUser_Platinum_SuccessCase() throws Exception {
        UserEntity user3 = createUser("3", 50000, "Platinum");
        Mockito.when(userRepository.save(argThat((UserEntity user) -> user.getMemberType().equalsIgnoreCase("Platinum")))).thenReturn(user3);

        RegisterRequest registerRequest = createRegisterRequest("3", 50000);

        UserEntity savedUser = registerService.save(registerRequest);

        assertThat(savedUser.getMemberType()).isEqualTo("Platinum");

    }

    @Test(expected = InvalidSalaryException.class)
    public void SaveNewUser_FailCase_Salary_Lower_Than_15000() throws Exception {

        RegisterRequest registerRequest = createRegisterRequest("1", 14999);

        registerService.save(registerRequest);


    }


    private UserEntity createUser(String number, Integer Salary, String memberType) {
        UserEntity user = new UserEntity();
        user.setUsername("test" + number);
        user.setPassword("test" + number);
        user.setFirstName("name" + number);
        user.setLastName("lastname" + number);
        user.setEmail("email" + number + "@test.com");
        user.setMobileNo("081111111" + number);
        user.setAddress("address" + number);
        user.setSalary(Salary);
        user.setMemberType(memberType);
        return user;
    }

    private RegisterRequest createRegisterRequest(String number, Integer salary) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test" + number);
        registerRequest.setPassword("test" + number);
        registerRequest.setFirstName("name" + number);
        registerRequest.setLastName("lastname" + number);
        registerRequest.setEmail("email" + number + "@test.com");
        registerRequest.setMobileNo("081111111" + number);
        registerRequest.setAddress("address" + number);
        registerRequest.setSalary(salary);
        return registerRequest;
    }
}

