package com.jarasy.lv.common.constant;

/**
 * Created by cyjxc1 on 2017/6/6 0006.
 */
public abstract class CommonErrorCode {

    public final static int INVALID_ERROR = 100;

    /**
     * http 请求错误
     */
    public final static int HTTP_INVOKE_ERROR = 101;

    public final static int PARAM_ERROR = 102;

    public final static int UNKONW_ERROR = 103;

    public final static int DATA_ERROR = 104;

    public final static int SERVICE_PROMPT = 105;

    public final static int SHOP_SEAL_APPLY_ADD_ERROR = 10;//印章申请业务新增保存失败
    public final static int SHOP_SEAL_APPLY_UPDATE_ERROR = 10;//印章申请业务修改保存失败
    public static final int UNIT_INFO_ADD_ERROR = 11;//单位新增异常
    public static final int UNIT_CHANGE_ERROR = 12;//单位变更异常
    public static final int UNIT_EXCHANGE_ERROR = 13;//单位转区异常
    public static final int COMMON_IDGENERATION_CONFIG_NOT_EXIST = 14;//生成ID时ID该业务类型没有配置
    public static final int COMMON_IDGENERATION_CONFIG_ALREADY_EXIST = 15;//配置ID生成器时该业务类型已经配置过
    public static final int COMMON_IDGENERATION_GAP_RUN_OUT = 16;//获取ID时，该区间内ID已用尽，得不到新的ID

    public static final int PRODUCEUNITINFO_ADD_DATA_ERROR = 20;//制作单位新增数据失败
    public static final int PRODUCEUNITINFO_DEL_DATA_ERROR = 21;//制作单位删除数据失败
    public static final int PRODUCEUNITINFO_UPD_DATA_ERROR = 22;//制作单位修改数据失败
    public static final int PRODUCEUNITINFO_PUN_DATA_ERROR = 29; //制作单位录入处罚失败

    public static final int APPROVEUNITINFO_ADD_DATA_ERROR = 23;//审批单位新增数据失败
    public static final int APPROVEUNITINFO_DEL_DATA_ERROR = 24;//审批单位删除数据失败
    public static final int APPROVEUNITINFO_UPD_DATA_ERROR = 25;//审批单位修改数据失败

    public static final int SHOP_EMPLOYEE_ADD_ERROR = 26; //添加从业人员失败。
    public static final int SHOP_EMPLOYEE_UPDATE_ERROR = 27; //更新从业人员失败。
    public static final int SHOP_EMPLOYEE_DELETE_ERROR = 28; //删除从业人员失败。

}
