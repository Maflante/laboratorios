package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private final AuthController authController = new AuthController();

    @Test
    void me_deberiaRetornarUsernameYRoles() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("rodrigo");

        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        );

        // Usar thenAnswer para evitar error de compilación
        when(authentication.getAuthorities()).thenAnswer(invocation -> authorities);

        Map<String, Object> resultado = authController.me(authentication);

        assertEquals("rodrigo", resultado.get("username"));
        List<String> roles = (List<String>) resultado.get("roles");
        assertEquals(2, roles.size());
        assertTrue(roles.contains("ROLE_ADMIN"));
        assertTrue(roles.contains("ROLE_USER"));
    }

    @Test
    void me_sinRoles_deberiaRetornarListaVacia() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("ana");

        // También con thenAnswer
        when(authentication.getAuthorities()).thenAnswer(invocation -> Collections.emptyList());

        Map<String, Object> resultado = authController.me(authentication);

        assertEquals("ana", resultado.get("username"));
        List<String> roles = (List<String>) resultado.get("roles");
        assertTrue(roles.isEmpty());
    }
}
