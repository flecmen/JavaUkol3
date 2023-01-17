package org.example.staff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StaffConfig {

    @Bean
    CommandLineRunner commandLineRunner(StaffRepository staffRepository){
        return args -> {
            Staff bob = new Staff(
                    "Bob Vance",
                    "Vance@refrigiration.com",
                    LocalDate.of(1956, 4, 15),
                    120632,
                    35
            );

            Staff michael = new Staff(
                    "Michael Scott",
                    "mr@ping.com",
                    LocalDate.of(1965, 9, 22),
                    36350.6,
                    65
            );

            Staff jim = new Staff(
                    "Jim Halpert",
                    "halpert@seznam.cz",
                    LocalDate.of(1973, 3, 16),
                    55000,
                    60
            );

            staffRepository.saveAll(
                    List.of(bob, michael, jim)
            );
        };
    }
}
