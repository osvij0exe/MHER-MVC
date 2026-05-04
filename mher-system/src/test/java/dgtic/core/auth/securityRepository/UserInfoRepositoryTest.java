package dgtic.core.auth.securityRepository;

import static org.assertj.core.api.Assertions.assertThat;

import dgtic.core.auth.model.UserInfo;
import dgtic.core.auth.model.UserInfoRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserInfoRepositoryTest {
    private final TestEntityManager testEntityManager;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoRepositoryTest(TestEntityManager testEntityManager, UserInfoRepository userInfoRepository) {
        this.testEntityManager = testEntityManager;
        this.userInfoRepository = userInfoRepository;
    }

    @Test
    public void testCreateUser() {
        UserInfo user = new UserInfo();
        user.setUseFirstName("Admin");
        user.setUseLastName("Admin");
        user.setUseEmail("admin@gmail.com");
        user.setUsePasswd("admin1234"); //user
        user.setUseIdStatus(1);
        user.setUseCreatedBy(1L);
        user.setUseModifiedBy(1L);
        UserInfo savedUser = userInfoRepository.save(user);
        UserInfo existUser = testEntityManager.find(UserInfo.class, savedUser.getUseId());
        assertThat(user.getUseEmail()).isEqualTo(existUser.getUseEmail());
    }

    @Test
    public void testReadUser() {
        UserInfo user = new UserInfo();
        user.setUseEmail("admin@gmail.com");
        Optional<UserInfo> userInfo = userInfoRepository.findById(1L);
        if (userInfo.isPresent()) {
            UserInfo savedUser = userInfo.get();
            UserInfo existUser = testEntityManager.find(UserInfo.class, savedUser.getUseId());
            assertThat(user.getUseEmail()).isEqualTo(existUser.getUseEmail());
        } else
            assertThat(user.getUseEmail()).isEqualTo(null);
    }

    @Test
    public void testCreateUser2() {
        UserInfo user = new UserInfo();
        user.setUseFirstName("userPrueba");
        user.setUseLastName("userPrueba");
        user.setUseEmail("userPrueba@hotmail.com");
        user.setUsePasswd("user1234"); //user
        user.setUseIdStatus(1);
        user.setUseCreatedBy(1L);
        user.setUseModifiedBy(1L);
        UserInfo savedUser = userInfoRepository.save(user);
        UserInfo existUser = testEntityManager.find(UserInfo.class, savedUser.getUseId());
        assertThat(user.getUseEmail()).isEqualTo(existUser.getUseEmail());
    }

    @Test
    public void testRoleCreationUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
        Set<UserInfoRole> roles = new HashSet<>();
        UserInfoRole role = new UserInfoRole();
        role.setUsrId(2L);
        roles.add(role);
        Optional<UserInfo> userInfo = userInfoRepository.findById(4L);
        if (userInfo.isPresent()) {
            UserInfo user = userInfo.get();
            user.setUseInfoRoles(roles);
            user.setUsePasswd(passwordEncoder.encode("user"));// user
            userInfoRepository.save(user);
            System.out.println("User updated with roles");
        }
    }

    @Test
    public void testRoleCreationUser2() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
        Set <UserInfoRole> roles = new HashSet<>();
        UserInfoRole role = new UserInfoRole();
        role.setUsrId(1L);
        roles.add(role);
        Optional<UserInfo> userInfo = userInfoRepository.findById(3L);
        if (userInfo.isPresent()) {
            UserInfo user = userInfo.get();
            user.setUseInfoRoles(roles);
            user.setUsePasswd(passwordEncoder.encode("admin"));// admin
            userInfoRepository.save(user);
            System.out.println("User updated with roles");
        }
    }
    @Test
    public void testCreateUser3() {
        UserInfo user = new UserInfo();
        user.setUseFirstName("Jessica");
        user.setUseLastName("Amdor");
        user.setUseEmail("jessica@gmail.com");
        user.setUsePasswd("jessica"); //user
        user.setUseIdStatus(1);
        user.setUseCreatedBy(1L);
        user.setUseModifiedBy(1L);
        UserInfo savedUser = userInfoRepository.save(user);
        UserInfo existUser = testEntityManager.find(UserInfo.class, savedUser.getUseId());
        assertThat(user.getUseEmail()).isEqualTo(existUser.getUseEmail());
    }
    @Test
    public void testRoleCreationUser3() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
        Set <UserInfoRole> roles = new HashSet<>();
        UserInfoRole role = new UserInfoRole();
        role.setUsrId(2L);
        roles.add(role);
        Optional<UserInfo> userInfo = userInfoRepository.findById(6L);
        if (userInfo.isPresent()) {
            UserInfo user = userInfo.get();
            user.setUseInfoRoles(roles);
            user.setUsePasswd(passwordEncoder.encode("jessica"));// admin
            userInfoRepository.save(user);
            System.out.println("User updated with roles");
        }
    }

}
