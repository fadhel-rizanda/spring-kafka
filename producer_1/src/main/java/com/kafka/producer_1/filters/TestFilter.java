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

@Component // notes semua filter otomatis didaftarkan ke Spring context, jadi @component sebatas create bean aja dan spring otomatis mendaftarkan ke filter chain
@Order(1)
@Slf4j
public class TestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("TestFilter is applied");
        try {
            filterChain.doFilter(request, response);
        } finally {
            log.info("TestFilter is closed");
        }
    }
}
