package co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Approval
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ApprovalRepository:JpaRepository<Approval,Int> {

    @Query("select a from Approval a order by a.createAt desc")
    fun findAllApprovalByCreateAtDesc(pageable: Pageable): Page<Approval>

    @Query("select count(a.idApproval) from Approval a where a.status = :status")
    fun countApprovalByStatus(status: String): Int
}