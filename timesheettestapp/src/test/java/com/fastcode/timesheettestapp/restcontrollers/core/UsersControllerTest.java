package com.fastcode.timesheettestapp.restcontrollers.core;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import java.time.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.core.env.Environment;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;
import com.fastcode.timesheettestapp.commons.search.SearchUtils;
import com.fastcode.timesheettestapp.application.core.users.UsersAppService;
import com.fastcode.timesheettestapp.application.core.users.dto.*;
import com.fastcode.timesheettestapp.domain.core.users.IUsersRepository;
import com.fastcode.timesheettestapp.domain.core.users.UsersEntity;
import com.fastcode.timesheettestapp.domain.core.timesheet.ITimesheetRepository;
import com.fastcode.timesheettestapp.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheettestapp.domain.core.users.IUsersRepository;
import com.fastcode.timesheettestapp.domain.core.users.UsersEntity;
import com.fastcode.timesheettestapp.domain.core.timesheetstatus.ITimesheetstatusRepository;
import com.fastcode.timesheettestapp.domain.core.timesheetstatus.TimesheetstatusEntity;
import com.fastcode.timesheettestapp.application.core.timesheet.TimesheetAppService;    
import com.fastcode.timesheettestapp.application.core.usertask.UsertaskAppService;    

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				properties = "spring.profiles.active=test")
public class UsersControllerTest {
	
	@Autowired
	protected SortHandlerMethodArgumentResolver sortArgumentResolver;

	@Autowired
	@Qualifier("usersRepository") 
	protected IUsersRepository users_repository;
	
	@Autowired
	@Qualifier("timesheetRepository") 
	protected ITimesheetRepository timesheetRepository;
	
	@Autowired
	@Qualifier("usersRepository") 
	protected IUsersRepository usersRepository;
	
	@Autowired
	@Qualifier("timesheetstatusRepository") 
	protected ITimesheetstatusRepository timesheetstatusRepository;
	
	@SpyBean
	@Qualifier("usersAppService")
	protected UsersAppService usersAppService;
	
    @SpyBean
    @Qualifier("timesheetAppService")
	protected TimesheetAppService  timesheetAppService;
	
    @SpyBean
    @Qualifier("usertaskAppService")
	protected UsertaskAppService  usertaskAppService;
	
	@SpyBean
	protected LoggingHelper logHelper;

	@SpyBean
	protected Environment env;

	@Mock
	protected Logger loggerMock;

	protected UsersEntity users;

	protected MockMvc mvc;
	
	@Autowired
	EntityManagerFactory emf;
	
    static EntityManagerFactory emfs;
    
    static int relationCount = 10;
    
	int countTimesheet = 10;
	
	int countUsers = 10;
	
	int countTimesheetstatus = 10;
	
	@PostConstruct
	public void init() {
	emfs = emf;
	}

	@AfterClass
	public static void cleanup() {
		EntityManager em = emfs.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

		em.createNativeQuery("truncate table timesheet.users RESTART IDENTITY").executeUpdate();


		em.createNativeQuery("truncate table timesheet.timesheet RESTART IDENTITY").executeUpdate();

		em.createNativeQuery("truncate table timesheet.users RESTART IDENTITY").executeUpdate();

		em.createNativeQuery("truncate table timesheet.timesheetstatus RESTART IDENTITY").executeUpdate();
	 	em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
		em.getTransaction().commit();
	}
	
	public TimesheetEntity createTimesheetEntity() {
	
		if(countTimesheet>60) {
			countTimesheet = 10;
		}
		
		TimesheetEntity timesheetEntity = new TimesheetEntity();
		timesheetEntity.setId(Long.valueOf(relationCount));
  		timesheetEntity.setNotes(String.valueOf(relationCount));
		timesheetEntity.setPeriodendingdate(SearchUtils.stringToLocalDate("19"+countTimesheet+"-09-01"));
		timesheetEntity.setPeriodstartingdate(SearchUtils.stringToLocalDate("19"+countTimesheet+"-09-01"));
		timesheetEntity.setVersiono(0L);
		relationCount++;
		UsersEntity users= createUsersEntity();
		timesheetEntity.setUsers(users);
		TimesheetstatusEntity timesheetstatus= createTimesheetstatusEntity();
		timesheetEntity.setTimesheetstatus(timesheetstatus);
		if(!timesheetRepository.findAll().contains(timesheetEntity))
		{
			 timesheetEntity = timesheetRepository.save(timesheetEntity);
		}
		countTimesheet++;
	    return timesheetEntity;
	}
	public UsersEntity createUsersEntity() {
	
		if(countUsers>60) {
			countUsers = 10;
		}
		
		UsersEntity usersEntity = new UsersEntity();
  		usersEntity.setEmailaddress(String.valueOf(relationCount));
  		usersEntity.setFirstname(String.valueOf(relationCount));
		usersEntity.setId(Long.valueOf(relationCount));
		usersEntity.setIsactive(false);
		usersEntity.setIsemailconfirmed(false);
		usersEntity.setJoinDate(SearchUtils.stringToLocalDate("19"+countUsers+"-09-01"));
  		usersEntity.setLastname(String.valueOf(relationCount));
  		usersEntity.setPassword(String.valueOf(relationCount));
  		usersEntity.setUsername(String.valueOf(relationCount));
		usersEntity.setVersiono(0L);
		relationCount++;
		if(!usersRepository.findAll().contains(usersEntity))
		{
			 usersEntity = usersRepository.save(usersEntity);
		}
		countUsers++;
	    return usersEntity;
	}
	public TimesheetstatusEntity createTimesheetstatusEntity() {
	
		if(countTimesheetstatus>60) {
			countTimesheetstatus = 10;
		}
		
		TimesheetstatusEntity timesheetstatusEntity = new TimesheetstatusEntity();
		timesheetstatusEntity.setId(Long.valueOf(relationCount));
  		timesheetstatusEntity.setStatusname(String.valueOf(relationCount));
		timesheetstatusEntity.setVersiono(0L);
		relationCount++;
		if(!timesheetstatusRepository.findAll().contains(timesheetstatusEntity))
		{
			 timesheetstatusEntity = timesheetstatusRepository.save(timesheetstatusEntity);
		}
		countTimesheetstatus++;
	    return timesheetstatusEntity;
	}

	public UsersEntity createEntity() {
	
		UsersEntity usersEntity = new UsersEntity();
  		usersEntity.setEmailaddress("1");
  		usersEntity.setFirstname("1");
		usersEntity.setId(1L);
		usersEntity.setIsactive(false);
		usersEntity.setIsemailconfirmed(false);
    	usersEntity.setJoinDate(SearchUtils.stringToLocalDate("1996-09-01"));
  		usersEntity.setLastname("1");
  		usersEntity.setPassword("1");
  		usersEntity.setUsername("1");
		usersEntity.setVersiono(0L);
		
		return usersEntity;
	}

	public CreateUsersInput createUsersInput() {
	
	    CreateUsersInput usersInput = new CreateUsersInput();
  		usersInput.setEmailaddress("5");
  		usersInput.setFirstname("5");
		usersInput.setIsactive(false);
		usersInput.setIsemailconfirmed(false);
    	usersInput.setJoinDate(SearchUtils.stringToLocalDate("1996-08-10"));
  		usersInput.setLastname("5");
  		usersInput.setPassword("5");
  		usersInput.setUsername("5");
		
		return usersInput;
	}

	public UsersEntity createNewEntity() {
		UsersEntity users = new UsersEntity();
		users.setEmailaddress("3");
		users.setFirstname("3");
		users.setId(3L);
		users.setIsactive(false);
		users.setIsemailconfirmed(false);
    	users.setJoinDate(SearchUtils.stringToLocalDate("1996-08-11"));
		users.setLastname("3");
		users.setPassword("3");
		users.setUsername("3");
		
		return users;
	}
	
	public UsersEntity createUpdateEntity() {
		UsersEntity users = new UsersEntity();
		users.setEmailaddress("4");
		users.setFirstname("4");
		users.setId(4L);
		users.setIsactive(false);
		users.setIsemailconfirmed(false);
    	users.setJoinDate(SearchUtils.stringToLocalDate("1996-09-09"));
		users.setLastname("4");
		users.setPassword("4");
		users.setUsername("4");
		
		return users;
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
    
		final UsersController usersController = new UsersController(usersAppService, timesheetAppService, usertaskAppService,
	logHelper,env);
		when(logHelper.getLogger()).thenReturn(loggerMock);
		doNothing().when(loggerMock).error(anyString());

		this.mvc = MockMvcBuilders.standaloneSetup(usersController)
				.setCustomArgumentResolvers(sortArgumentResolver)
				.setControllerAdvice()
				.build();
	}

	@Before
	public void initTest() {

		users= createEntity();
		List<UsersEntity> list= users_repository.findAll();
		if(!list.contains(users)) {
			users=users_repository.save(users);
		}

	}

	@Test
	public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
	
		mvc.perform(get("/users/" + users.getId()+"/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}  

	@Test
	public void FindById_IdIsNotValid_ReturnStatusNotFound() {

		 org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(get("/users/999")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Not found"));

	}
	@Test
	public void CreateUsers_UsersDoesNotExist_ReturnStatusOk() throws Exception {
		CreateUsersInput usersInput = createUsersInput();	
			

		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
	
		String json = ow.writeValueAsString(usersInput);

		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

	}     
	
	

	@Test
	public void DeleteUsers_IdIsNotValid_ThrowEntityNotFoundException() {

        doReturn(null).when(usersAppService).findById(999L);
        org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(delete("/users/999")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new EntityNotFoundException("There does not exist a users with a id=999"));

	}  

	@Test
	public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
	
	 	UsersEntity entity =  createNewEntity();
	 	entity.setVersiono(0L);
		entity = users_repository.save(entity);
		

		FindUsersByIdOutput output= new FindUsersByIdOutput();
		output.setEmailaddress(entity.getEmailaddress());
		output.setFirstname(entity.getFirstname());
		output.setId(entity.getId());
		output.setIsactive(entity.getIsactive());
		output.setIsemailconfirmed(entity.getIsemailconfirmed());
		output.setLastname(entity.getLastname());
		output.setPassword(entity.getPassword());
		output.setUsername(entity.getUsername());
		
         Mockito.doReturn(output).when(usersAppService).findById(entity.getId());
       
    //    Mockito.when(usersAppService.findById(entity.getId())).thenReturn(output);
        
		mvc.perform(delete("/users/" + entity.getId()+"/")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}  


	@Test
	public void UpdateUsers_UsersDoesNotExist_ReturnStatusNotFound() throws Exception {
   
        doReturn(null).when(usersAppService).findById(999L);
        
        UpdateUsersInput users = new UpdateUsersInput();
  		users.setEmailaddress("999");
  		users.setFirstname("999");
		users.setId(999L);
		users.setIsactive(false);
		users.setIsemailconfirmed(false);
		users.setJoinDate(SearchUtils.stringToLocalDate("1996-09-28"));
  		users.setLastname("999");
  		users.setPassword("999");
  		users.setUsername("999");

		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(users);

		 org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(put("/users/999").contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Unable to update. Users with id=999 not found."));
	}    

	@Test
	public void UpdateUsers_UsersExists_ReturnStatusOk() throws Exception {
		UsersEntity entity =  createUpdateEntity();
		entity.setVersiono(0L);
		
		entity = users_repository.save(entity);
		FindUsersByIdOutput output= new FindUsersByIdOutput();
		output.setEmailaddress(entity.getEmailaddress());
		output.setFirstname(entity.getFirstname());
		output.setId(entity.getId());
		output.setIsactive(entity.getIsactive());
		output.setIsemailconfirmed(entity.getIsemailconfirmed());
		output.setJoinDate(entity.getJoinDate());
		output.setLastname(entity.getLastname());
		output.setPassword(entity.getPassword());
		output.setUsername(entity.getUsername());
		output.setVersiono(entity.getVersiono());
		
        Mockito.when(usersAppService.findById(entity.getId())).thenReturn(output);
        
		UpdateUsersInput usersInput = new UpdateUsersInput();
		usersInput.setEmailaddress(entity.getEmailaddress());
		usersInput.setFirstname(entity.getFirstname());
		usersInput.setId(entity.getId());
		usersInput.setIsactive(entity.getIsactive());
		usersInput.setIsemailconfirmed(entity.getIsemailconfirmed());
		usersInput.setLastname(entity.getLastname());
		usersInput.setPassword(entity.getPassword());
		usersInput.setUsername(entity.getUsername());
		
		
		
		ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(usersInput);
	
		mvc.perform(put("/users/" + entity.getId()+"/").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());

		UsersEntity de = createUpdateEntity();
		de.setId(entity.getId());
		users_repository.delete(de);
		

	}    
	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {

		mvc.perform(get("/users?search=id[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}    

	@Test
	public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {

		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/users?search=usersid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())).hasCause(new Exception("Wrong URL Format: Property usersid not found!"));

	} 
	
	
    @Test
	public void GetTimesheets_searchIsNotEmptyAndPropertyIsNotValid_ThrowException() {
	
		Map<String,String> joinCol = new HashMap<String,String>();
		joinCol.put("id", "1");

		Mockito.when(usersAppService.parseTimesheetsJoinColumn("userid")).thenReturn(joinCol);
		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/users/1/timesheets?search=abc[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
	    		  .andExpect(status().isOk())).hasCause(new Exception("Wrong URL Format: Property abc not found!"));
	
	}    
	
	@Test
	public void GetTimesheets_searchIsNotEmptyAndPropertyIsValid_ReturnList() throws Exception {

		Map<String,String> joinCol = new HashMap<String,String>();
		joinCol.put("id", "1");
		
        Mockito.when(usersAppService.parseTimesheetsJoinColumn("userid")).thenReturn(joinCol);
		mvc.perform(get("/users/1/timesheets?search=userid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
	    		  .andExpect(status().isOk());
	}  
	
	@Test
	public void GetTimesheets_searchIsNotEmpty() {
	
		Mockito.when(usersAppService.parseTimesheetsJoinColumn(anyString())).thenReturn(null);
	 		  		    		  
	    org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(get("/users/1/timesheets?search=userid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Invalid join column"));
	}    
	
    @Test
	public void GetUsertasks_searchIsNotEmptyAndPropertyIsNotValid_ThrowException() {
	
		Map<String,String> joinCol = new HashMap<String,String>();
		joinCol.put("id", "1");

		Mockito.when(usersAppService.parseUsertasksJoinColumn("userid")).thenReturn(joinCol);
		org.assertj.core.api.Assertions.assertThatThrownBy(() ->  mvc.perform(get("/users/1/usertasks?search=abc[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
	    		  .andExpect(status().isOk())).hasCause(new Exception("Wrong URL Format: Property abc not found!"));
	
	}    
	
	@Test
	public void GetUsertasks_searchIsNotEmptyAndPropertyIsValid_ReturnList() throws Exception {

		Map<String,String> joinCol = new HashMap<String,String>();
		joinCol.put("id", "1");
		
        Mockito.when(usersAppService.parseUsertasksJoinColumn("userid")).thenReturn(joinCol);
		mvc.perform(get("/users/1/usertasks?search=userid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
	    		  .andExpect(status().isOk());
	}  
	
	@Test
	public void GetUsertasks_searchIsNotEmpty() {
	
		Mockito.when(usersAppService.parseUsertasksJoinColumn(anyString())).thenReturn(null);
	 		  		    		  
	    org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(get("/users/1/usertasks?search=userid[equals]=1&limit=10&offset=1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())).hasCause(new EntityNotFoundException("Invalid join column"));
	}    
    
}

