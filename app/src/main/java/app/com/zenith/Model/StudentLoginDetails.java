package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by archi_info on 3/21/2017.
 */

public class StudentLoginDetails
{
    // TODO Create ***** Rujul *********
    @SerializedName("stu_id")
    @Expose
    private String studId;
    @SerializedName("stu_firstname")
    @Expose
    private String studName;
    @SerializedName("stu_email")
    @Expose
    private String studEmail;
    @SerializedName("stu_phoneno")
    @Expose
    private String studPhone;
    @SerializedName("stu_school")
    @Expose
    private String studSchool;

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudEmail() {
        return studEmail;
    }

    public void setStudEmail(String studEmail) {
        this.studEmail = studEmail;
    }

    public String getStudPhone() {
        return studPhone;
    }

    public void setStudPhone(String studPhone) {
        this.studPhone = studPhone;
    }

    public String getStudSchool() {
        return studSchool;
    }

    public void setStudSchool(String studSchool) {
        this.studSchool = studSchool;
    }
}
