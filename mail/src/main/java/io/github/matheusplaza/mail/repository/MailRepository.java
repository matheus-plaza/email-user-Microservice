package io.github.matheusplaza.mail.repository;

import io.github.matheusplaza.mail.entity.MailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailRepository extends JpaRepository<MailModel, UUID> {
}
