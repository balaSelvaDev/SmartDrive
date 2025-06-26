package mca.finalyearproject.smartDrive.SmartDrive.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "brand_list")
@Getter
@Setter
public class BrandEntity {

    @lombok.Setter
    @Id
    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created_date_time", updatable = false, insertable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "last_updated_date_time", insertable = false)
    private LocalDateTime lastUpdatedDateTime;

}
