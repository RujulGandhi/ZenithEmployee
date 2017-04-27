package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetail
{
    // TODO Create ***** Rujul *********
    @SerializedName("emp_id")
    @Expose
    private String empId;
    @SerializedName("emp_name")
    @Expose
    private String empName;
    @SerializedName("emp_email")
    @Expose
    private String empEmail;
    @SerializedName("emp_phone")
    @Expose
    private String empPhone;
    @SerializedName("emp_city")
    @Expose
    private String empCity;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpCity() {
        return empCity;
    }

    public void setEmpCity(String empCity) {
        this.empCity = empCity;

    }
}

