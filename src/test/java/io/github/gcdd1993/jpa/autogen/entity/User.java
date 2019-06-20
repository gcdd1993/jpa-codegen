package io.github.gcdd1993.jpa.autogen.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 用户表
 *
 * @author gaochen
 * Created on 2019/6/17.
 */
@Entity
@Table(name = "sys_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String phone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "r_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;

}
