package com.example.sorcier.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sorcier.bo.Maison;


public interface MaisonRepositorySqlite extends JpaRepository<Maison, Integer> {

}
