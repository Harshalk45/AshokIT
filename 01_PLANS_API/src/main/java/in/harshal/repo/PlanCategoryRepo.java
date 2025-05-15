package in.harshal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.harshal.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {

}
