package com.mitocode.hotelapp_backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //no genera un bean
public interface IGenericRepo<T,ID> extends JpaRepository <T, ID>{

}
