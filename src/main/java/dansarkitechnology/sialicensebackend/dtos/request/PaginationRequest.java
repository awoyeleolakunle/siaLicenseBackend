package dansarkitechnology.sialicensebackend.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {

    private Long examId;
    private int pageNumber;
    private int pageSize;
}
