package com.meechai.backendtest;

import com.meechai.backendtest.configuration.AuthorizationServerConfig;
import com.meechai.backendtest.configuration.ResourceServerConfig;
import com.meechai.backendtest.configuration.WebSecurityConfig;
import com.meechai.backendtest.entity.UserEntity;
import com.meechai.backendtest.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class,
        AuthorizationServerEndpointsConfiguration.class,
        AuthorizationServerSecurityConfiguration.class,
        ResourceServerConfiguration.class,
        ObjectPostProcessor.class,
        AuthorizationServerConfig.class,
        ResourceServerConfig.class,
        WebSecurityConfig.class,
        SecurityFilterAutoConfiguration.class,
        SpringBootWebSecurityConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration.class,
        org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration.class,
        org.springframework.security.config.annotation.ObjectPostProcessor.class
})
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnCorrectUser() {
        // given
        UserEntity user = new UserEntity();
        user.setUsername("test1");
        user.setPassword("test1");
        user.setFirstName("name1");
        user.setLastName("lastname1");
        user.setEmail("email1@test.com");
        user.setMobileNo("0811111111");
        user.setAddress("address1");
        user.setRole("User");
        user.setRefCode("202006210000");
        user.setSalary(50000);
        user.setApproveStatus(false);
        user.setMemberType("Platinum");

        entityManager.persist(user);
        entityManager.flush();

        // when
        UserEntity found = userRepository.findFirstByUsername(user.getUsername());

        // then
        assertThat(found.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(found.getLastName()).isEqualTo(user.getLastName());
        assertThat(found.getPassword()).isEqualTo(user.getPassword());
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
        assertThat(found.getMobileNo()).isEqualTo(user.getMobileNo());
        assertThat(found.getAddress()).isEqualTo(user.getAddress());
        assertThat(found.getRole()).isEqualTo(user.getRole());
        assertThat(found.getRefCode()).isEqualTo(user.getRefCode());
        assertThat(found.getSalary()).isEqualTo(user.getSalary());
        assertThat(found.getApproveStatus()).isEqualTo(user.getApproveStatus());
        assertThat(found.getMemberType()).isEqualTo(user.getMemberType());
    }

    @Test
    public void whenSaveUser_thenReturnCorrectUser() {
        // given
        UserEntity user = new UserEntity();
        user.setUsername("test1");
        user.setPassword("test1");
        user.setFirstName("name1");
        user.setLastName("lastname1");
        user.setEmail("email1@test.com");
        user.setMobileNo("0811111111");
        user.setAddress("address1");
        user.setRole("User");
        user.setRefCode("202006210000");
        user.setSalary(50000);
        user.setApproveStatus(false);
        user.setMemberType("Platinum");

        entityManager.persist(user);
        entityManager.flush();


        // when
        UserEntity found = userRepository.save(user);

        // then
        assertThat(found.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(found.getLastName()).isEqualTo(user.getLastName());
        assertThat(found.getPassword()).isEqualTo(user.getPassword());
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
        assertThat(found.getMobileNo()).isEqualTo(user.getMobileNo());
        assertThat(found.getAddress()).isEqualTo(user.getAddress());
        assertThat(found.getRole()).isEqualTo(user.getRole());
        assertThat(found.getRefCode()).isEqualTo(user.getRefCode());
        assertThat(found.getSalary()).isEqualTo(user.getSalary());
        assertThat(found.getApproveStatus()).isEqualTo(user.getApproveStatus());
        assertThat(found.getMemberType()).isEqualTo(user.getMemberType());
    }
}
