import java.sql.Timestamp;
import javax.persistence.GenerationType;
@Data
@Table(name="reservations")
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ReservationHistoEntity {
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="reservation_date", nullable=false)
    private Timestamp createdAt;
    @Column(name="reservation_status", nullable=false)
    private String status;
    @Column(name="reservation_price", nullable=false)
    private Double price;
}
