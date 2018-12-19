package com.jarasy.lv.redis;


/**
 * Created by wjh on 2018/4/9.
 */
public class HashKeyPrefix {

    public final  static String LOGIN_TOKEN = "login:token:";
    public final  static String LOGIN_USER = "login:user:";
    public final static String USER_LOGIN_LIST = "user:login:users";
    public final static String LOGIN_INVALID_ACCESS = "login:invalid:access";
    public final static String COMM_DAY = "comm:CommDay:";
    public final static String SEAL_INFO = "seal:SealInfo:";
    public final static String SEAL_APPLY = "seal:SealApply:";
    public final static String SEAL_APPLY_DETAIL = "seal:SealApplyDetail:";
    public final static String RELATION_SEAL_APPLY_TO_SEAL_APPLY_DETAIL = "seal:r:SealApply:SealApplyDetail:";
    public final static String SEAL_CANCEL = "seal:SealCancel:";
    public final static String SEAL_CANCEL_DETAIL = "seal:SealCancelDetail:";
    public final static String RELATION_SEAL_CANCEL_TO_SEAL_CANCEL_DETAIL = "seal:r:SealCancel:SealCancelDetail:";
    public final static String SEAL_DELIVER = "seal:SealDeliver:";
    public final static String SEAL_DELIVER_DETAIL = "seal:SealDeliverDetail:";
    public final static String RELATION_SEAL_DELIVER_TO_SEAL_DELIVER_DETAIL = "seal:r:SealDeliver:SealDeliverDetail:";
    public final static String SEAL_UNDERTAKE = "seal:SealUndertake:";
    public final static String SEAL_UNDERTAKE_DETAIL = "seal:SealUndertakeDetail:";
    public final static String RELATION_SEAL_UNDERTAKE_TO_SEAL_UNDERTAKE_DETAIL = "seal:r:SealUndertake:SealUndertakeDetail:";
    public final static String SEAL_DOCUMENT = "business:SealDocument:";
    public final static String SEAL_EXERCISE = "seal:Exercise";
    public final static String RELATION_BUSINESS_TO_SEAL_DOCUMENT = "business:r:SealDocument:";

    public final static String UNIT_INFO = "unit:UnitInfo:";
    public final static String UNIT_PASSWORD = "unit:UnitPassword:";
    public final static String RESULT_USE_UNIT_SEAL = "result:UseUnitSeal:";
    public final static String RESULT_CAN_DELIVER_SEAL = "result:CanDeliverSeal:";

    public final static String ORDER_INFO = "order:OrderInfo:";
    public final static String ORDER_OPERATOR = "order:OrderOperator:";
    public final static String ORDER_UNIT = "order:OrderUnit:";
    public final static String ORDER_SEAL = "order:OrderSeal:";
    public final static String ORDER_DOCUMENT = "order:OrderDocument:";
    public final static String ORDER_NUM = "order:OrderNum:";
    public final static String ORDER_DETAIL = "order:detail:";
    public final static String ORDER_UNIT_CHANGE = "order:unit:change";
    public final static String ORDER_SEAL_CANCEL = "order:seal:cancel";
    public final static String ORDER_OPERATOR_SUFFIX = ":operator";
    public final static String ORDER_SEAL_SUFFIX = ":seal";
    public final static String ORDER_UNIT_SUFFIX = ":unit";
    public final static String ORDER_DOCUMENT_SUFFIX = ":documet";
    public final static String ORDER_REMOTE_AUTHORIZE_CHECK_PHONE = "order:remote:authorize:check:phone:";
    public final static String ORDER_REMOTE_AUTHORIZE_COOKIE_ID = "order:remote:authorize:cookie:id:";
    public final static String ORDER_REMOTE_AUTHORIZE_TOKEN = "order:remote:authorize:token:";
    public final static String APPROVE_UNIT_ID_INFO = "approveunit:id:info:";
    public final static String APPROVE_UNIT_AREACODE_INFO = "approveunit:areacode:info:";


    //COMM_DICTIONARY
    public final static String HKEY_PREFIX_COMM_DICTIONARY = "common:CommDictionary:";
    public final static String HKEY_PREFIX_COMM_DICTIONARY_TYPE_IDS = "result:common:CommDictionary:type-ids:%s";
    public final static String HKEY_PREFIX_COMM_DICTIONARY_TYPE_NAME = "result:common:CommDictionary:type-name:%s";
    public final static String HKEY_PREFIX_COMM_DICTIONARY_TYPE_VALUE = "result:common:CommDictionary:type-value:%s";


    //platfomr-open
    public final static String PLATFORM_ACCOUNT_ID_INFO = "platform:account:id:info:";
    public final static String PLATFORM_ACCOUNT_IP = "platform:account:ip:";
    public final static String PLATFORM_ACCOUNT_PASSWD = "platform:account:passwd:";


    public final static String USER_INFO = "user:info:";

    public final static String ROLE_INFO = "role:info:";

    //role
    public final static String ROLE_ID = "role:id:";

    //sysconfig
    public final static String SYSCONFIG_TYPE_DRIVER = "sys:config:driver:";
    public final static String SYSCONFIG_TYPE_DRIVER_ID = "sys:config:driver:id:";

    public final static String SHOP_SITUATION = "shop:situation:info";
    
    //ocr
    public final static String OCR_VIDEO_KEY = "ocr:authorize:video:";
    
    //����ӡ��token key
    public  final static String USER_ESEAL_TOKEN_KEY = "user:eseal:token:";
}
