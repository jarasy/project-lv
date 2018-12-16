package com.cyyz.spt.springbase.domain;

/**
 * Created by Administrator on 2017/6/26.
 */
public class UserVO {
    private String userId;
    private String userName;
    private String deptId;        // 用户所属部门Id ,门店也是部门
    private String depName;
    private String productionUnitId; // 用户所属公司ID
    private String areaCode;      // 用户所属区域编码，以数字开头
    private String approveCode;    // 用户办理业务所需审批的分局编码，以字母开头
    private String productionTag;//制作单位标识，用于判断是否为创业标识
    private String userType;      // 用户类型
    private String roleId;         // 用户角色ID
    private Boolean isCyyz;       // 是否为创业印章用户
    private String clientIP;
    private String cooperationMode;//外部企业核准模式   0：不扣费，1：扣费
    private String productionUnitName;   //特行证名字
    private String autoApply;//是否自动审核：1自动审核，0非自动审核
    private String eSealApply;   // 是否可以做电子印章申请
    public  String checkType; //门店是否自动核查标识:1-是,0-否
    private boolean allCity; //是否全市统备
    //电子印章token
    private String esealToken;

    public String getCityCode(){
        return this.areaCode.substring(0,4)+"00";
    }


    public String getAutoApply() {
        return autoApply;
    }

    public void setAutoApply(String autoApply) {
        this.autoApply = autoApply;
    }


    public String getCooperationMode() {
        return cooperationMode;
    }

    public void setCooperationMode(String cooperationMode) {
        this.cooperationMode = cooperationMode;
    }


    public String getProductionTag() {
        return productionTag;
    }

    public void setProductionTag(String productionTag) {
        this.productionTag = productionTag;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getApproveCode() {
        return approveCode;
    }

    public void setApproveCode(String approveCode) {
        this.approveCode = approveCode;
    }

    public String getProductionUnitId() {
        return productionUnitId;
    }

    public void setProductionUnitId(String productionUnitId) {
        this.productionUnitId = productionUnitId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Boolean getIsCyyz() {
        return isCyyz;
    }

    public void setIsCyyz(Boolean isCyyz) {
        this.isCyyz = isCyyz;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getProductionUnitName() {
        return productionUnitName;
    }

    public void setProductionUnitName(String productionUnitName) {
        this.productionUnitName = productionUnitName;
    }

    public Boolean getCyyz() {
        return isCyyz;
    }

    public void setCyyz(Boolean cyyz) {
        isCyyz = cyyz;
    }

    public String geteSealApply() {
        return eSealApply;
    }

    public void seteSealApply(String eSealApply) {
        this.eSealApply = eSealApply;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public boolean isAllCity() {
        return allCity;
    }

    public void setAllCity(boolean allCity) {
        this.allCity = allCity;
    }

    public String getEsealToken() {
        return esealToken;
    }

    public void setEsealToken(String esealToken) {
        this.esealToken = esealToken;
    }
}
