package com.example.sorcier.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sorcier.bo.Sorcier;

public interface SorcierRepositorySqlite extends JpaRepository<Sorcier, Integer> {

}
