package com.gym.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "membership_plan")
public class MembershipPlan {

    @Id
    private Integer id;
    private String plan_name;
    private Integer duration_months;
    private Double fee;
    private String description;
    private String is_active;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getPlan_name() { return plan_name; }
    public void setPlan_name(String plan_name) { this.plan_name = plan_name; }
    public Integer getDuration_months() { return duration_months; }
    public void setDuration_months(Integer duration_months) { this.duration_months = duration_months; }
    public Double getFee() { return fee; }
    public void setFee(Double fee) { this.fee = fee; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIs_active() { return is_active; }
    public void setIs_active(String is_active) { this.is_active = is_active; }
}
