package com.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by msoch_000 on 18-09-2017.
 */
@Configuration
public class SecurityConfig extends BCryptPasswordEncoder {

}

