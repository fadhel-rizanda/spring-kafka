package com.kafka.producer_1.configurations;

import com.kafka.producer_1.interceptors.TestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final TestInterceptor testInterceptorBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptorBean)
                .addPathPatterns("/api/**") // Apply interceptor to specific URL patterns;
//                .excludePathPatterns("/api/v1/products/**") // Exclude interceptor from specific URL patterns
                .order(1); // Set the order of the interceptor
    }
}
