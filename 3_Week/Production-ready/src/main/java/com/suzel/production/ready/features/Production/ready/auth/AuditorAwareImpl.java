package com.suzel.production.ready.features.Production.ready.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // Ensure this method returns a valid username or other identifier
        return Optional.of("Suzel"); // or fetch from the security context
    }
}
