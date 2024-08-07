package com.microservices.Services.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.microservices.Services.persistence.entity.ServiceEntity;
import com.microservices.Services.persistence.services.FunctionService;

@RestController
@RequestMapping("/api/services")
public class ControllerServices {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    private FunctionService functionService;

    public ControllerServices(FunctionService functionService) {
        this.functionService = functionService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllServices() {
        try {
            return ResponseEntity.ok(functionService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/searchId/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(functionService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<?> getServiceByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(functionService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveService(@RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("available") Boolean available) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            String nameFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(uploadDir, nameFile).toString();
            Files.write(file.getBytes(), new java.io.File(filePath));
            String fileUrl = "/upload/" + nameFile;
            String fullFileUrl = "http://localhost:8060" + fileUrl;
            ServiceEntity service = new ServiceEntity();
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);
            service.setAvailable(available);
            service.setImageUrl(fullFileUrl);
            functionService.save(service);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        try {
            functionService.delete(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/sale/{price}")
    public ResponseEntity<?> countServices(@PathVariable Double price) {
        try {
            return ResponseEntity.ok(functionService.findByPrice(price));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/sale/{price}/{name}")
    public ResponseEntity<?> priceServices(@PathVariable Double price, @PathVariable String name) {
        try {
            return ResponseEntity.ok(functionService.findByPriceAndName(price, name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{available}")
    public ResponseEntity<?> findByAvailable(@PathVariable Boolean available) {
        try {
            return ResponseEntity.ok(functionService.findByAvailable(available));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("available") Boolean available) {
        try {
            // Buscar el servicio existente
            ServiceEntity service = functionService.findById(id).orElse(null);
            if (service == null) {
                return ResponseEntity.notFound().build();
            }

            // Si se proporciona un nuevo archivo, manejar la actualización de la imagen
            if (!file.isEmpty()) {
                // Eliminar la imagen antigua si existe
                if (service.getImageUrl() != null) {
                    String oldFileName = service.getImageUrl().substring(service.getImageUrl().lastIndexOf("/") + 1);
                    String oldFilePath = Paths.get(uploadDir, oldFileName).toString();
                    java.nio.file.Files.deleteIfExists(new java.io.File(oldFilePath).toPath());
                }

                // Guardar el nuevo archivo
                String nameFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String filePath = Paths.get(uploadDir, nameFile).toString();
                Files.write(file.getBytes(), new java.io.File(filePath));
                String fileUrl = "/upload/" + nameFile;
                String fullFileUrl = "http://localhost:8060" + fileUrl;
                service.setImageUrl(fullFileUrl);
            }

            // Actualizar el resto de los campos
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);
            service.setAvailable(available);
            functionService.UpdateUser(id, service);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
