package com.xuanfeng.nzq.domain.entity;

import lombok.Data;

/**
 * @description: 好友信息
 * @author: lvxianqing
 * @create: 2018/11/23 10:48
 */
@Data
public class FriendInfo {
    private Long xf;
    private String nickname;
    private String remark;
    private Byte status;
    private Long groupId;
}
