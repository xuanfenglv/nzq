package com.xuanfeng.nzq.websocket.main.game.msg.request;

import com.xuanfeng.nzq.commons.msg.CheckParamResult;
import com.xuanfeng.nzq.commons.msg.request.RequestMsg;

/**
 * @description: 落子请求
 * @author: lvxianqing
 * @create: 2018/12/24 20:13
 */

public class DropRequest extends RequestMsg {
    // 行
    private byte row;
    // 列
    private byte column;

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public byte getColumn() {
        return column;
    }

    public void setColumn(byte column) {
        this.column = column;
    }
}
