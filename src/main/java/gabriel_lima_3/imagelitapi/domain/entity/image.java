package gabriel_lima_3.imagelitapi.domain.entity;
import gabriel_lima_3.imagelitapi.Enums.ImageExtendions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name ="tb_image")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class image {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;
    @Column
    private Long size;
    @Column
    @Enumerated (EnumType.ORDINAL)
    private ImageExtendions images;
    @Column
    @CreatedDate ()
    private LocalDateTime uploadoTime;
    @Column
    private String tags;
    @Column
    @Lob
    private byte[] files;


}
