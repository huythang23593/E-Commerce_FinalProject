/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.entities.UserRoleEntity;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.repository.UserRepository;
import com.mycompany.spring_mvc_project_final.repository.UserRoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmailLikeAndStatusLike(email, UserStatus.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<UserRoleEntity> roleNames = userRoleRepository.findByUsers_Email(email);
        Set<GrantedAuthority> grantList = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleNames)) {
            for (UserRoleEntity role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().toString());
                grantList.add(authority);
            }
        }

        return (UserDetails) new User(user.getEmail(), user.getPassword(), grantList);
    }
    
    public  List<UserEntity> getUser(){
        return (List<UserEntity>) userRepository.findAll();
    }
    
    public UserEntity findUserById(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);
                if (optional.isPresent()) {
            return optional.get();
        } else {
            return new UserEntity();
        }
    }
    
    public void saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }
    
    
    public UserEntity findByUser(String email){
        return userRepository.findByFullname(email);
    }
}
