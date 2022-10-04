package exceptions;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
	LocalDateTime timestamp;
	String mensaje;
	String detalles;
	
}
