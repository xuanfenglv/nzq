package com.xuanfeng.nzq.api.request.friends;

import com.xuanfeng.nzq.api.request.base.WithMyXfRequest;

/**
 * @description: 修改分组请求
 * @author: lvxianqing
 * @create: 2018/12/16 16:56
 */

public class UpdateGroupRequest extends WithMyXfRequest {
    private Long fxf;
    private Long groupId;

    public Long getFxf() {
        return fxf;
    }

    public void setFxf(Long fxf) {
        this.fxf = fxf;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
