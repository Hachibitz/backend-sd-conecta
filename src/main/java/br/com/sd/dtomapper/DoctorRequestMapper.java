package br.com.sd.dtomapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

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
		doctor.setCRM(dtoDoc.getCrms());
		return doctor;
	}
	
}