package com.xuanfeng.nzq.api.request.group;

import com.xuanfeng.nzq.api.request.base.WithMyXfRequest;
import lombok.Data;

/**
 * @description: 添加分组请求
 * @author: lvxianqing
 * @create: 2019/04/21 15:04
 */

@Data
public class AddGroupReq extends WithMyXfRequest {
    private String name;
}
