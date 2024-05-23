package com.ping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class Doctor implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 医生id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 医生姓名
     */
      private String name;

      /**
     * 医生电话号码
     */
      private Integer phone;

      /**
     * 医生私钥
     */
      private String privatekey;
      private String password;

      /**
     * 医院名称
     */
      private String hospital;

      /**
     * 科室名称
     */
      private String office;

      /**
     * 医生公钥
     */
      private String publickey;


}
