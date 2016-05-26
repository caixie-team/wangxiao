package io.wangxiao.edu.app.entity.website;

import lombok.Data;

@Data
public class AppGroup {

    private String groupId;//群组ID
    private String groupName;//群名
    private String groupHead;//群图像
    private String ownerType;//群所有类型，必选项。0为公开群，1为私有群
    private String ownerAccount;//所有者账号，必选项
    private String appRoval;//加入类型，必选项。0为自由加入，1为需要群主验证
    private String groupInfo;//群扩展信息，可选项。用于保存一些额外的信息，服务器不会对此信息做解析，在获取详情的时候可以拉取到
    private String maxNumber;//群组最大支持人数

    public AppGroup() {
    }

    public AppGroup(String groupName, String groupHead, String ownerType,
                    String ownerAccount, String appRoval, String groupInfo) {
        this.groupName = groupName;
        this.groupHead = groupHead;
        this.ownerType = ownerType;
        this.ownerAccount = ownerAccount;
        this.appRoval = appRoval;
        this.groupInfo = groupInfo;
    }

}
