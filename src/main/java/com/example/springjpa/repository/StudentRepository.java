package com.example.springjpa.repository;

import com.example.springjpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// baza bilan ishlaydi
// Repository bean qilivchi anatatsiya
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


}
