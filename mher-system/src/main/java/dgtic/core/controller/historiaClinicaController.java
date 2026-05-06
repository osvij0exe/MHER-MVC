package dgtic.core.controller;

import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.service.historiaClinicaService.IHistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("historiaClinica")
public class historiaClinicaController {
    @Autowired
    IHistoriaClinicaService historiaClinicaService;


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
