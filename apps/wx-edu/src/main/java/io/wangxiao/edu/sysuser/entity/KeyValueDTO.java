package io.wangxiao.edu.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @description 键值类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KeyValueDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7773297939894138862L;
    private int key;
    private String value;
}
