package br.com.sd.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import br.com.sd.exception.UnauthorizedException;
import br.com.sd.model.Crm;
import br.com.sd.model.Doctor;
import br.com.sd.model.DoctorDTORequest;
import br.com.sd.model.DoctorDTOResponse;
import br.com.sd.repository.DoctorRepository;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
	
	@InjectMocks
	DoctorService doctorService;
	
	@Mock
	DoctorRepository repository;
	
	@Mock
	Doctor doctorMock;
	
	@Mock
	RestTemplate restTemplate;
	
    @BeforeEach
    public void init() {
        restTemplate = new RestTemplate();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(doctorService, "restTemplate", restTemplate);
    }
	
	@Test
	public void updateDoctor() {
		Mockito.when(doctorService.updateDoctor(Mockito.anyLong(), Mockito.any(Doctor.class))).thenReturn("Update successful!");
		Mockito.when(doctorService.updateDoctor(Mockito.anyLong(), null)).thenReturn("No body!");
		Mockito.when(doctorService.updateDoctor(null, Mockito.any(Doctor.class))).thenReturn("Id not Found!");
		Mockito.when(doctorService.updateDoctor(null, null)).thenReturn("Invalid request: no Id and no body!");
	}
	
	@Test
	public void deleteDoctor() {
		Mockito.when(doctorService.deleteDoctor(Mockito.anyLong())).thenReturn("Delete successful!");
		Mockito.when(doctorService.deleteDoctor(null)).thenReturn("Id not Found!");
	}
	
	@Test
	public void findDoctorById() {
		Mockito.when(doctorService.findDoctorId(Mockito.anyLong())).thenReturn(doctorMock);
		Mockito.when(doctorService.findDoctorId(null)).thenThrow(NullPointerException.class);
	}
	
	@Test
	public void getDoctors() {
		Mockito.when(doctorService.getDoctors(null, null)).thenReturn(repository.findAll());
		Mockito.when(doctorService.getDoctors(null, Mockito.anyString())).thenReturn(repository.findBySpecialty("mockSpecialty"));
		Mockito.when(doctorService.getDoctors(Mockito.anyString(), null)).thenReturn(repository.findByName("mockName"));
		Mockito.when(doctorService.getDoctors(Mockito.anyString(), Mockito.anyString())).thenReturn(repository.findByNameAndSpecialty("mockName", "mockSpecialty"));
	}
	
	@Test
	public void login() {
		Mockito.when(doctorService.login(null, null)).thenThrow(UnauthorizedException.class);
		Mockito.when(doctorService.login(null, Mockito.anyString())).thenThrow(UnauthorizedException.class);
		Mockito.when(doctorService.login(Mockito.anyString(), null)).thenThrow(UnauthorizedException.class);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class),
                ArgumentMatchers.<Class<DoctorDTOResponse>>any())).thenReturn(ResponseEntity.ok(doctorDTOResponseBuilder("Authorized")));
	}
	
	@Test
	public void inserDoctorTest() {
		DoctorDTORequest theDoctor = createDoctor();
		DoctorDTORequest badDoctor = createBadDoctor();
		ResponseEntity<DoctorDTOResponse> badResponse = new ResponseEntity<DoctorDTOResponse>(HttpStatus.BAD_REQUEST);
		ResponseEntity<DoctorDTOResponse> goodResponse = new ResponseEntity<DoctorDTOResponse>(HttpStatus.OK);
		Mockito.when(doctorService.insertDoctor(theDoctor)).thenReturn(goodResponse);
		Mockito.when(doctorService.insertDoctor(badDoctor)).thenReturn(badResponse);
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
	
	public DoctorDTORequest createDoctor() {
		List<Crm> crmList = new ArrayList<>();
		DoctorDTORequest theDoctor = new DoctorDTORequest();
		theDoctor.setCrms(crmList);
		theDoctor.setEmail("anymail@email.com");
		theDoctor.setMobile_phone(null);
		theDoctor.setName("DoctorTest");
		theDoctor.setSurname("Testo");
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
