package com.gym.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
public class Member {

    @Id
    private Integer id;
    private String full_name;
    private String phone;
    private String gender;
    private Integer age;
    private String join_date;
    private String status;
    private PlanRef plan;
    private TrainerRef trainer;

    public static class PlanRef {
        private Integer plan_id;
        private String plan_name;
        private Object fee;
        public Integer getPlan_id() { return plan_id; }
        public void setPlan_id(Integer plan_id) { this.plan_id = plan_id; }
        public String getPlan_name() { return plan_name; }
        public void setPlan_name(String plan_name) { this.plan_name = plan_name; }
        public Object getFee() { return fee; }
        public void setFee(Object fee) { this.fee = fee; }
    }

    public static class TrainerRef {
        private Integer trainer_id;
        private String trainer_name;
        public Integer getTrainer_id() { return trainer_id; }
        public void setTrainer_id(Integer trainer_id) { this.trainer_id = trainer_id; }
        public String getTrainer_name() { return trainer_name; }
        public void setTrainer_name(String trainer_name) { this.trainer_name = trainer_name; }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getJoin_date() { return join_date; }
    public void setJoin_date(String join_date) { this.join_date = join_date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public PlanRef getPlan() { return plan; }
    public void setPlan(PlanRef plan) { this.plan = plan; }
    public TrainerRef getTrainer() { return trainer; }
    public void setTrainer(TrainerRef trainer) { this.trainer = trainer; }
}
