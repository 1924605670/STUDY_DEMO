package com.study.rabbit.repo;

import com.study.rabbit.beans.DsTfcpassAllEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassAllRepo extends JpaRepository<DsTfcpassAllEntity,String> {

}
