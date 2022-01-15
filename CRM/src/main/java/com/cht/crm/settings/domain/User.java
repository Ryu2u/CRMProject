package com.cht.crm.settings.domain;

public class User {
    /*
        关于字符串中表现得日期及时间
        我们在市场中 常用的两种方式
        日期: 年月日(10char) 时分秒(19char)
            yyyy-MM-dd HH:mm:ss SSS

         关于登录:
                验证账号和密码
                User user = 执行sql语句select count(*) from tbl_user where loginAct=? and loginPwd=?;
                若user对象为null, 则说明账号密码错误
                如果user对象不为null,说明账号密码正确
                需要验证其他信息
                从user中get到其他属性.
                例如:
                expireTime 失效时间
                lockState 锁定状态
                allowIps 允许访问的IP地址

     */
    private String id; //编号 主键
    private String loginAct;//登录账号
    private String name;//用户的真实姓名
    private String loginPwd; //登录密码
    private String email; //邮箱
    private String expireTime; //失效时间
    private String lockState; //锁定状态
    private String deptno; //部门编号
    private String allowIps; //允许访问的ip
    private String createTime; //创建时间
    private String createBy; //创建人
    private String editTime; //修改时间
    private String editBy;// 修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }
}
