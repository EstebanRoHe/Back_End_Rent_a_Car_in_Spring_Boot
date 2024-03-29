package com.rent_a_car.Controllers;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.rent_a_car.Model.Car;
import com.rent_a_car.Model.TypeCar;
import com.rent_a_car.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Car")
@CrossOrigin(origins="https://reant.vercel.app", maxAge = 3600)
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @Value("${cloudinary.cloud_name}")
    private String user;

    @Value("${cloudinary.api_key}")
    private String pass;

    @Value("${cloudinary.api_secret}")
    private String api_secret;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Car>> findAll(){
        List<Car> list=new ArrayList<Car>();
        carRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> create(@RequestParam("licencePlate") Optional<String> licencePlate,
                                      @RequestParam("description") Optional<String> description,
                                      @RequestParam("cylinder_capacity") Optional<String> cylinder_capacity,
                                      @RequestParam("capacity") Optional<Integer> capacity,
                                      @RequestParam("model_year") Optional<String> model_year,
                                      @RequestParam("imagen") Optional<MultipartFile> imagen,
                                      @RequestParam("price") Optional<Integer> price,
                                      @RequestParam("typeCar") Optional<TypeCar> typeCar) {
        try {
            Car car = new Car();
            car.setLicencePlate(licencePlate.orElse(null));
            car.setDescription(description.orElse(null));
            car.setCylinder_capacity(cylinder_capacity.orElse(null));
            car.setCapacity(capacity.orElse(null));
            car.setModel_year(model_year.orElse(null));

            if (imagen.isPresent() && !imagen.get().isEmpty()) {
                MultipartFile file = imagen.get();
                byte[] fileBytes = file.getBytes();
                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", user,
                        "api_key", pass,
                        "api_secret", api_secret
                ));
                Map<String, Object> uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("secure_url");
                car.setImage(imageUrl);
            }
            car.setPrice(price.orElse(null));
            car.setTypeCar(typeCar.orElse(null));
            return ResponseEntity.ok(carRepository.save(car));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{idCar}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Car> findById(@PathVariable Long idCar){
        if(!carRepository.findById(idCar).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carRepository.findById(idCar).get());
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> update(@RequestBody Car car){
        if(!carRepository.findById(car.getIdCar()).isPresent()){
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{idCar}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> delete(@PathVariable Long idCar){
        if(!carRepository.findById(idCar).isPresent()){
            ResponseEntity.badRequest().build();
        }

        carRepository.delete(carRepository.findById(idCar).get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filtro")
    @PreAuthorize("hasRole('ADMIN') or hasAnyRole('USER')")
    public ResponseEntity<List<Car>> getCarLicencePlate(@RequestParam String licencePlate) {
        List<Car> Car = carRepository.findByLicencePlateContainingIgnoreCase(licencePlate);
        if (!Car.isEmpty()) {
            return ResponseEntity.ok(Car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filtrodescription")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Car>> getCarDescription(@RequestParam String description) {
        List<Car> Car = carRepository.findByTypeCar_DescriptionContainingIgnoreCase (description);
        if (!Car.isEmpty()) {
            return ResponseEntity.ok(Car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}




/*
//guardar normal
    @PostMapping
    @PreAuthorize("permitAll()")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity create(@RequestBody Car car){
        if(carRepository.existsByLicencePlate(car.getLicencePlate())){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carRepository.save(car));
    }

 */

/*

//es para guardar local
@PostMapping
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public ResponseEntity<Car> create(@RequestParam("licencePlate") Optional<String> licencePlate,
                                  @RequestParam("description") Optional<String> description,
                                  @RequestParam("cylinder_capacity") Optional<String> cylinder_capacity,
                                  @RequestParam("capacity") Optional<Integer> capacity,
                                  @RequestParam("model_year") Optional<String> model_year,
                                  @RequestParam("imagen") Optional<MultipartFile> imagen,
                                  @RequestParam("typeCar") Optional<TypeCar> typeCar) {
    try {
        Car car = new Car();
        car.setLicencePlate(licencePlate.orElse(null));
        car.setDescription(description.orElse(null));
        car.setCylinder_capacity(cylinder_capacity.orElse(null));
        car.setCapacity(capacity.orElse(null));
        car.setModel_year(model_year.orElse(null));

        if (imagen.isPresent() && !imagen.get().isEmpty()) {
            byte[] bytes = imagen.get().getBytes();
            String filename = imagen.get().getOriginalFilename();
            Path path = Paths.get("src/main/resources/files/" + filename);
            Files.write(path, bytes);
            String imageUrl = "/files/" + filename;

            car.setImage(imageUrl);
        }
        car.setTypeCar(typeCar.orElse(null));
        return ResponseEntity.ok(carRepository.save(car));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
*/

