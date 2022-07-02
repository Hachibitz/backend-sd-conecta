package br.com.sd.dtomapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import br.com.sd.model.Crm;
import br.com.sd.model.CrmDTO;
import br.com.sd.model.Doctor;
import br.com.sd.model.DoctorDTORequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorRequestMapper {
	
	default Doctor dtoToDoctorMapper(DoctorDTORequest dtoDoc){
		
		Doctor doctor = new Doctor();
		doctor.setEmail(dtoDoc.getEmail());
		doctor.setName(dtoDoc.getName());
		doctor.setSobrenome(dtoDoc.getSurname());
		doctor.setPhone(dtoDoc.getMobile_phone());
		List<Crm> crm = new ArrayList<>();
		Crm theCrm = new Crm();
		crm.add(theCrm);
		List<CrmDTO> crmDTO = dtoDoc.getCrms();
		crm.get(0).setCrm(crmDTO.get(0).getCrm());
		crm.get(0).setUf(crmDTO.get(0).getUf());
		crm.get(0).setEspecialidade(crmDTO.get(0).getEspecialidade());
		doctor.setCRM(crm);
		doctor.setPass(dtoDoc.getMobile_phone());
		return doctor;
	}
}