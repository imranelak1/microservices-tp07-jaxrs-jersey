package ma.rest.spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;
import jakarta.xml.bind.annotation.*;

/** Compte persisté ; le type est stocké par rang comme demandé pour Jersey. */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "compte")
@XmlAccessorType(XmlAccessType.FIELD)
public class Compte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @PositiveOrZero private double solde;

  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
  @NotNull
  private Date dateCreation;

  @Enumerated(EnumType.ORDINAL)
  @NotNull
  private TypeCompte type;
}
