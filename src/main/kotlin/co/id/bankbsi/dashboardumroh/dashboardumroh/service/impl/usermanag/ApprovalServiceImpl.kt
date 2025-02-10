package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Approval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.mapToApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.RemarkApproval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.MenuRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohListTravelRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohSettingRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.ApprovalService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import com.google.gson.JsonParser
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ApprovalServiceImpl(
    val approvalRepository: ApprovalRepository,
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val menuRepository: MenuRepository,
    val umrohSettingRepository: UmrohSettingRepository,
    val umrohListTravelRepository: UmrohListTravelRepository,
    val validationUtill: ValidationUtill
) : ApprovalService {
    override fun create(createApprovalRequest: CreateApprovalRequest): ApprovalResponse {
        validationUtill.validate(createApprovalRequest)
        val approval = Approval(
            maker = createApprovalRequest.maker,
            approver = createApprovalRequest.approver,
            status = createApprovalRequest.status,
            typeData = createApprovalRequest.typeData,
            dataBefore = createApprovalRequest.dataBefore,
            dataAfter = createApprovalRequest.dataAfter,
            createAt = Date(),
            updateAt = Date(),
            remarkApproval = createApprovalRequest.remarkApproval
        )
        approvalRepository.save(approval)
        return approval.mapToApprovalResponse()
    }

    override fun listApproval(listApprovalRequest: ListApprovalRequest): List<ApprovalResponse> {
        val page = approvalRepository.findAllApprovalByCreateAtDesc(
            PageRequest.of(
                listApprovalRequest.page,
                listApprovalRequest.size
            )
        )
        val approval = page.get().collect(Collectors.toList())
        return approval.map { it.mapToApprovalResponse() }
    }

    override fun update(id: Int, updateApprovalRequest: UpdateApprovalRequest): ApprovalResponse {
        val approval = findApprovalByIdOrThrowNotFound(id)
        validationUtill.validate(updateApprovalRequest)
        approval.apply {
            maker = updateApprovalRequest.maker
            approver = updateApprovalRequest.approver
            status = updateApprovalRequest.status
            typeData = updateApprovalRequest.typeData
            dataBefore = updateApprovalRequest.dataBefore
            dataAfter = updateApprovalRequest.dataAfter
            updateAt = Date()
            remarkApproval = updateApprovalRequest.remarkApproval
        }
        approvalRepository.save(approval)
        return approval.mapToApprovalResponse()
    }

    override fun get(id: Int): ApprovalResponse {
        val approval = findApprovalByIdOrThrowNotFound(id)
        return approval.mapToApprovalResponse()
    }

    override fun approveChangeSettingParameter(idApproval: Int, userLdap: String): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: return false
        if (approval.status != "Pending") {
            return false
        }
        val jsonObjectAfter = JsonParser.parseString(approval.dataAfter).asJsonObject
        val idSetting = jsonObjectAfter.get("idSetting") ?: return false
        val keySetting = jsonObjectAfter.get("keySetting")?.asString ?: return false
        val valueSetting = jsonObjectAfter.get("valueSetting")?.asString ?: return false

        val umrohSetting = umrohSettingRepository.findById(idSetting.asInt).orElseThrow() ?: return false

        umrohSetting.keySetting = keySetting
        umrohSetting.valueSetting = valueSetting
        umrohSettingRepository.save(umrohSetting)

        approval.approver = user.userLdap
        approval.status = "Approved"
        approval.updateAt = Date()
        approval.remarkApproval = "Approved by ${user.userLdap}"
        approvalRepository.save(approval)
        return true
    }

    override fun rejectChangeSettingParameter(
        idApproval: Int,
        userLdap: String,
        remarkApproval: RemarkApproval
    ): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: return false
        if (approval.status != "Pending") {
            return false
        }

        approval.approver = user.userLdap
        approval.status = "Rejected"
        approval.updateAt = Date()
        approval.remarkApproval = remarkApproval.remarkApproval
        approvalRepository.save(approval)
        return true
    }

    override fun approveRole(idApproval: Int, userLdap: String): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()

        if (approval.status != "Pending") {
            return false
        }
        val jsonObjectAfter = JsonParser.parseString(approval.dataAfter).asJsonObject
        val namaRole = jsonObjectAfter.get("namaRole")?.asString ?: throw NotFoundException()
        val idMenu = jsonObjectAfter.get("idMenu")?.asJsonArray ?: throw NotFoundException()
        val role = Role(
            namaRole = namaRole
        )
        val menus = menuRepository.findAllById(idMenu.map { it.asInt })
        role.menus = role.menus.plus(menus)
        roleRepository.save(role)

        approval.approver = user.userLdap
        approval.status = "Approved"
        approval.updateAt = Date()
        approval.remarkApproval = "Approved by ${user.userLdap}"
        approvalRepository.save(approval)
        return true
    }

    override fun rejectRole(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
        if (approval.status != "Pending") {
            return false
        }
        approval.approver = user.userLdap
        approval.status = "Rejected"
        approval.updateAt = Date()
        approval.remarkApproval = remarkApproval.remarkApproval
        approvalRepository.save(approval)
        return true
    }

    override fun approveChangeTravel(idApproval: Int, userLdap: String): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
        if (approval.status != "Pending") {
            return false
        }
        val jsonObjectAfter = JsonParser.parseString(approval.dataAfter).asJsonObject
        val idList = jsonObjectAfter.get("idList")?.asString ?: throw NotFoundException()
        val namaTravel = jsonObjectAfter.get("namaTravel")?.asString ?: throw NotFoundException()
        val alamat = jsonObjectAfter.get("alamat")?.asString ?: throw NotFoundException()
        val kota = jsonObjectAfter.get("kota")?.asString ?: throw NotFoundException()
        val email = jsonObjectAfter.get("email")?.asString ?: throw NotFoundException()
        val website = jsonObjectAfter.get("website")?.asString ?: throw NotFoundException()
        val logoTravel = jsonObjectAfter.get("logoTravel")?.asString ?: throw NotFoundException()
        val background = jsonObjectAfter.get("background")?.asString ?: throw NotFoundException()
        val telp = jsonObjectAfter.get("telp")?.asString ?: throw NotFoundException()


        val travel = umrohListTravelRepository.findById(idList).orElseThrow() ?: throw NotFoundException()
        travel.namaTravel = namaTravel
        travel.alamat = alamat
        travel.kota = kota
        travel.email = email
        travel.website = website
        travel.logoTravel = logoTravel
        travel.background = background
        travel.telp = telp

        umrohListTravelRepository.save(travel)

        approval.approver = user.userLdap
        approval.status = "Approved"
        approval.updateAt = Date()
        approval.remarkApproval = "Approved by ${user.userLdap}"
        approvalRepository.save(approval)
        return true
    }

    override fun rejectChangeTravel(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
        if (approval.status != "Pending") {
            return false
        }
        approval.approver = user.userLdap
        approval.status = "Rejected"
        approval.updateAt = Date()
        approval.remarkApproval = remarkApproval.remarkApproval
        approvalRepository.save(approval)
        return true
    }

    override fun approveUser(idApproval: Int, userLdap: String): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val marker = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
        if (approval.status != "Pending") {
            return false
        }
        val jsonObjectAfter = JsonParser.parseString(approval.dataAfter).asJsonObject
        val nama = jsonObjectAfter.get("nama")?.asString ?: throw NotFoundException()
        val unit = jsonObjectAfter.get("unit")?.asString ?: throw NotFoundException()
        val idRole = jsonObjectAfter.get("idRole")?.asInt ?: throw NotFoundException()
        val status = jsonObjectAfter.get("status")?.asString ?: throw NotFoundException()
        val userLdapObj = jsonObjectAfter.get("userLdap")?.asString ?: throw NotFoundException()

        val role = roleRepository.findById(idRole).orElseThrow() ?: throw NotFoundException()
        val user = User(
            nama = nama,
            unit = unit,
            idRole = role,
            status = status,
            createdAt = Date(),
            lastLogin = Date(),
            userLdap = userLdapObj
        )
        userRepository.save(user)

        approval.approver = marker.userLdap
        approval.status = "Approved"
        approval.updateAt = Date()
        approval.remarkApproval = "Approved by ${marker.userLdap}"
        approvalRepository.save(approval)
        return true
    }

    override fun rejectUser(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean {
        val approval = approvalRepository.findById(idApproval).orElseThrow()
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
        if (approval.status != "Pending") {
            return false
        }
        approval.approver = user.userLdap
        approval.status = "Rejected"
        approval.updateAt = Date()
        approval.remarkApproval = remarkApproval.remarkApproval
        approvalRepository.save(approval)
        return true
    }




    private fun findApprovalByIdOrThrowNotFound(id: Int): Approval {
        val approval = approvalRepository.findByIdOrNull(id)
        if (approval == null) {
            throw NotFoundException()
        } else {
            return approval
        }
    }
}