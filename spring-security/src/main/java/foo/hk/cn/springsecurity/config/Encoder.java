package foo.hk.cn.springsecurity.config;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @Author: foo
 * @Date: 2021-11-03 14:23
 * @description:
 */
public class Encoder {

    public static void main(String[] args) {
        //getUser();

        createBCryptPasswordEncoder();
    }

    static void getPasswordEncoder() {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    static void getCustomPasswordEncoder() {

        String idForEncode = "bcrypt";
        Map encodes = new HashMap<>();
        encodes.put(idForEncode, new BCryptPasswordEncoder());
        encodes.put("noop", NoOpPasswordEncoder.getInstance());
        encodes.put("pdkdf2", new Pbkdf2PasswordEncoder());
        encodes.put("scrypt", new SCryptPasswordEncoder());
        encodes.put("sha256", new StandardPasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encodes);

    }

    static void getUser() {

        /*UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        System.out.println(user);
        System.out.println(user.getPassword());*/

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users.username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users.username("admin")
                .password("paassword")
                .roles("USER", "ADMIN")
                .build();

    }

    static void createBCryptPasswordEncoder() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encode = encoder.encode("123456");
        assertTrue (encoder.matches("123456", encode));

        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
        argon2PasswordEncoder.encode("123456");

        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        pbkdf2PasswordEncoder.encode("123456");

        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        sCryptPasswordEncoder.encode("123456");

    }

    static void setSecurityContextHolder() {

        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("foo", "foo", "ROLE_USER");
        //new UsernamePasswordAuthenticationToken()
        emptyContext.setAuthentication(authenticationToken);

        SecurityContextHolder.setContext(emptyContext);

        // 最好用上面那种可以有效避免多线程的 race conditions，而不是用下面这种。
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    static void getAuthenticatedUser() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        // identify 用户。UserDetails 实例。
        Object principal = authentication.getPrincipal();
        // roles 和 scopes
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 密码，大多数情况，密码会在用户被验证后清楚，以确定不会被泄露
        Object credentials = authentication.getCredentials();

        // SecurityContextHolder.MODE_THREADLOCAL
        //SecurityContextHolder


    }

}
