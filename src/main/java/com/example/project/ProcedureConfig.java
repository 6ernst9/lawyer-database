package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcedureConfig implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public ProcedureConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE OR REPLACE FUNCTION get_avg_salary_by_idp(IN idp BIGINT, IN start_date DATE, IN end_date DATE) "+
                "RETURNS FLOAT AS $$ "+
                "BEGIN "+
                "RETURN ( SELECT AVG(cm.salar_baza + cm.comision * cj.onorar / 100 / 12) "+
                "FROM Contract_m cm JOIN Contract_j cj ON (cm.id_angajat = cj.id_avocat) "+
                "WHERE (cj.data BETWEEN start_date AND end_date) AND cm.id_angajat = idp ); "+
                "END; "+
                "$$ LANGUAGE plpgsql; ");
    }
}
