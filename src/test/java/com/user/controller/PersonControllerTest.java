//package com.user.controller;
//
//import com.user.response.Response;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class, secure = false)
//public class PersonControllerTest {
//
//    @MockBean
//    private PersonService personService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private Person mockPerson = new Person("1a", "Fabricio", "Brasil");
//
//    private String examplePersonJson = "{\"name\":\"Fabricio\",\"country\":\"Brasil\"}";
//
//
//    @Test
//    public void retrievePeople() throws Exception{
//
//
//        List<Person> people = new ArrayList<>();
//        people.add(mockPerson);
//
//        Response<List<Person>> personList = new Response<>();
//        personList.setData(people);
//
//        Mockito.when(personService.listAll())
//                .thenReturn(ResponseEntity.ok(personList));
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/api/person/all")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//
//        System.out.println("Reponse Result: " + result.getResponse().getContentAsString());
//        System.out.println("Reponse Result 2 : " + result.getResponse().getStatus());
//
//        String expected = "{\"data\":[{\"id\":\"1a\",\"name\":\"Fabricio\",\"country\":\"Brasil\"}],\"errors\":[]}";
//
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
//
//    }
//
//    @Test
//    public void retrievePerson() throws Exception{
//
//        Response<Person> responsePerson = new Response<>();
//        responsePerson.setData(mockPerson);
//
//        Mockito.when(personService.listPerson(Mockito.anyString()))
//                .thenReturn(ResponseEntity.ok(responsePerson));
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/api/person")
//                .param("id", "1a")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//
//        System.out.println("Reponse Result: " + result.getResponse().getContentAsString());
//        System.out.println("Reponse Result 2 : " + result.getResponse().getStatus());
//
//        String expected = "{\"data\":{\"id\":\"1a\",\"name\":\"Fabricio\",\"country\":\"Brasil\"},\"errors\":[]}";
//
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
//
//    }
//
//
////    @Test
////    public void insertPerson() throws Exception{
////
////        Response<Person> responseDto = new Response<>();
////        responseDto.setData(mockPerson);
////
////        BindingResult bindingResult = Mockito.mock(BindingResult.class);
////
////
////        Mockito.when(personService.insert(mockPerson, bindingResult))
////                .thenReturn(ResponseEntity.status(HttpStatus.OK).body(responseDto));
////
////        RequestBuilder requestBuilder = MockMvcRequestBuilders
////                .post("/api/person")
////                .accept(MediaType.APPLICATION_JSON)
////                .content(examplePersonJson)
////                .contentType(MediaType.APPLICATION_JSON);
////
////
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////        MockHttpServletResponse response = result.getResponse();
////
////        System.out.println("Response Result: " + result.getResponse().getContentAsString());
////        System.out.println("Response Result 2 : " + response.getStatus());
////
////        String expected = "{id:1a,name:Fabricio,country:Brasil}";
////
////        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
////
//////        Mockito.when(
//////                studentService.retrieveCourse(Mockito.anyString(),
//////                        Mockito.anyString())).thenReturn(mockCourse);
//////
//////        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//////                "/students/Student1/courses/Course1").accept(
//////                MediaType.APPLICATION_JSON);
//////
//////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//////
//////        System.out.println(result.getResponse());
//////        String expected = "{id:Course1,name:Spring,description:10 Steps}";
//////
//////        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
//////
//////        JSONAssert.assertEquals(expected, result.getResponse()
//////                .getContentAsString(), false);
////
////    }
//
//
//    @Test
//    public void deletePerson() throws Exception{
//
//        Response<Person> responsePerson = new Response<>();
//        responsePerson.setData(mockPerson);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/api/person/1a")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println("Reponse Result 2 : " + result.getResponse().getStatus());
//
//        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
//
//    }
//
//
//
//
//
//}
