package com.project.auth.repository;

import com.project.auth.entity.User;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
