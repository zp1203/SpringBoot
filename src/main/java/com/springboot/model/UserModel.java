package com.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    //姓名
    private String name;
    //类型
    private String name_type;
    //密码
    private String user_password;
    //创建时间
    private Date create_time;
    //更新时间
    private Date update_time;

}
