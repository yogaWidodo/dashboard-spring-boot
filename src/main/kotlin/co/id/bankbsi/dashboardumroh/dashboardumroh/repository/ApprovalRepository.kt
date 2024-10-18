package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Approval
import org.springframework.data.jpa.repository.JpaRepository

interface ApprovalRepository:JpaRepository<Approval,String> {

}