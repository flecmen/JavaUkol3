package org.example.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getStaff(){ return staffService.getAllStaff();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStaff(@RequestBody Staff staff){ staffService.addStaff(staff);}

    @DeleteMapping(path = "{staffId}")
    public void delStaff(@PathVariable("staffID") Long staffId){
        staffService.deleteStaff(staffId);
    }

    @PutMapping(path = "{staffId}")
    public void updateStaff(@PathVariable("staffId") Long staffId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String email) {
        staffService.updateStaff(staffId, name, email, null, null);
    }
}
