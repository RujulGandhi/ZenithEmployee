package app.com.zenith.Model;

/**
 * Created by archi_info on 3/25/2017.
 */
public class StudentListSetget
{
    // TODO MODEL CLASS FOR StudentList Page
    private String S_stu_image;
    private String S_stu_firstname;
    private String S_stu_school;
    private String stu_join_date;

    public String getS_stu_image() {
        return S_stu_image;
    }

    public void setS_stu_image(String s_stu_image) {
        S_stu_image = s_stu_image;
    }

    public String getS_stu_firstname() {
        return S_stu_firstname;
    }

    public void setS_stu_firstname(String s_stu_firstname) {
        S_stu_firstname = s_stu_firstname;
    }

    public String getS_stu_school() {
        return S_stu_school;
    }

    public void setS_stu_school(String s_stu_school) {
        S_stu_school = s_stu_school;
    }

    public String getStu_join_date() {
        return stu_join_date;
    }

    public void setStu_join_date(String stu_join_date) {
        this.stu_join_date = stu_join_date;
    }
}
