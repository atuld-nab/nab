package au.com.nab.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import au.com.nab.service.CurrencyService;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService service;

    @Test
    public void canFetchAll() throws Exception {
        this.mvc.perform(get("/getAll")).andExpect(status().isOk());

    }


    @Test
    public void canFetchByCurrencyCode() throws Exception
    {
        this.mvc.perform(get("/getCode/{code}","LTC")).andExpect(status().isOk());
    }


}