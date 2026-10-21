package com.kafka.producer_1.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// notes @Component //  karena dicoment maka harus didaftarkan di FilterConfig.java, sebenernya mah bukan didaftarkan cuman di buat beannya dan register secara manual di FilterConfig.java
//@Order(1)
@Slf4j
public class TestFilterBean extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("TestFilterBean is applied");
        try {
            filterChain.doFilter(request, response);
        } finally {
            log.info("TestFilterBean is closed");
        }
    }
}
