package com.Nordertino.payment_manager.request;

import java.time.LocalDateTime;

public record UserRequest(Long id, String name, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
