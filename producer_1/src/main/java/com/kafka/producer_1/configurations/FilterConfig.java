package com.kafka.producer_1.configurations;

import com.kafka.producer_1.filters.TestFilterBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<TestFilterBean> testFilterBean() {
        FilterRegistrationBean<TestFilterBean> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TestFilterBean());
        bean.addUrlPatterns("/*"); /* Notes dia tidak bisa /** karena filter dijalankan di level servlet, sedangkan /** itu di level spring mvc
           kekurangannya tidak bisa match semua subpath dari /api/*, jadi cuman match /api/..., kalo /api/.../... tidak akan kevalidasi,
          kalo mau match semua subpath harus pake interceptor atau path matcher manual di filternya
         */
        bean.setOrder(1); // Set the order of the filter
        return bean;
    }
}
