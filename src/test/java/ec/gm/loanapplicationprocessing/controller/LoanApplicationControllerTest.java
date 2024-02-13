package ec.gm.loanapplicationprocessing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.gm.loanapplicationprocessing.LoanApplicationProcessingApplication;
import ec.gm.loanapplicationprocessing.controller.request.LoanApplicationRequest;
import ec.gm.loanapplicationprocessing.controller.response.LoanApplicationResponse;
import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.model.LoanApplicationTestFactory;
import ec.gm.loanapplicationprocessing.service.LoanApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoanApplicationProcessingApplication.class})
@WebMvcTest(LoanApplicationController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class LoanApplicationControllerTest {
    private static final Long LOAN_APPLICATION_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private LoanApplicationService loanApplicationService;

    private LoanApplication loanApplication;
    private LoanApplicationRequest loanApplicationRequest;
    private LoanApplicationResponse loanApplicationResponse;
    private String applicant = "Galo";
    private String officer = "Officer GM";


    @BeforeEach
    void init() {

        loanApplication = LoanApplicationTestFactory.getInstance().createLoanApplication();
        loanApplicationRequest = new LoanApplicationRequest(applicant, officer);
        loanApplicationResponse = new LoanApplicationResponse(loanApplication);
    }

    @Test
    void postLoanApplicationTest() throws Exception {
        when(loanApplicationService.createLoanApplication(applicant, officer)).thenReturn(loanApplication);
        String requestString = objectMapper.writeValueAsString(loanApplicationRequest);
        String responseString = objectMapper.writeValueAsString(loanApplicationResponse);
        mockMvc.perform(post("/loanApplications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseString));
    }

    @Test
    void getLoanApplicationTest() throws Exception {
        when(loanApplicationService.getLoanApplication(LOAN_APPLICATION_ID)).thenReturn(loanApplication);
        mockMvc.perform(get("/loanApplications/" + LOAN_APPLICATION_ID))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(loanApplicationResponse)));
    }
}
