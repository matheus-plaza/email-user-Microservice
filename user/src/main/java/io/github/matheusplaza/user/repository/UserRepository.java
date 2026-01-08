package io.github.matheusplaza.user.repository;

import io.github.matheusplaza.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
