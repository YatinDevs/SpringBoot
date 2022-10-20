package com.yatindevs.spring.mysql.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.yatindevs.spring.mysql.entities_model.*;

// type , Id
public interface UserRepository extends JpaRepository< User, Integer>{

}
