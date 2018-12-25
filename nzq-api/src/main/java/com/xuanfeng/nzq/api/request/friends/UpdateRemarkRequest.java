package com.xuanfeng.nzq.api.request.friends;

import com.xuanfeng.nzq.api.request.base.WithMyXfRequest;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2018/12/16 16:14
 */
@Validated
@Data
public class UpdateRemarkRequest extends WithMyXfRequest {

    // 好友账号
    private Long fxf;
    // 新备注
    private String remark;

    public Long getFxf() {
        return fxf;
    }

    public void setFxf(Long fxf) {
        this.fxf = fxf;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
