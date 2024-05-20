package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.Gugun;
import com.ssafy.enjoytrip.model.dto.Sido;
import com.ssafy.enjoytrip.model.service.AreaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AreaRestController.class)
class AreaRestControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AreaService areaService;
//
//    private List<Sido> sidoList;
//    private List<Gugun> gugunList;
//
//    @BeforeEach
//    void setUp() {
//        Sido sido1 = new Sido(1, "서울");
//        Sido sido2 = new Sido(6, "부산");
//        sidoList = Arrays.asList(sido1, sido2);
//
//        Gugun gugun1 = new Gugun(1, "강남구", 1);
//        Gugun gugun2 = new Gugun(2, "강화군", 1);
//        gugunList = Arrays.asList(gugun1, gugun2);
//    }
//
//    @Test
//    void testGetSido() throws Exception {
//        given(areaService.getSidoList()).willReturn(sidoList);
//
//        mockMvc.perform(get("/areasido/sido")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{'sidoCode':'1','sidoName':'서울'},{'sidoCode':'6','sidoName':'부산'}]"));
//    }
//
//    @Test
//    void testGetSido_NoContent() throws Exception {
//        given(areaService.getSidoList()).willReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/areasido/sido")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void testGetGugun() throws Exception {
//        given(areaService.getGugunList(1)).willReturn(gugunList);
//
//        mockMvc.perform(get("/areasido/gugun/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{'gugunCode':'1','gugunName':'강남구','sidoCode':'1'},{'gugunCode':'2','gugunName':'강화군','sidoCode':'1'}]"));
//    }
//
//    @Test
//    void testGetGugun_NoContent() throws Exception {
//        given(areaService.getGugunList(1)).willReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/areasido/gugun/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
}
