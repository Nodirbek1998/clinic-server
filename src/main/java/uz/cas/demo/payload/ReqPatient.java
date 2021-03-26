package uz.cas.demo.payload;

public class ReqPatient {

    private String firstName;

    private String lastName;

    private String phone;

    private Integer doctorId;

    public ReqPatient() {
    }

    public ReqPatient(String firstName, String lastName, String phone, String doctorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.doctorId = Integer.valueOf(doctorId);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
