package ec.edu.ups.demoUni;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // 🔥 Habilitar CORS
@RestController
@RequestMapping("/api/universidad")
public class UniversidadController {
	
	@Autowired
	private UniversidadRepository uniRepository;

	// Obtener todas las universidades
	@GetMapping
	public ResponseEntity<List<Universidad>> getAllUniversities() {
		List<Universidad> universidades = uniRepository.findAll();
		return ResponseEntity.ok(universidades);
	}

	// Obtener universidad por ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getUniversidadById(@PathVariable Integer id) {
		Optional<Universidad> universidad = uniRepository.findById(id);
		if (universidad.isPresent()) {
			return ResponseEntity.ok(universidad.get());
		} else {
			return ResponseEntity.badRequest().body("{\"error\": \"Universidad no encontrada con ID: " + id + "\"}");
		}
	}

	// Crear nueva universidad
	@PostMapping
	public ResponseEntity<?> createUniversidad(@RequestBody Universidad universidad) {
		Universidad nuevaUni = uniRepository.save(universidad);
		return ResponseEntity.ok(nuevaUni); // 🔄 Retorna el objeto creado en JSON
	}

	// Actualizar universidad
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUniversity(@PathVariable Integer id, @RequestBody Universidad universidad) {
		return uniRepository.findById(id)
			.map(existingUni -> {
				existingUni.setNombre(universidad.getNombre());
				existingUni.setDireccion(universidad.getDireccion());
				existingUni.setFundador(universidad.getFundador());
				Universidad updatedUni = uniRepository.save(existingUni);
				return ResponseEntity.ok(updatedUni); // 🔄 Retorna la universidad actualizada en JSON
			})
			.orElse(ResponseEntity.badRequest().body("{\"error\": \"Universidad no encontrada con ID: " + id + "\"}"));
	}

	// Eliminar universidad
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUniversity(@PathVariable Integer id) {
	    return uniRepository.findById(id)
	        .map(uni -> {
	            uniRepository.deleteById(id);
	            return ResponseEntity.ok("{\"message\": \"Universidad eliminada exitosamente con ID: " + id + "\"}");
	        })
	        .orElse(ResponseEntity.badRequest().body("{\"error\": \"Universidad no encontrada con ID: " + id + "\"}"));
	}
}
