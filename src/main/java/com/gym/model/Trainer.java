package com.gym.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainer")
public class Trainer {

    @Id
    private Integer id;
    private String full_name;
    private String phone;
    private String specialization;
    private String hire_date;
    private String is_active;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getHire_date() { return hire_date; }
    public void setHire_date(String hire_date) { this.hire_date = hire_date; }
    public String getIs_active() { return is_active; }
    public void setIs_active(String is_active) { this.is_active = is_active; }
}
