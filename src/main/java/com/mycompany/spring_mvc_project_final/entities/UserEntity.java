/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity extends PersonalInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_relationship",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"))
    private Set<UserRoleEntity> userRoles;
    
    @OneToMany(mappedBy = "users" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VoteEntity> vote;
    
    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrdersEntity> order;
    
    @OneToMany(mappedBy = "users" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comment;
    
    @OneToMany(mappedBy = "userEntity" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavoriteEntity> favorite;

    public UserEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<VoteEntity> getVote() {
        return vote;
    }

    public void setVote(List<VoteEntity> vote) {
        this.vote = vote;
    }

    public List<CommentEntity> getComment() {
        return comment;
    }

    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
    }

    public List<FavoriteEntity> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<FavoriteEntity> favorite) {
        this.favorite = favorite;
    }

    public List<OrdersEntity> getOrder() {
        return order;
    }

    public void setOrder(List<OrdersEntity> order) {
        this.order = order;
    }
    
    
    
}
