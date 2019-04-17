package com.xuanfeng.nzq.api.response.app;

import lombok.Data;

/**
 * @description:
 * @author: lvxianqing
 * @create: 2019/04/17 14:34
 */
@Data
public class ShortApplication {
    private Long id;

    private Long sendXf;

    private String sendNickname;

    private Long receiveXf;

    private String receiveNickname;

    private String text;

    private String remark;

    private Byte status;
}
