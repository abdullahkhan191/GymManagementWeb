package com.gym.controller;

import com.gym.model.Member;
import com.gym.model.MembershipPlan;
import com.gym.model.Trainer;
import com.gym.repository.MemberRepository;
import com.gym.repository.PlanRepository;
import com.gym.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GymController {

    @Autowired
    private MemberRepository memberRepo;
    @Autowired
    private TrainerRepository trainerRepo;
    @Autowired
    private PlanRepository planRepo;

    // ─── HOME ───────────────────────────────────────────────────────────────
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalMembers", memberRepo.count());
        model.addAttribute("activeMembers", memberRepo.findByStatus("Active").size());
        model.addAttribute("totalTrainers", trainerRepo.count());
        model.addAttribute("totalPlans", planRepo.count());
        return "index";
    }

    // ─── MEMBERS ────────────────────────────────────────────────────────────
    @GetMapping("/members")
    public String members(Model model,
                          @RequestParam(required = false) String search,
                          @RequestParam(required = false) String status) {
        List<Member> members;
        if (search != null && !search.isEmpty()) {
            members = memberRepo.findByFull_nameContainingIgnoreCase(search);
        } else if (status != null && !status.isEmpty() && !status.equals("All")) {
            members = memberRepo.findByStatus(status);
        } else {
            members = memberRepo.findAll();
        }
        model.addAttribute("members", members);
        model.addAttribute("plans", planRepo.findByIs_active("Yes"));
        model.addAttribute("trainers", trainerRepo.findByIs_active("Yes"));
        model.addAttribute("newMember", new Member());
        return "members";
    }

    @PostMapping("/members/add")
    public String addMember(@RequestParam String full_name,
                            @RequestParam String phone,
                            @RequestParam String gender,
                            @RequestParam Integer age,
                            @RequestParam String status,
                            @RequestParam Integer plan_id,
                            @RequestParam Integer trainer_id) {
        Member m = new Member();
        // Generate new ID
        List<Member> all = memberRepo.findAll();
        int newId = all.stream().mapToInt(mb -> mb.getId() != null ? mb.getId() : 0).max().orElse(100) + 1;
        m.setId(newId);
        m.setFull_name(full_name);
        m.setPhone(phone);
        m.setGender(gender);
        m.setAge(age);
        m.setStatus(status);
        m.setJoin_date(new SimpleDateFormat("dd-MMM-yy").format(new Date()));

        if (plan_id != null) {
            Optional<MembershipPlan> plan = planRepo.findById(plan_id);
            plan.ifPresent(p -> {
            Member.PlanRef pr = new Member.PlanRef();
            pr.setPlan_id(p.getId());
            pr.setPlan_name(p.getPlan_name());
            pr.setFee(p.getFee());
            m.setPlan(pr);
            });
        }

        if (trainer_id != null) {
            Optional<Trainer> trainer = trainerRepo.findById(trainer_id);
            trainer.ifPresent(t -> {
            Member.TrainerRef tr = new Member.TrainerRef();
            tr.setTrainer_id(t.getId());
            tr.setTrainer_name(t.getFull_name());
            m.setTrainer(tr);
            });
        }

        memberRepo.save(m);
        return "redirect:/members";
    }

    @GetMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Integer id) {
        if (id != null) {
            memberRepo.deleteById(id);
        }
        return "redirect:/members";
    }

    // ─── TRAINERS ───────────────────────────────────────────────────────────
    @GetMapping("/trainers")
    public String trainers(Model model) {
        model.addAttribute("trainers", trainerRepo.findAll());
        return "trainers";
    }

    @PostMapping("/trainers/add")
    public String addTrainer(@RequestParam String full_name,
                             @RequestParam String phone,
                             @RequestParam String specialization,
                             @RequestParam String is_active) {
        Trainer t = new Trainer();
        List<Trainer> all = trainerRepo.findAll();
        int newId = all.stream().mapToInt(tr -> tr.getId() != null ? tr.getId() : 0).max().orElse(200) + 1;
        t.setId(newId);
        t.setFull_name(full_name);
        t.setPhone(phone);
        t.setSpecialization(specialization);
        t.setIs_active(is_active);
        t.setHire_date(new SimpleDateFormat("dd-MMM-yy").format(new Date()));
        trainerRepo.save(t);
        return "redirect:/trainers";
    }

    @GetMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable Integer id) {
        if (id != null) {
            trainerRepo.deleteById(id);
        }
        return "redirect:/trainers";
    }

    // ─── PLANS ──────────────────────────────────────────────────────────────
    @GetMapping("/plans")
    public String plans(Model model) {
        model.addAttribute("plans", planRepo.findAll());
        return "plans";
    }

    @PostMapping("/plans/add")
    public String addPlan(@RequestParam String plan_name,
                          @RequestParam Integer duration_months,
                          @RequestParam Double fee,
                          @RequestParam String description,
                          @RequestParam String is_active) {
        MembershipPlan p = new MembershipPlan();
        List<MembershipPlan> all = planRepo.findAll();
        int newId = all.stream().mapToInt(pl -> pl.getId() != null ? pl.getId() : 0).max().orElse(300) + 1;
        p.setId(newId);
        p.setPlan_name(plan_name);
        p.setDuration_months(duration_months);
        p.setFee(fee);
        p.setDescription(description);
        p.setIs_active(is_active);
        planRepo.save(p);
        return "redirect:/plans";
    }

    @GetMapping("/plans/delete/{id}")
    public String deletePlan(@PathVariable Integer id) {
        if (id != null) {
            planRepo.deleteById(id);
        }
        return "redirect:/plans";
    }
}
