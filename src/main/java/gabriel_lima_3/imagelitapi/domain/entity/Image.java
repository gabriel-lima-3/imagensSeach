package gabriel_lima_3.imagelitapi.domain.entity;
import gabriel_lima_3.imagelitapi.domain.enums.ImageExtensions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name ="tb_image")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Image {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;
    @Column
    private Long size;
    @Column
    @Enumerated (EnumType.STRING)
    private ImageExtensions extensions;
    @Column
    @CreatedDate ()
    private LocalDateTime uploadoTime;
    @Column
    private String tags;
    @Column
    @Lob
    private byte[] files;

    public String getFileName(){
       return getName().concat(".").concat(getExtensions().name());
    }



}
