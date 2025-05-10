package in.harshal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.harshal.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
