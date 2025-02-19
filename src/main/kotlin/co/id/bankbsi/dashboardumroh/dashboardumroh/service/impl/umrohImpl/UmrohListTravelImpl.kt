package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohListTravel
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.toResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Approval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.mapToApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.ListTravelListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohListTravelResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohListTravelRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohListTravelService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import com.google.gson.JsonObject
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UmrohListTravelImpl(
    private val umrohListTravelRepository: UmrohListTravelRepository,
    private val userRepository: UserRepository,
    private val approvalRepository: ApprovalRepository,
    private val validationUtill: ValidationUtill
) : UmrohListTravelService {
    override fun create(umrohListTravelRequest: UmrohListTravelRequest): UmrohListTravelResponse {
        validationUtill.validate(umrohListTravelRequest)
        val travel = UmrohListTravel(
            idList = umrohListTravelRequest.idList,
            kdTravel = umrohListTravelRequest.kdTravel,
            namaTravel = umrohListTravelRequest.namaTravel,
            alamat = umrohListTravelRequest.alamat,
            kota = umrohListTravelRequest.kota,
            email = umrohListTravelRequest.email,
            website = umrohListTravelRequest.website,
            logoTravel = umrohListTravelRequest.logoTravel,
            background = umrohListTravelRequest.background,
            telp = umrohListTravelRequest.telp
        )
        umrohListTravelRepository.save(travel)
        return travel.toResponse()
    }

    override fun list(listRequest: ListTravelListRequest): List<UmrohListTravelResponse> {
        val page = umrohListTravelRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val travel = page.get().collect(Collectors.toList())
        return travel.map { it.toResponse() }

    }

    override fun get(id: String): UmrohListTravelResponse {
        val travel = findUmrohListTravelOrThrowNotFound(id)
        return travel.toResponse()

    }

    override fun update(
        id: String,
        userLdap: String,
        umrohListTravelUpdate: UmrohListTravelUpdate
    ): ApprovalResponse {
        validationUtill.validate(umrohListTravelUpdate)
        val travel = umrohListTravelRepository.findById(id).orElseThrow { NotFoundException() }
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
//        val dataBefore = "{idList=${travel.idList}, namaTravel=${travel.namaTravel}, alamat=${travel.alamat}, kota=${travel.kota}, email=${travel.email}, website=${travel.website}, logoTravel=${travel.logoTravel}, background=${travel.background}, telp=${travel.telp}}"
//        val dataAfter = "{idList=${travel.idList}, namaTravel=${umrohListTravelUpdate.namaTravel}, alamat=${umrohListTravelUpdate.alamat}, kota=${umrohListTravelUpdate.kota}, email=${umrohListTravelUpdate.email}, website=${umrohListTravelUpdate.website}, logoTravel=${umrohListTravelUpdate.logoTravel}, background=${umrohListTravelUpdate.background}, telp=${umrohListTravelUpdate.telp}}"
        val travelJsonDataBefore = JsonObject().apply {
            addProperty("idList", travel.idList)
            addProperty("namaTravel", travel.namaTravel)
            addProperty("alamat", travel.alamat)
            addProperty("kota", travel.kota)
            addProperty("email", travel.email)
            addProperty("website", travel.website)
            addProperty("logoTravel", travel.logoTravel)
            addProperty("background", travel.background)
            addProperty("telp", travel.telp)
        }

        val travelJsonDataAfter = JsonObject().apply {
            addProperty("idList", travel.idList)
            addProperty("namaTravel", umrohListTravelUpdate.namaTravel)
            addProperty("alamat", umrohListTravelUpdate.alamat)
            addProperty("kota", umrohListTravelUpdate.kota)
            addProperty("email", umrohListTravelUpdate.email)
            addProperty("website", umrohListTravelUpdate.website)
            addProperty("logoTravel", umrohListTravelUpdate.logoTravel)
            addProperty("background", umrohListTravelUpdate.background)
            addProperty("telp", umrohListTravelUpdate.telp)
        }


        val approval = Approval(
            maker = user.userLdap,
            approver = "",
            status = "Pending",
            typeData = "Edit Umroh List Travel",
            dataBefore = travelJsonDataBefore.toString(),
            dataAfter = travelJsonDataAfter.toString(),
            createAt = Date(),
            updateAt = Date(),
            remarkApproval = ""
        )
        approvalRepository.save(approval)
        return approval.mapToApprovalResponse()
    }


    private fun findUmrohListTravelOrThrowNotFound(id: String): UmrohListTravel {
        return umrohListTravelRepository.findById(id)
            .orElseThrow { IllegalArgumentException("UmrohListTravel not found with id: $id") }
    }


}