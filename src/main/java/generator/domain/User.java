package generator.domain;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 用户表创建
*/
public class User implements Serializable {

    /**
    * 用户id
    */
    @NotNull(message="[用户id]不能为空")
    @ApiModelProperty("用户id")
    private Long id;
    /**
    * 用户昵称
    */
    @Size(max= 256,message="编码长度不能超过256")
    @ApiModelProperty("用户昵称")
    @Length(max= 256,message="编码长度不能超过256")
    private String username;
    /**
    * 用户账号
    */
    @Size(max= 256,message="编码长度不能超过256")
    @ApiModelProperty("用户账号")
    @Length(max= 256,message="编码长度不能超过256")
    private String userAccount;
    /**
    * 用户头像
    */
    @Size(max= 1024,message="编码长度不能超过1024")
    @ApiModelProperty("用户头像")
    @Length(max= 1024,message="编码长度不能超过1,024")
    private String avatarUrl;
    /**
    * 用户性别
    */
    @ApiModelProperty("用户性别")
    private Integer gender;
    /**
    * 用户密码
    */
    @NotBlank(message="[用户密码]不能为空")
    @Size(max= 512,message="编码长度不能超过512")
    @ApiModelProperty("用户密码")
    @Length(max= 512,message="编码长度不能超过512")
    private String userPassword;
    /**
    * 用户电话
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("用户电话")
    @Length(max= 128,message="编码长度不能超过128")
    private String phone;
    /**
    * 用户邮箱
    */
    @Size(max= 512,message="编码长度不能超过512")
    @ApiModelProperty("用户邮箱")
    @Length(max= 512,message="编码长度不能超过512")
    private String email;
    /**
    * 用户状态 0 - 正常
    */
    @NotNull(message="[用户状态 0 - 正常]不能为空")
    @ApiModelProperty("用户状态 0 - 正常")
    private Integer userStatus;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date updateTime;
    /**
    * 是否删除
    */
    @NotNull(message="[是否删除]不能为空")
    @ApiModelProperty("是否删除")
    private Integer isDelete;
    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
    @NotNull(message="[用户角色 0 - 普通用户 1 - 管理员]不能为空")
    @ApiModelProperty("用户角色 0 - 普通用户 1 - 管理员")
    private Integer userRole;
    /**
    * 用户编号
    */
    @Size(max= 512,message="编码长度不能超过512")
    @ApiModelProperty("用户编号")
    @Length(max= 512,message="编码长度不能超过512")
    private String userCode;
    /**
    * 标签 json 列表
    */
    @Size(max= 1024,message="编码长度不能超过1024")
    @ApiModelProperty("标签 json 列表")
    @Length(max= 1024,message="编码长度不能超过1,024")
    private String tags;

    /**
    * 用户id
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 用户昵称
    */
    private void setUsername(String username){
    this.username = username;
    }

    /**
    * 用户账号
    */
    private void setUserAccount(String userAccount){
    this.userAccount = userAccount;
    }

    /**
    * 用户头像
    */
    private void setAvatarUrl(String avatarUrl){
    this.avatarUrl = avatarUrl;
    }

    /**
    * 用户性别
    */
    private void setGender(Integer gender){
    this.gender = gender;
    }

    /**
    * 用户密码
    */
    private void setUserPassword(String userPassword){
    this.userPassword = userPassword;
    }

    /**
    * 用户电话
    */
    private void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 用户邮箱
    */
    private void setEmail(String email){
    this.email = email;
    }

    /**
    * 用户状态 0 - 正常
    */
    private void setUserStatus(Integer userStatus){
    this.userStatus = userStatus;
    }

    /**
    * 创建时间
    */
    private void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 
    */
    private void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 是否删除
    */
    private void setIsDelete(Integer isDelete){
    this.isDelete = isDelete;
    }

    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
    private void setUserRole(Integer userRole){
    this.userRole = userRole;
    }

    /**
    * 用户编号
    */
    private void setUserCode(String userCode){
    this.userCode = userCode;
    }

    /**
    * 标签 json 列表
    */
    private void setTags(String tags){
    this.tags = tags;
    }


    /**
    * 用户id
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 用户昵称
    */
    private String getUsername(){
    return this.username;
    }

    /**
    * 用户账号
    */
    private String getUserAccount(){
    return this.userAccount;
    }

    /**
    * 用户头像
    */
    private String getAvatarUrl(){
    return this.avatarUrl;
    }

    /**
    * 用户性别
    */
    private Integer getGender(){
    return this.gender;
    }

    /**
    * 用户密码
    */
    private String getUserPassword(){
    return this.userPassword;
    }

    /**
    * 用户电话
    */
    private String getPhone(){
    return this.phone;
    }

    /**
    * 用户邮箱
    */
    private String getEmail(){
    return this.email;
    }

    /**
    * 用户状态 0 - 正常
    */
    private Integer getUserStatus(){
    return this.userStatus;
    }

    /**
    * 创建时间
    */
    private Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 
    */
    private Date getUpdateTime(){
    return this.updateTime;
    }

    /**
    * 是否删除
    */
    private Integer getIsDelete(){
    return this.isDelete;
    }

    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
    private Integer getUserRole(){
    return this.userRole;
    }

    /**
    * 用户编号
    */
    private String getUserCode(){
    return this.userCode;
    }

    /**
    * 标签 json 列表
    */
    private String getTags(){
    return this.tags;
    }

}
