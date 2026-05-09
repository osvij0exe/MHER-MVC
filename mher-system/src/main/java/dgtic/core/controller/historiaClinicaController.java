package dgtic.core.controller;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.dto.Request.DoctorRequest;
import dgtic.core.model.dto.Request.HistoriaClinicaRequest;
import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import dgtic.core.model.dto.Response.RecetaResponse;
import dgtic.core.service.doctorService.IDoctorService;
import dgtic.core.service.especialidadService.IEspecialidadService;
import dgtic.core.service.historiaClinicaService.IHistoriaClinicaService;
import dgtic.core.service.pacienteService.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("historiasClinicas")
public class historiaClinicaController {
    @Autowired
    IHistoriaClinicaService historiaClinicaService;

    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IEspecialidadService especialidadService;

    @Autowired
    IDoctorService doctorService;


    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Integer id) {

        HistoriaClinica historia =
                historiaClinicaService.findById(id);

        byte[] pdf = historiaClinicaService.generarPdf(historia);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("historia-clinica-" + id + ".pdf")
                        .build()
        );

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


}
