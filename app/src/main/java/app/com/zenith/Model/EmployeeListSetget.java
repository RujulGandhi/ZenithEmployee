package app.com.zenith.Model;

/**
 * Created by archi_info on 3/21/2017.
 */
public class EmployeeListSetget
{
    // TODO Model Class for Employee List Page
    private String emp_listImage;
    private String emp_listName;
    private String emp_listId;
    private String emp_listDatetime;


    public String getEmp_listId()
    {
        return emp_listId;
    }

    public void setEmp_listId(String emp_listId) {
        this.emp_listId = emp_listId;
    }

    public String getEmp_listName() {
        return emp_listName;
    }

    public void setEmp_listName(String emp_listName) {
        this.emp_listName = emp_listName;
    }

    public String getEmp_listImage() {
        return emp_listImage;
    }

    public void setEmp_listImage(String emp_listImage) {
        this.emp_listImage = emp_listImage;
    }

    public String getEmp_listDatetime() {
        return emp_listDatetime;
    }

    public void setEmp_listDatetime(String emp_listDatetime) {
        this.emp_listDatetime = emp_listDatetime;
    }
}