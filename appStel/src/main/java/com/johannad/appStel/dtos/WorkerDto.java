package com.johannad.appStel.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private int id;
    private String nomTrabajador;
    private int ccTrabajador;
    private long celTrabajador;
    private String emaTrabajador;
    private String tpcoTrabajador;
    private String conTrabajador;
    private String cargTrabajador;
    private String empTrabajador;

    private RoleDto role;












    //private List<CorrespondeceDto> correspondeceDtoList;
    //private RoleDto fkid_rol;
    //private  List<VisitorDto> visitorDtoList;
    //private List<RoleDto> roleDtoList;


}