package com.pharmeasy.admin.service;

import com.pharmeasy.admin.dto.request.MarkVisitedRequestDTO;
import com.pharmeasy.admin.dto.response.FitnessReasonDTO;
import com.pharmeasy.admin.dto.response.LocationResponsedDTO;
import com.pharmeasy.qsr.commonpersistence.entity.lab.MedicalLab;
import com.pharmeasy.qsr.commonpersistence.entity.lab.VisitedStatus;
import com.pharmeasy.qsr.commonpersistence.entity.masters.LocationAreas;
import com.pharmeasy.qsr.commonpersistence.entity.masters.PhleboOrderStatusLog;
import com.pharmeasy.qsr.commonpersistence.entity.masters.ProcessStatus;
import com.pharmeasy.qsr.commonpersistence.entity.orders.Order;
import com.pharmeasy.qsr.commonpersistence.entity.orders.OrderAgent;
import com.pharmeasy.qsr.commonpersistence.entity.orders.QsrCheckups;
import com.pharmeasy.qsr.commonpersistence.repository.lab.MedicalLabRepository;
import com.pharmeasy.qsr.commonpersistence.repository.lab.VisitedStatusRepository;
import com.pharmeasy.qsr.commonpersistence.repository.masters.LocationAreasRepository;
import com.pharmeasy.qsr.commonpersistence.repository.masters.ProcessStatusRepository;
import com.pharmeasy.qsr.commonpersistence.repository.orders.OrderAgentRepository;
import com.pharmeasy.qsr.commonpersistence.repository.orders.OrderRepository;
import com.pharmeasy.qsr.commonpersistence.repository.orders.QsrCheckupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LabsService {

    @Autowired
    private MedicalLabRepository medicalLabRepository;

    @Autowired
    private OrderAgentRepository orderAgentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VisitedStatusRepository visitedStatusRepository;

    @Autowired
    private ProcessStatusRepository processStatusRepository;

    @Autowired
    private LocationAreasRepository locationAreasRepository;

    @Autowired
            private QsrCheckupRepository qsrCheckupRepository;

    LocationAreas locationAreas = new LocationAreas();
//    ProcessStatus processStatus=new ProcessStatus();

    public List<MedicalLab> getLabs() {
        List<MedicalLab> medicalLab = medicalLabRepository.findAll();
        return medicalLab;
    }

    public List<OrderAgent> getAgentList() {
        List<OrderAgent> agents = orderAgentRepository.findAll();
        return agents;
    }

    public FitnessReasonDTO getReasons(String type) {
        FitnessReasonDTO fitnessReasonDTO = new FitnessReasonDTO();
        ProcessStatus processStatus = processStatusRepository.getReasons(type);
        fitnessReasonDTO.setId(processStatus.getId());
//        fitnessReasonDTO.setNewEntry(processStatus.getNewEntry());
        fitnessReasonDTO.setStatus(processStatus.getStatus());
        return fitnessReasonDTO;
    }

//    public List<LocationResponsedDTO> getLocationAreaList() {
//        List<LocationAreas> locationAreas = locationAreasRepository.getListForLocationAreas();
//        List<LocationResponsedDTO> locationList = new ArrayList<>();
//        for (LocationAreas location : locationAreas) {
//            LocationResponsedDTO locationResponsedDTO = new LocationResponsedDTO();
//            locationResponsedDTO.setId(location.getId());
//            locationResponsedDTO.setName(location.getName());
//            locationResponsedDTO.setPincode(location.getPincode());
//            locationList.add(locationResponsedDTO);
//        }
//        return locationList;
//    }

    public VisitedStatus markVisited(MarkVisitedRequestDTO markVisitedRequestDTO,Long encryptedId){
        VisitedStatus visitedStatus=visitedStatusRepository.updateStatus();
        Order order=orderRepository.findByEncryptedId(encryptedId);
        QsrCheckups qsrCheckups=qsrCheckupRepository.findByOrderId(order.getId());
        order.setIsVisited(markVisitedRequestDTO.getIsVisited());

        if(markVisitedRequestDTO.getIsVisited()==true){
            order.setVisitedTime(new Date());
            qsrCheckups.setCheckupStatus("report_pending");
            qsrCheckups.setReportsPendingDate(new Date());
        }
        PhleboOrderStatusLog phleboOrderStatusLog=new PhleboOrderStatusLog();
        phleboOrderStatusLog.setEncryptedOrderId(encryptedId);
        phleboOrderStatusLog.setStatus(String.valueOf(visitedStatus));
        phleboOrderStatusLog.setOrder_type(order.getOrderStatus());

        Long phleboOrderStatusLogId =phleboOrderStatusLog.getId();
//         if(markVisitedRequestDTO.getIsVisited(qsrCheckups.))
        return visitedStatus;
    }
}

