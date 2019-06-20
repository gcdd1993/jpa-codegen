package io.github.gcdd1993.jpa.autogen.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 *
 * @author gaochen
 * Created on 2019/6/17.
 */
@Data
@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "r_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissionList;

}
