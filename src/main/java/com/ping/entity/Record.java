package com.ping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
    public class Record implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 医生id
     */
      private Integer doctorId;

      /**
     * 患者id
     */
      private Integer patientId;

      /**
     * 档案创建时间
     */
      private String createtime;

      /**
     * 医生诊断（患者公钥加密）
     */
      private String descriptionEnbyp;

      /**
     * 备注（患者公钥加密）
     */
      private String remarkEnbyp;

      /**
     * 是否确认（0未确认，1已确认）
     */
      private Integer affirm;


}
