package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginMainStatus
{
    // TODO Create ***** Rujul *********
    @SerializedName("user_type")
    @Expose
    private String usertype;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("login_detail")
    @Expose
    private LoginDetail loginDetail;
    private  StudentLoginDetails studentLoginDetails;

    public StudentLoginDetails getStudentLoginDetails() {
        return studentLoginDetails;
    }

    public void setStudentLoginDetails(StudentLoginDetails studentLoginDetails) {
        this.studentLoginDetails = studentLoginDetails;
    }

    public String getUsertype()
    {
        return usertype;
    }

    public void setUsertype(String usertype)
    {
        this.usertype = usertype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginDetail getLoginDetail() {
        return loginDetail;
    }

    public void setLoginDetail(LoginDetail loginDetail) {
        this.loginDetail = loginDetail;
    }

}