package com.pharmeasy.admin.controller;

import com.pharmeasy.admin.dto.request.MarkVisitedRequestDTO;
import com.pharmeasy.admin.dto.response.FitnessReasonDTO;
import com.pharmeasy.admin.dto.response.LocationResponsedDTO;
import com.pharmeasy.admin.service.LabsService;
import com.pharmeasy.qsr.commonpersistence.entity.lab.MedicalLab;
import com.pharmeasy.qsr.commonpersistence.entity.lab.VisitedStatus;
import com.pharmeasy.qsr.commonpersistence.entity.masters.LocationAreas;
import com.pharmeasy.qsr.commonpersistence.entity.orders.OrderAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private LabsService labsService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest() {
        return "testing";
    }

    @GetMapping("/employers/checkupsAll")
    public String getAllCheckups() {
        return "all checkups";
    }

    @GetMapping("/employers/companies")
    public String getCompanies() {
        return "all checkups";

    }

    @RequestMapping(value = "/labs/all",method = RequestMethod.GET)
    public List<MedicalLab> getAllLabs(){
        return labsService.getLabs();
    }

    @RequestMapping(value = "/list/agents",method = RequestMethod.GET)
    public List<OrderAgent> getAgentList(){
        return labsService.getAgentList();
    }

    @RequestMapping(value = "/labs/fitness/reasons",method = RequestMethod.GET)
    public FitnessReasonDTO getFitnessReasons(String type){
        return labsService.getReasons(type);
    }

    @RequestMapping(value = "/labs/{lab_id}/reports/{report_id}/remove",method = RequestMethod.GET)
    public FitnessReasonDTO removeReportId(String type){
        return labsService.getReasons(type);
    }

//    @RequestMapping(value = "/labs/{lab_id}/orders/{encrypted_id}/reports/confirmation",method = RequestMethod.GET)
//    public String GetEncryptedId(Long encryptedId,Long labId){
//        return labsService.getOrderEncryptedId(encryptedId,labId);
//    }

//    @RequestMapping(value = "/lists/location-area-web",method = RequestMethod.GET)
//    public List<LocationResponsedDTO> getLocationArea(){
//        return labsService.getLocationAreaList();
//    }

    @RequestMapping(value="/orders/mark-visited",method = RequestMethod.POST)
    public VisitedStatus labMarkVisited(MarkVisitedRequestDTO markVisitedRequestDTO,Long encryptedId){
       return labsService.markVisited(markVisitedRequestDTO,encryptedId);
    }

}
