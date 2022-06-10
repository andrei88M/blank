package com.example.test.repo;

import com.example.test.entity.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends CrudRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
