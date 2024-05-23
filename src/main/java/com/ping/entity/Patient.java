package com.ping.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2024-03-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Patient implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 患者id
     */
        private Integer id;

      /**
     * 患者姓名
     */
      private String name;

      /**
     * 患者电话
     */
      private Long phone;

      /**
     * 患者私钥
     */
      private String privatekey;
      private String password;

      /**
     * 患者性别（0：女；1：男）
     */
      private Integer sex;

      /**
     * 患者年龄
     */
      private Integer age;

      /**
     * 患者公钥
     */
      private String publickey;


}
