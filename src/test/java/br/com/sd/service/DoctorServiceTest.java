package br.com.sd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sd.auth.Token;
import br.com.sd.dtomapper.DoctorRequestMapper;
import br.com.sd.exception.UnauthorizedException;
import br.com.sd.model.Crm;
import br.com.sd.model.CrmDTO;
import br.com.sd.model.Doctor;
import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import br.com.sd.proxy.DoctorProxy;
import br.com.sd.repository.CrmRepository;
import br.com.sd.repository.DoctorRepository;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
	
	@InjectMocks
	DoctorService doctorService;
	
	@Mock
	DoctorRepository doctorRepository;
	
	@Mock
	CrmRepository crmRepository;
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	DoctorProxy proxy;
	
	@Mock
	DoctorRequestMapper doctorRequestMapper;
	
	@Test
	public void updateDoctor() {
		Mockito.when(doctorRepository.save(Mockito.any())).thenReturn(null);
		String response = doctorService.updateDoctor(1L, new Doctor());
		assertEquals(response, "Update realizado!");
	}
	
	@Test
	public void deleteDoctor() {
		Mockito.doNothing().when(crmRepository).deleteById(Mockito.any());
		Mockito.doNothing().when(doctorRepository).deleteById(Mockito.any());
		String response = doctorService.deleteDoctor(1L);
		assertEquals(response, "Delete realizado!");
	}
	
	@Test
	public void findDoctorById() {
		Mockito.when(doctorRepository.getReferenceById(Mockito.any())).thenReturn(new Doctor());
		Doctor response = doctorService.findDoctorId(1L);
		assertNotNull(response);
	}
	
	@Test
	public void getDoctors1() {
		List<Doctor> doctor = new ArrayList<>();
		Mockito.when(doctorRepository.findByName(Mockito.any())).thenReturn(doctor);
		List<Doctor> response = doctorService.getDoctors(Mockito.anyString(), null);
		assertNotNull(response);
	}
	
	@Test
	public void getDoctors2() {
		List<Doctor> doctor = new ArrayList<>();
		Mockito.when(doctorRepository.findBySpecialty(Mockito.any())).thenReturn(doctor);
		List<Doctor> response = doctorService.getDoctors(null, Mockito.anyString());
		assertNotNull(response);
	}
	
	@Test
	public void getDoctors3() {
		List<Doctor> doctor = new ArrayList<>();
		Mockito.when(doctorRepository.findByNameAndSpecialty(Mockito.any(), Mockito.any())).thenReturn(doctor);
		List<Doctor> response = doctorService.getDoctors(Mockito.anyString(), Mockito.anyString());
		assertNotNull(response);
	}
	
	@Test
	public void getDoctors4() {
		List<Doctor> doctor = new ArrayList<>();
		Mockito.when(doctorRepository.findAll()).thenReturn(doctor);
		List<Doctor> response = doctorService.getDoctors(null, null);
		assertNotNull(response);
	}
	
	@Test
	public void login() {
		UnauthorizedException thrown = Assertions.assertThrows(UnauthorizedException.class, () -> {
			Mockito.when(doctorRepository.findByEmail(Mockito.any())).thenReturn(createDoctor());
			Mockito.when(doctorRepository.save(Mockito.any())).thenReturn(new Doctor());
			ResponseEntity<DoctorDTOResponse> response = doctorService.login("mockmail@email.com", Mockito.anyString());
		});
		Assertions.assertEquals(("Usuário ou senha inválido."), thrown.getMessage());
	}
	
	@Test
	public void insertDoctorTest() {
		Mockito.when(proxy.getToken(Mockito.any())).thenReturn(ResponseEntity.ok(createToken()));
		Mockito.when(proxy.insertDoctor(Mockito.any(), Mockito.anyString())).thenReturn(ResponseEntity.ok(new DoctorDTOResponse()));
		Mockito.when(doctorRepository.save(Mockito.any())).thenReturn(new Doctor());
		ResponseEntity<DoctorDTOResponse> response = doctorService.insertDoctor(createDoctorRequest());
		assertEquals(response, ResponseEntity.ok().body(new DoctorDTOResponse()));
	}
	
	public DoctorDTOResponse doctorDTOResponseBuilder(String status) {
		DoctorDTOResponse dtoResponse = new DoctorDTOResponse();
		if(status.equals("Authorized")) {
			dtoResponse.setAccess_token("MockAccessToken");
			dtoResponse.setAuthorization_status("Authorized");
			dtoResponse.setRefresh_token("MockRefreshToken");
			return dtoResponse;
		}else {
			dtoResponse.setAccess_token(null);
			dtoResponse.setAuthorization_status("Unauthorized");
			dtoResponse.setRefresh_token(null);
			return dtoResponse;
		}
	}
	
	public Token createToken() {
		Token token = new Token();
		token.setAccess_token("mock");
		token.setExpires_in(0000);
		token.setScope("mock");
		token.setToken_type("Bearer");
		return token;
	}
	
	public DoctorDTORequest createDoctorRequest() {
		List<CrmDTO> crmList = new ArrayList<>();
		CrmDTO crm = new CrmDTO();
		crmList.add(crm);
		DoctorDTORequest theDoctor = new DoctorDTORequest();
		theDoctor.setCrms(crmList);
		theDoctor.setEmail("anymail@email.com");
		theDoctor.setMobile_phone(null);
		theDoctor.setName("DoctorTest");
		theDoctor.setSurname("Testo");
		return theDoctor;
	}
	
	public Doctor createDoctor() {
		List<Crm> crmList = new ArrayList<>();
		Crm crm = new Crm();
		crmList.add(crm);
		Doctor theDoctor = new Doctor();
		theDoctor.setCRM(crmList);
		theDoctor.setEmail("anymail@email.com");
		theDoctor.setPhone(null);
		theDoctor.setName("DoctorTest");
		theDoctor.setSobrenome("Testo");
		theDoctor.setPass("00000001");
		return theDoctor;
	}
	
	public DoctorDTORequest createBadDoctor() {
		DoctorDTORequest theDoctor = new DoctorDTORequest();
		theDoctor.setCrms(null);
		theDoctor.setEmail(null);
		theDoctor.setMobile_phone(null);
		theDoctor.setName(null);
		theDoctor.setSurname(null);
		return theDoctor;
	}
}
