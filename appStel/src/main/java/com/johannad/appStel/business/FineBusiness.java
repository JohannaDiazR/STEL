package com.johannad.appStel.business;

import com.johannad.appStel.dtos.FineDto;
import com.johannad.appStel.dtos.PropertyDto;
import com.johannad.appStel.dtos.WalletStatusDto;
import com.johannad.appStel.dtos.WorkerDto;
import com.johannad.appStel.entity.Fine;
import com.johannad.appStel.entity.Property;
import com.johannad.appStel.entity.WalletStatus;
import com.johannad.appStel.entity.Worker;
import com.johannad.appStel.service.FineService;
import com.johannad.appStel.service.PropertyService;
import com.johannad.appStel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FineBusiness {
    @Autowired
    private FineService fineService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private WorkerService workerService;

    private List<Fine> fineList;
    private List<FineDto> fineDtoList = new ArrayList<>();

    public List<FineDto> findAll() throws Exception {
        this.fineList = this.fineService.findAll();
        this.fineList.stream().forEach(fine -> {
            FineDto fineDto = new FineDto();
            fineDto.setId(fine.getId());

            Property property = fine.getProperty();
            if (property!=null){
                PropertyDto propertyDto = new PropertyDto();
                propertyDto.setId(property.getId());
                propertyDto.setAndInmueble(property.getAndInmueble());
                propertyDto.setNumInmueble(property.getNumInmueble());
                fineDto.setProperty(propertyDto);
            }
            Worker worker = fine.getWorker();
            if (worker != null){
                WorkerDto workerDto = new WorkerDto();
                workerDto.setId(worker.getId());
                workerDto.setNomTrabajador(worker.getNomTrabajador());
                workerDto.setCcTrabajador(worker.getCcTrabajador());
                workerDto.setCelTrabajador(worker.getCelTrabajador());
                workerDto.setEmaTrabajador(worker.getEmaTrabajador());
                workerDto.setTpcoTrabajador(worker.getTpcoTrabajador());
                workerDto.setConTrabajador(worker.getConTrabajador());
                workerDto.setCargTrabajador(worker.getCargTrabajador());
                workerDto.setEmpTrabajador(worker.getEmpTrabajador());
                fineDto.setWorker(workerDto);
            }
            fineDto.setTipoMulta(fine.getTipoMulta());
            fineDto.setFecMulta(fine.getFecMulta());
            fineDto.setEvidMulta(fine.getEvidMulta());
            fineDto.setValMulta(fine.getValMulta());
            fineDto.setFpagMulta(fine.getFpagMulta());

            fineDtoList.add(fineDto);
        });
        return this.fineDtoList;
    }
    //POST
    public FineDto create(FineDto fineDto) throws Exception {
        Fine fine = mapDtoToFine(fineDto);
        Fine createdFine = fineService.create(fine);
        return mapFineToDto(createdFine);
    }
    //PUT
    public void update(FineDto fineDto, int id) throws Exception {
        Fine existingFine = fineService.findById(id);
        if (existingFine == null) {
            throw new Exception("Fine not found");
        }

        existingFine.setTipoMulta(fineDto.getTipoMulta());
        existingFine.setFecMulta(fineDto.getFecMulta());
        existingFine.setEvidMulta(fineDto.getEvidMulta());
        existingFine.setValMulta(fineDto.getValMulta());
        existingFine.setFpagMulta(fineDto.getFpagMulta());

        PropertyDto propertyDto = fineDto.getProperty();
        if (propertyDto != null) {
            Property existingProperty = existingFine.getProperty();
            if (existingProperty == null) {
                existingProperty = new Property();
            }
            existingProperty.setId(propertyDto.getId());
            existingProperty.setAndInmueble(propertyDto.getAndInmueble());
            existingProperty.setNumInmueble(propertyDto.getNumInmueble());

            existingFine.setProperty(existingProperty);
        }
        WorkerDto workerDto = fineDto.getWorker();
        if (workerDto != null){
            Worker existingWorker = existingFine.getWorker();
            if (existingWorker == null) {
                existingWorker = new Worker();
            }
            existingWorker.setId(workerDto.getId());
            existingWorker.setNomTrabajador(workerDto.getNomTrabajador());
            existingWorker.setCcTrabajador(workerDto.getCcTrabajador());
            existingWorker.setCelTrabajador(workerDto.getCelTrabajador());
            existingWorker.setEmaTrabajador(workerDto.getEmaTrabajador());
            existingWorker.setTpcoTrabajador(workerDto.getTpcoTrabajador());
            existingWorker.setConTrabajador(workerDto.getConTrabajador());
            existingWorker.setCargTrabajador(workerDto.getCargTrabajador());
            existingWorker.setEmpTrabajador(workerDto.getEmpTrabajador());

            existingFine.setWorker(existingWorker);
        }
        fineService.update(existingFine);
    }
    private FineDto mapFineToDto(Fine fine) {
        FineDto fineDto = new FineDto();
        fineDto.setId(fine.getId());


        PropertyDto propertyDto = new PropertyDto();
        Property property = fine.getProperty();
        if (property != null) {

            propertyDto.setId(property.getId());
            propertyDto.setAndInmueble(property.getAndInmueble());
            propertyDto.setNumInmueble(property.getNumInmueble());
            fineDto.setProperty(propertyDto);
        }

        WorkerDto workerDto = new WorkerDto();
        Worker worker = fine.getWorker();
        if (worker != null){

            workerDto.setId(workerDto.getId());
            workerDto.setNomTrabajador(worker.getNomTrabajador());
            workerDto.setCcTrabajador(worker.getCcTrabajador());
            workerDto.setCelTrabajador(worker.getCelTrabajador());
            workerDto.setEmaTrabajador(worker.getEmaTrabajador());
            workerDto.setTpcoTrabajador(worker.getTpcoTrabajador());
            workerDto.setConTrabajador(worker.getConTrabajador());
            workerDto.setCargTrabajador(worker.getCargTrabajador());
            workerDto.setEmpTrabajador(worker.getEmpTrabajador());
            fineDto.setWorker(workerDto);
        }
        fineDto.setTipoMulta(fine.getTipoMulta());
        fineDto.setFecMulta(fine.getFecMulta());
        fineDto.setEvidMulta(fine.getEvidMulta());
        fineDto.setValMulta(fine.getValMulta());
        fineDto.setFpagMulta(fine.getFpagMulta());

        return fineDto;
    }

    private Fine mapDtoToFine(FineDto fineDto) {
        Fine fine = new Fine();
        fine.setTipoMulta(fineDto.getTipoMulta());
        fine.setFecMulta(fineDto.getFecMulta());
        fine.setEvidMulta(fineDto.getEvidMulta());
        fine.setValMulta(fineDto.getValMulta());
        fine.setFpagMulta(fineDto.getFpagMulta());

        PropertyDto propertyDto = fineDto.getProperty();
        if (propertyDto != null){
            Property property = new Property();
            property.setId(propertyDto.getId());
            property.setAndInmueble(propertyDto.getAndInmueble());
            property.setNumInmueble(propertyDto.getNumInmueble());
            fine.setProperty(property);
        }

        WorkerDto workerDto = fineDto.getWorker();
        if (workerDto != null) {
            Worker worker = new Worker();

            worker.setId(workerDto.getId());
            worker.setNomTrabajador(workerDto.getNomTrabajador());
            worker.setCcTrabajador(workerDto.getCcTrabajador());
            worker.setCelTrabajador(workerDto.getCelTrabajador());
            worker.setEmaTrabajador(workerDto.getEmaTrabajador());
            worker.setTpcoTrabajador(workerDto.getTpcoTrabajador());
            worker.setConTrabajador(workerDto.getConTrabajador());
            worker.setCargTrabajador(workerDto.getCargTrabajador());
            worker.setEmpTrabajador(workerDto.getEmpTrabajador());

            fine.setWorker(worker);
        }
        return fine;
    }

}