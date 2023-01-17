package org.example.staff;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff(){ return staffRepository.findAll();}

    public void addStaff(Staff staff) {
        Optional<Staff> staffOptional = staffRepository.findStaffByEmail(staff.getEmail());

        if (staffOptional.isPresent()) {
            throw new IllegalStateException("Email is taken");
        }

        staffRepository.save(staff);
    }

    public void deleteStaff(Long staffId) {
        boolean exists = staffRepository.existsById(staffId);
        if (!exists) {
            throw new IllegalStateException(
                    "Staff member with id" + staffId + "does not exist"
            );
        }
        staffRepository.deleteById(staffId);
    }

    @Transactional
    public void updateStaff(Long staffId, String name, String email, Double salary, Integer monthly_working_hours) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(
                () -> new IllegalStateException(
                        "Staff member with id" + staffId + "does not exist"
                )
        );

        if (name != null && name.length() > 0 && !Objects.equals(staff.getName(), name)) {
            staff.setName(name);
        }

        if (email != null &&
                email.length() > 0 && !Objects.equals(staff.getEmail(), email)) {
            Optional<Staff> staffOptional = staffRepository.findStaffByEmail(email);
            if (staffOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            staff.setEmail(email);
        }

        if (salary != null && salary >= 0){
            staff.setSalary(salary);
        }else{
            throw new IllegalArgumentException("salary cannot be negative");
        }

        if (monthly_working_hours != null && monthly_working_hours >= 0){
            staff.setMonthly_working_hours(monthly_working_hours);
        }else{
            throw new IllegalArgumentException("working hours cannot be less than 0");
        }
    }
}
