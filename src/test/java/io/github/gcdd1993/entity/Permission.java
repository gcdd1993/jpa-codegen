package io.github.gcdd1993.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 权限
 *
 * @author gaochen
 * Created on 2019/6/17.
 */
@Data
@Entity
@IdClass(User.class)
@Table(name = "sys_permission")
public class Permission {

    @Id
    private Integer id;

    private String name;

    private String code;

}
