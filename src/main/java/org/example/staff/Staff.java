package org.example.staff;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Table(name = "Staff")
@Entity
public class Staff {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "staff_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "staff_sequence"
    )
    private Long id;

    private String name;

    private String email;

    private LocalDate date_of_birth;

    private Double salary;

    private Integer monthly_working_hours;

    public Staff(long id, String name, String email, LocalDate dob, double salary, int monthly_working_hours){
        this.id = id;
        this.name = name;
        this.email = email;
        this.date_of_birth = dob;
        this.salary = salary;
        this.monthly_working_hours = monthly_working_hours;
    }

    public Staff(String name, String email, LocalDate dob, double salary, int monthly_working_hours){
        this.name = name;
        this.email = email;
        this.date_of_birth = dob;
        this.salary = salary;
        this.monthly_working_hours = monthly_working_hours;
    }

    public Staff() {
        this.name = "Bob Blob";
        this.email = "bob@webmail.com";
        this.date_of_birth = LocalDate.of(LocalDate.now().getYear()-40, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        this.salary = Double.valueOf(150000);
        this.monthly_working_hours = 40;
    }

    @Transient
    private Integer age;

    @Transient
    private double salary_per_hour;



    public Integer getAge() {return Period.between(this.date_of_birth, LocalDate.now()).getYears();}
    public double getSalary_per_hour(){return this.salary / this.monthly_working_hours;}
}
