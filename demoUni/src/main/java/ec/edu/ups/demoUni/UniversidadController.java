package ec.edu.ups.demoUni;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/universidad")
public class UniversidadController {
	
	@Autowired
	private UniversidadRepository uniRepository;
	
	@GetMapping
	public List<Universidad> getAllUniversities(){
		return uniRepository.findAll();
	}
	
	@GetMapping("/{id}")
    public Universidad getBookById(@PathVariable Integer id){
        return uniRepository.findById(id).get();
    }

	@PostMapping
	public ResponseEntity<?> createUniversidad(@RequestBody Universidad universidad) {
		Universidad nuevaUni = uniRepository.save(universidad);
		return ResponseEntity.ok("Universidad creada exitosamente con ID: " + nuevaUni.getUniId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUniversity(@PathVariable Integer id) {
	    return uniRepository.findById(id)
	        .map(uni -> {
	            uniRepository.deleteById(id);
	            return ResponseEntity.ok("Universidad eliminada exitosamente con ID: " + id);
	        })
	        .orElse(ResponseEntity.badRequest().body("Universidad no encontrada con ID: " + id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUniversity(@PathVariable Integer id, @RequestBody Universidad universidad) {
	    return uniRepository.findById(id)
	        .map(existingUni -> {
	            existingUni.setNombre(universidad.getNombre());
	            existingUni.setDireccion(universidad.getDireccion());
	            existingUni.setFundador(universidad.getFundador());
	            uniRepository.save(existingUni);
	            return ResponseEntity.ok("Universidad actualizada exitosamente con ID: " + id);
	        })
	        .orElse(ResponseEntity.badRequest().body("Universidad no encontrada con ID: " + id));
	}
}
