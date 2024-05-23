package com.ping.entity;

import java.util.Date;
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
    public class Authorize implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        private Integer id;

      /**
     * 医生id
     */
      private Integer doctorId;

      /**
     * 档案id
     */
      private Integer recordId;

      /**
     * 描述
     */
      private String descriptionId;

    private String remarkEnbyd;

      /**
     * 状态
     */
      private Integer state;

      /**
     * 创建时间
     */
      private Date createtime;


}
