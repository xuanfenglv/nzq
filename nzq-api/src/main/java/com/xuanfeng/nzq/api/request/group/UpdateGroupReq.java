package com.xuanfeng.nzq.api.request.group;

import com.xuanfeng.nzq.api.request.base.WithMyXfRequest;
import lombok.Data;

/**
 * @description: 更新分组
 * @author: lvxianqing
 * @create: 2019/04/21 15:06
 */
@Data
public class UpdateGroupReq extends WithMyXfRequest {
    // 分组id
    private Long id;
    // 分组name
    private String name;
}
